package io.github.brenoepics.overlay.api.communication.types

import com.google.gson.JsonObject

class IncomingEvent {
    var header: String? = null
    var data: JsonObject? = null
}
