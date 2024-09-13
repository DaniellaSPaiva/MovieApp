package com.daniellapaiva.movieapp.domain

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.daniellapaiva.movieapp.domain.util.AppError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetMovieDetailsUseCaseTest {

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private val movieRepository: MovieRepository = mockk()

    @Before
    fun setUp() {
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    }

    @Test
    fun `should return success when movie details are fetched successfully`() = runBlocking {

        val movieId = 123
        val expectedMovie = Movie(
            id = movieId,
            title = "Example Movie",
            overview = "An example movie overview",
            posterPath = "/path_to_poster.jpg"
        )

        coEvery { movieRepository.getMovieDetails(movieId) } returns expectedMovie

        val result = getMovieDetailsUseCase(movieId)

        assertTrue(result.isSuccess)
        assertEquals(expectedMovie, result.getOrNull())
    }

    @Test
    fun `should return failure when IOException occurs`() = runBlocking {

        val movieId = 123
        coEvery { movieRepository.getMovieDetails(movieId) } throws IOException()

        val result = getMovieDetailsUseCase(movieId)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is AppError.NetworkError)
    }

    @Test
    fun `should return failure when unknown exception occurs`() = runBlocking {

        val movieId = 123
        coEvery { movieRepository.getMovieDetails(movieId) } throws RuntimeException("Unexpected error")

        val result = getMovieDetailsUseCase(movieId)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is AppError.UnknownError)
        assertEquals(
            "Unexpected error",
            (result.exceptionOrNull() as? AppError.UnknownError)?.message
        )
    }
}