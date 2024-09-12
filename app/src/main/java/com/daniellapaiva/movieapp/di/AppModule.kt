package com.daniellapaiva.movieapp.di

import com.daniellapaiva.movieapp.data.repository.MovieRepositoryImpl
import com.daniellapaiva.movieapp.domain.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.presentation.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single { GetPopularMoviesUseCase(get()) }

    viewModel { MovieViewModel(get()) }
}