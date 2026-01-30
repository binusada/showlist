package com.app.codefuse.core_network.client

import com.app.codefuse.core_network.config.NetworkConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OkHttpProvider @Inject constructor (
    private val interceptors: Set<Interceptor>
) {
    fun create(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(NetworkConfig.DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConfig.DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .apply { interceptors.forEach { addInterceptor(it) } }
            .build()
}