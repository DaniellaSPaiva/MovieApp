package com.daniellapaiva.movieapp.designsystem.components

import androidx.compose.runtime.Composable
import com.daniellapaiva.movieapp.presentation.common.UIState

@Composable
fun <T> UIStateHandler(
    uiState: UIState<T>,
    successContent: @Composable (T) -> Unit,
    onRetry: () -> Unit = {}
) {
    when (uiState) {
        is UIState.Loading -> {
            LoadingScreen()
        }
        is UIState.Success -> {
            successContent(uiState.data)
        }
        is UIState.Error -> {
            val errorMessage = uiState.error.message ?: "Unknown error"
            ErrorScreen(message = errorMessage, onRetry = onRetry)
        }
    }
}
