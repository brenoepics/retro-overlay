package io.github.brenoepics.overlay.plugin

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
import io.github.brenoepics.overlay.api.communication.CommunicationDelegate
import io.github.brenoepics.overlay.api.communication.CommunicationManager
import io.github.brenoepics.overlay.plugin.messages.PacketUtils
import io.github.brenoepics.overlay.plugin.packets.incoming.JavascriptCallbackEvent
import gnu.trove.map.hash.THashMap
import io.github.brenoepics.overlay.plugin.events.MorningstarListener
import io.github.brenoepics.overlay.plugin.messages.PacketUtils.Companion.removePacket
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class Main : HabboPlugin() {
    @Throws(Exception::class)
    override fun onEnable() {
        Emulator.getPluginManager().registerEvents(this, MorningstarListener())
    }

    @Throws(Exception::class)
    override fun onDisable() {
        removePacket(PacketUtils.OVERLAY_PACKET_ID)
    }

    override fun hasPermission(habbo: Habbo, s: String): Boolean {
        return false
    }

    companion object {
        init {
            CommunicationManager.registerMessages(PluginMessageRegistrar.getMessages())
        }
    }
}
