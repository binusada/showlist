package com.app.codefuse.core_network.serialiser

import kotlinx.serialization.json.Json

object JsonProvider {
    val json: Json = Json {
        ignoreUnknownKeys = true   // forward compatibility
        explicitNulls = false
        isLenient = true
        encodeDefaults = true
    }
}