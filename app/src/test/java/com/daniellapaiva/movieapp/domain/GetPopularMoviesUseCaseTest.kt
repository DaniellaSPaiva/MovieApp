package com.daniellapaiva.movieapp.domain

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.domain.util.AppError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPopularMoviesUseCaseTest {

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private val movieRepository: MovieRepository = mockk()

    @Before
    fun setUp() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `should return success when movie list is fetched successfully`() = runBlocking {

        val expectedMovies = listOf(
            Movie(id = 1, title = "Movie 1", overview = "Overview 1", posterPath = "/path1.jpg"),
            Movie(id = 2, title = "Movie 2", overview = "Overview 2", posterPath = "/path2.jpg")
        )
        coEvery { movieRepository.getPopularMovies() } returns expectedMovies

        val result = getPopularMoviesUseCase()

        assertTrue(result.isSuccess)
        assertEquals(expectedMovies, result.getOrNull())
    }

    @Test
    fun `should return empty list when there are no popular movies`() = runTest {
        coEvery { movieRepository.getPopularMovies() } returns emptyList()

        val result = getPopularMoviesUseCase()

        assertEquals(emptyList<Movie>(), result.getOrNull())
    }

    @Test
    fun `should return failure when IOException occurs`() = runBlocking {

        coEvery { movieRepository.getPopularMovies() } throws IOException()

        val result = getPopularMoviesUseCase()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is AppError.NetworkError)
    }

    @Test
    fun `should return failure when unknown exception occurs`() = runBlocking {

        coEvery { movieRepository.getPopularMovies() } throws RuntimeException("Unexpected error")

        val result = getPopularMoviesUseCase()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is AppError.UnknownError)
        assertEquals("Unexpected error", result.exceptionOrNull()?.message)
    }
}
