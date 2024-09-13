package com.daniellapaiva.movieapp.domain

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private val movieRepository: MovieRepository = mockk()

    @Before
    fun setUp() {
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    }

    @Test
    fun `should return movie details when movieId is provided`() = runBlocking {
        val movieId = 123
        val expectedMovie = Movie(
            id = 123,
            title = "Example Movie",
            overview = "An example movie overview",
            posterPath = "/path_to_poster.jpg"
        )

        coEvery { movieRepository.getMovieDetails(movieId) } returns expectedMovie

        val result = getMovieDetailsUseCase(movieId)

        assertEquals(expectedMovie, result)
    }
}