package com.overlay.plugin

import com.overlay.api.communication.CommunicationDelegate
import com.overlay.plugin.messages.incoming.common.JoinRoomEvent

fun registerMessages() {
    CommunicationDelegate.registerMessage("join_room", JoinRoomEvent::class.java)
    CommunicationDelegate.registerMessage("join_room2", JoinRoomEvent::class.java)
}
