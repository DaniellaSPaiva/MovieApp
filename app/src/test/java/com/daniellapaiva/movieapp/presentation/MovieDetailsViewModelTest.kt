package com.daniellapaiva.movieapp.presentation

import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.daniellapaiva.movieapp.presentation.viewmodel.MovieDetailsViewModel
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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest : KoinTest {

    private val movieRepository: MovieRepository = mockk()
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase by inject()
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        startKoin {
            modules(
                module {
                    single { movieRepository }
                    single { GetMovieDetailsUseCase(movieRepository) }
                }
            )
        }

        movieDetailsViewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `should update movieDetails state when fetchMovieDetails is called`() = runTest {

        val movieId = 123
        val expectedMovie = Movie(
            id = 123,
            title = "Example Movie",
            overview = "An example movie overview",
            posterPath = "/path_to_poster.jpg"
        )

        coEvery { getMovieDetailsUseCase(movieId) } returns expectedMovie

        movieDetailsViewModel.fetchMovieDetails(movieId)

        advanceUntilIdle()

        assertEquals(expectedMovie, movieDetailsViewModel.movieDetails.first())
    }
}
