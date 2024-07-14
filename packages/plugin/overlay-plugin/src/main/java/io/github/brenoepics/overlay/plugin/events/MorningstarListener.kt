package io.github.brenoepics.overlay.plugin.events

import com.eu.habbo.plugin.EventHandler
import com.eu.habbo.plugin.EventListener
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent
import com.eu.habbo.plugin.events.users.UserEnterRoomEvent
import com.eu.habbo.plugin.events.users.UserLoginEvent
import io.github.brenoepics.overlay.api.communication.CommunicationManager
import io.github.brenoepics.overlay.plugin.messages.PacketUtils
import io.github.brenoepics.overlay.plugin.messages.PacketUtils.Companion.OVERLAY_PACKET_ID
import io.github.brenoepics.overlay.plugin.packets.incoming.JavascriptCallbackEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MorningstarListener : EventListener {
    private val log: Logger = LoggerFactory.getLogger(MorningstarListener::class.java)

    @EventHandler
    fun EmulatorLoadedEvent?.onReady() {
        PacketUtils.hijackPacket(OVERLAY_PACKET_ID, JavascriptCallbackEvent::class.java)
        log.info(
            "Overlay Plugin has been enabled with {} incoming types.", CommunicationManager.getIncomingSize()
        )
    }

    @EventHandler
    fun UserEnterRoomEvent.onUserEnterRoomEvent() {
        PacketUtils.updateSessionData(habbo)
    }

    @EventHandler
    fun UserLoginEvent.onUserLoginEvent() {
        PacketUtils.updateSessionData(habbo)
    }
}