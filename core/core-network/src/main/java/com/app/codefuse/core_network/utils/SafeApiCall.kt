package com.app.codefuse.core_network.utils

import com.app.codefuse.core_network.error.ErrorMapper
import com.app.codefuse.core_network.error.NetworkError
import com.app.codefuse.core_network.models.NetworkResult
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    call: suspend () -> T
): NetworkResult<T> = try {
            NetworkResult.Success(call())
        } catch (e: HttpException) {
            NetworkResult.Error(ErrorMapper.map(e))
        } catch (e: IOException) {
            NetworkResult.Error(NetworkError.Unknown(e))
        } catch (e: Exception) {
            NetworkResult.Error(NetworkError.Unknown(e))
        }