package com.daniellapaiva.movieapp.di

import com.daniellapaiva.movieapp.data.repository.MovieRepositoryImpl
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import com.daniellapaiva.movieapp.domain.util.LanguageService
import com.daniellapaiva.movieapp.presentation.viewmodel.MovieDetailsViewModel
import com.daniellapaiva.movieapp.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { LanguageService() }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    single { GetPopularMoviesUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }

    viewModel { MovieListViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}