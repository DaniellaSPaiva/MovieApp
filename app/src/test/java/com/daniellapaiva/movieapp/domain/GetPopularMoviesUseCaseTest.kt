package com.daniellapaiva.movieapp.domain

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetPopularMoviesUseCaseTest {

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private val movieRepository: MovieRepository = mockk()

    @Before
    fun setUp() {
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `should return list of popular movies when called`() = runTest {
        val expectedMovies = listOf(
            Movie(id = 1, title = "Movie 1", overview = "Overview 1", posterPath = "/path1.jpg"),
            Movie(id = 2, title = "Movie 2", overview = "Overview 2", posterPath = "/path2.jpg")
        )

        coEvery { movieRepository.getPopularMovies() } returns expectedMovies

        val result = getPopularMoviesUseCase()

        assertEquals(expectedMovies, result)
    }

    @Test
    fun `should return empty list when there are no popular movies`() = runTest {
        coEvery { movieRepository.getPopularMovies() } returns emptyList()

        val result = getPopularMoviesUseCase()

        assertEquals(emptyList<Movie>(), result)
    }
}
