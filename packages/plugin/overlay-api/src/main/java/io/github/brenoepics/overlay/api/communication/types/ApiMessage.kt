package io.github.brenoepics.overlay.api.communication.types

import com.google.gson.JsonObject

abstract class ApiMessage protected constructor(val header: String) {
    @JvmField
    val data: JsonObject = JsonObject()
}
