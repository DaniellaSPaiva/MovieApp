package com.daniellapaiva.movieapp.domain.util

import java.util.Locale

class LanguageService {
    fun getLanguageForApi(): String {
        return when (Locale.getDefault().language) {
            "pt" -> "pt-BR"
            else -> "en-US"
        }
    }
}