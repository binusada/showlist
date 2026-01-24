package com.app.codefuse.core_network.error

sealed class NetworkException : Exception() {

    class NoInternet : NetworkException()
    class Timeout : NetworkException()
    class Unauthorized : NetworkException()
    class Forbidden : NetworkException()
    class NotFound : NetworkException()

    class ServerError(val code: Int) : NetworkException()

    class Unknown(cause: Throwable) : NetworkException() {
        init {
            initCause(cause)
        }
    }
}