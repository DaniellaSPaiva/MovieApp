package com.daniellapaiva.movieapp.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

private const val LANGUAGE = "language"

class LanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(LANGUAGE, getLanguageForApi())
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private fun getLanguageForApi(): String {
        return when (Locale.getDefault().language) {
            "pt" -> "pt-BR"
            else -> "en-US"
        }
    }
}