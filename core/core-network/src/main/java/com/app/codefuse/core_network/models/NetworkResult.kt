package com.app.codefuse.core_network.models

import com.app.codefuse.core_network.error.NetworkError

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val error: NetworkError) : NetworkResult<Nothing>()
}