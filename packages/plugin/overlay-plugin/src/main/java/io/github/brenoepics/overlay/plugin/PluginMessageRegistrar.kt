package io.github.brenoepics.overlay.plugin

import com.eu.habbo.habbohotel.gameclients.GameClient
import io.github.brenoepics.overlay.api.communication.types.ApiIncomingMap
import io.github.brenoepics.overlay.plugin.messages.incoming.common.HelloWorldEvent
import io.github.brenoepics.overlay.plugin.messages.incoming.common.JoinRoomEvent
import io.github.brenoepics.overlay.plugin.messages.incoming.session.RequestSessionDataEvent

object PluginMessageRegistrar : ApiIncomingMap<GameClient>() {
    init {
        addMessage("join_room", JoinRoomEvent::class.java)
        addMessage("request_session_data", RequestSessionDataEvent::class.java)
        addMessage("client_hello", HelloWorldEvent::class.java)
    }
}