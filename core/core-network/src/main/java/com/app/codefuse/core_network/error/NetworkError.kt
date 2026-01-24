package com.app.codefuse.core_network.error

sealed class NetworkError {

    object NoInternet : NetworkError()
    object Timeout : NetworkError()
    object Unauthorized : NetworkError()
    object Forbidden : NetworkError()
    object NotFound : NetworkError()

    data class ServerError(val code: Int, val message: String?) : NetworkError()
    data class Unknown(val throwable: Throwable) : NetworkError()
}