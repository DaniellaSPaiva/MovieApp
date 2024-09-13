package com.daniellapaiva.movieapp.presentation

import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.domain.util.AppError
import com.daniellapaiva.movieapp.presentation.common.UIState
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
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest : KoinTest {

    private val movieRepository: MovieRepository = mockk()
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase by inject()
    private lateinit var movieListViewModel: MovieListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        startKoin {
            modules(module {
                single { movieRepository }
                single { GetPopularMoviesUseCase(get()) }
            })
        }

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
        coEvery { getPopularMoviesUseCase() } returns Result.success(expectedMovies)

        movieListViewModel.fetchPopularMovies()
        advanceUntilIdle()

        val state = movieListViewModel.uiState.first()
        assertTrue(state is UIState.Success)
        assertEquals(expectedMovies, state.data)
    }

    @Test
    fun `should update movies state with empty list when no popular movies are available`() =
        runTest {
            coEvery { getPopularMoviesUseCase() } returns Result.success(emptyList())

            movieListViewModel.fetchPopularMovies()
            advanceUntilIdle()

            val state = movieListViewModel.uiState.first()
            assertTrue(state is UIState.Success)
            assertEquals(emptyList(), state.data)
        }

    @Test
    fun `should update error state when fetchPopularMovies fails with NetworkError`() = runTest {

        coEvery { getPopularMoviesUseCase() } returns Result.failure(AppError.NetworkError)

        movieListViewModel.fetchPopularMovies()
        advanceUntilIdle()

        val state = movieListViewModel.uiState.first()
        assertTrue(state is UIState.Error)
        assertTrue((state.error as? Throwable)?.message == "No internet connection")
    }

    @Test
    fun `should update error state when fetchPopularMovies fails with UnknownError`() = runTest {

        val errorMessage = "Unexpected error"
        coEvery { getPopularMoviesUseCase() } returns Result.failure(
            AppError.UnknownError(
                errorMessage
            )
        )

        movieListViewModel.fetchPopularMovies()
        advanceUntilIdle()

        val state = movieListViewModel.uiState.first()
        assertTrue(state is UIState.Error)
        assertTrue((state.error as? Throwable)?.message == "Unexpected error")
    }
}
