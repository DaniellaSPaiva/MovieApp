package com.daniellapaiva.movieapp.domain.util

import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException

suspend fun <T> safeCall(action: suspend () -> T): Result<T> {
    return try {
        Result.success(action())
    } catch (e: IOException) {
        Result.failure(AppError.NetworkError)
    } catch (e: TimeoutCancellationException) {
        Result.failure(AppError.TimeoutError)
    } catch (e: Exception) {
        Result.failure(AppError.UnknownError(e.message))
    }
}