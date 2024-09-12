package com.daniellapaiva.movieapp.application

import android.app.Application
import com.daniellapaiva.movieapp.di.appModule
import com.daniellapaiva.movieapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(networkModule, appModule)
        }
    }
}