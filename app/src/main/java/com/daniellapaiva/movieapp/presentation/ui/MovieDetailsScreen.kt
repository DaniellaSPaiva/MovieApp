package com.daniellapaiva.movieapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.presentation.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int?,
    viewModel: MovieViewModel = koinViewModel()
) {
    movieId?.let {
        LaunchedEffect(movieId) {
            viewModel.fetchMovieDetails(movieId)
        }
    }

    val movieDetails by viewModel.movieDetails.collectAsState()

    movieDetails?.let { movie ->
        MovieDetailsContent(movie = movie)
    }
}

@Composable
fun MovieDetailsContent(movie: Movie) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
            contentDescription = movie.title,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsContent(
        Movie(
            id = 1,
            title = "The Movie Title",
            overview = "This is a detailed overview of the movie with more information.",
            posterPath = "/path_to_image.jpg"
        )
    )
}