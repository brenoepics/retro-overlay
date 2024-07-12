package com.overlay.plugin

import com.overlay.api.communication.types.APIMessages
import com.overlay.plugin.messages.incoming.common.HelloWorldEvent
import com.overlay.plugin.messages.incoming.common.JoinRoomEvent
import com.overlay.plugin.messages.incoming.session.RequestSessionDataEvent

object PluginMessageRegistrar : APIMessages() {
    init {
        addMessage("join_room", JoinRoomEvent::class.java)
        addMessage("request_session_data", RequestSessionDataEvent::class.java)
        addMessage("client_hello", HelloWorldEvent::class.java)
    }
}