package io.github.brenoepics.overlay.plugin.messages

import com.eu.habbo.Emulator
import com.eu.habbo.habbohotel.users.Habbo
import com.eu.habbo.messages.PacketManager
import com.eu.habbo.messages.incoming.MessageHandler
import gnu.trove.map.hash.THashMap
import io.github.brenoepics.overlay.plugin.messages.outgoing.common.SessionDataComposer

/**
 * A utility class for packet related operations
 */
class PacketUtils {
    companion object {
        const val OVERLAY_PACKET_ID: Int = 314

        /**
         * Updates the session data for the habbo
         *
         * @param habbo the habbo to update the session data
         */
        @JvmStatic
        fun updateSessionData(habbo: Habbo?) {
            if (habbo == null) return
            val client = habbo.client ?: return

            val sessionDataComposer = SessionDataComposer(habbo.habboInfo)
            client.sendResponse(sessionDataComposer.compose())
        }

        /**
         * Hijacks the current handlers for the incoming types id
         *
         * @param incomingId the incoming id to hijack
         * @param customEvent the custom event to handle the incoming id
         */
        @Throws(Exception::class)
        fun hijackPacket(incomingId: Int, customEvent: Class<out MessageHandler?>?) {
            removePacket(incomingId)
            Emulator.getGameServer().packetManager.registerHandler(incomingId, customEvent)
        }

        /**
         * Removes the current handlers for the incoming types id
         *
         * @param incomingId the incoming id to remove the handlers
         */
        @Throws(NoSuchFieldException::class, IllegalAccessException::class)
        fun removePacket(incomingId: Int) {
            val packetManager = Emulator.getGameServer().packetManager
            val f = PacketManager::class.java.getDeclaredField("incoming")
            f.isAccessible = true
            val incoming = f[packetManager] as THashMap<*, *>
            incoming.remove(incomingId)
        }
    }
}
