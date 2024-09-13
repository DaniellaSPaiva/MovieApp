package com.daniellapaiva.movieapp.data

import com.daniellapaiva.movieapp.data.remote.api.MovieApiService
import com.daniellapaiva.movieapp.data.repository.MovieRepositoryImpl
import com.daniellapaiva.movieapp.domain.model.Movie
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieRepositoryImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiService: MovieApiService
    private lateinit var movieRepository: MovieRepositoryImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val client = OkHttpClient.Builder().build()

        movieApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

        movieRepository = MovieRepositoryImpl(movieApiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should return movie details from API`() = runBlocking {
        val mockJsonResponse = """
            {
                "id": 123,
                "title": "Inception",
                "overview": "A mind-bending thriller.",
                "poster_path": "/inception_poster.jpg"
            }
        """

        val expectedMovie = Movie(
            id = 123,
            title = "Inception",
            overview = "A mind-bending thriller.",
            posterPath = "/inception_poster.jpg"
        )

        val mockResponse = MockResponse()
            .setBody(mockJsonResponse)
            .addHeader("Content-Type", "application/json")
        mockWebServer.enqueue(mockResponse)

        val actualMovie = movieRepository.getMovieDetails(123)

        assertEquals(expectedMovie, actualMovie)
    }
}