package com.app.codefuse.core_network.error

import kotlinx.serialization.SerializationException
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorMapper {

    fun map(throwable: Throwable): NetworkError {
        return when (throwable) {
            is UnknownHostException -> NetworkError.NoInternet
            is IOException -> NetworkError.NoInternet // --- DNS issue
            is SocketTimeoutException -> NetworkError.Timeout
            is HttpException -> mapHttpException(throwable) // --- HTTP errors ---
            is SerializationException -> NetworkError.Unknown(throwable)  // --- Serialization issues ---
            else -> NetworkError.Unknown(throwable)
        }
    }

    private fun mapHttpException(exception: HttpException): NetworkError {
        return when (exception.code()) {
            401 -> NetworkError.Unauthorized
            403 -> NetworkError.Forbidden
            404 -> NetworkError.NotFound
            in 500..599 -> NetworkError.ServerError(
                code = exception.code(),
                message = exception.message()
            )
            else -> NetworkError.ServerError(
                code = exception.code(),
                message = exception.message()
            )
        }
    }
}