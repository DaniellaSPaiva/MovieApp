package com.daniellapaiva.movieapp.presentation

import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.presentation.viewmodel.MovieListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest: KoinTest {

    private val movieRepository: MovieRepository = mockk()
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase by inject()
    private lateinit var movieListViewModel: MovieListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(
                module {
                    single { movieRepository }
                    single { GetPopularMoviesUseCase(get()) }
                }
            )
        }

        coEvery { movieRepository.getPopularMovies() } returns listOf(
            Movie(id = 1, title = "Movie 1", overview = "Overview 1", posterPath = "/path1.jpg"),
            Movie(id = 2, title = "Movie 2", overview = "Overview 2", posterPath = "/path2.jpg")
        )

        movieListViewModel = MovieListViewModel(getPopularMoviesUseCase)
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `should update movies state when fetchPopularMovies is called`() = runTest {
        val expectedMovies = listOf(
            Movie(id = 1, title = "Movie 1", overview = "Overview 1", posterPath = "/path1.jpg"),
            Movie(id = 2, title = "Movie 2", overview = "Overview 2", posterPath = "/path2.jpg")
        )

        advanceUntilIdle()

        assertEquals(expectedMovies, movieListViewModel.popularMovies.first())
    }

    @Test
    fun `should update movies state with empty list when no popular movies are available`() = runTest {
        coEvery { movieRepository.getPopularMovies() } returns emptyList()

        movieListViewModel = MovieListViewModel(getPopularMoviesUseCase)

        advanceUntilIdle()

        assertEquals(emptyList(), movieListViewModel.popularMovies.first())
    }
}
