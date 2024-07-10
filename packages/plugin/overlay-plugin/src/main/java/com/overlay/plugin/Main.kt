package com.overlay.plugin

import com.eu.habbo.Emulator
import com.eu.habbo.habbohotel.users.Habbo
import com.eu.habbo.messages.PacketManager
import com.eu.habbo.messages.incoming.MessageHandler
import com.eu.habbo.plugin.EventHandler
import com.eu.habbo.plugin.EventListener
import com.eu.habbo.plugin.HabboPlugin
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent
import com.eu.habbo.plugin.events.users.UserEnterRoomEvent
import com.eu.habbo.plugin.events.users.UserLoginEvent
import com.overlay.api.communication.CommunicationManager
import com.overlay.plugin.messages.PacketUtils.updateSessionData
import com.overlay.plugin.packets.incoming.JavascriptCallbackEvent
import gnu.trove.map.hash.THashMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class Main : HabboPlugin(), EventListener {
    @Throws(Exception::class)
    override fun onEnable() {
        Emulator.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    @Throws(Exception::class)
    fun EmulatorLoadedEvent?.onEmulatorLoadedEvent() {
        // register incoming types
        hijackPacket(OVERLAY_PACKET_ID, JavascriptCallbackEvent::class.java)
        LOGGER.info(
            "PLUGIN - Overlay Plugin has been enabled with {} incoming types.", CommunicationManager.getIncomingSize()
        )
    }

    @EventHandler
    fun onUserEnterRoomEvent(e: UserEnterRoomEvent) {
        updateSessionData(e.habbo)
    }

    @EventHandler
    fun onUserLoginEvent(e: UserLoginEvent) {
        updateSessionData(e.habbo)
    }

    @Throws(Exception::class)
    override fun onDisable() {
        removePacket(OVERLAY_PACKET_ID)
    }

    override fun hasPermission(habbo: Habbo, s: String): Boolean {
        return false
    }

    @Throws(Exception::class)
    fun hijackPacket(incomingId: Int, customEvent: Class<out MessageHandler?>?) {
        removePacket(incomingId)
        Emulator.getGameServer().packetManager.registerHandler(incomingId, customEvent)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(Main::class.java)
        const val OVERLAY_PACKET_ID: Int = 314

        init {
            registerMessages()
        }

        @Throws(NoSuchFieldException::class, IllegalAccessException::class)
        private fun removePacket(incomingId: Int) {
            val packetManager = Emulator.getGameServer().packetManager
            val f = PacketManager::class.java.getDeclaredField("incoming")
            f.isAccessible = true
            val incoming = f[packetManager] as THashMap<*, *>

            // Removes the current arcturus handlers for these packets
            incoming.remove(incomingId)
        }
    }
}
