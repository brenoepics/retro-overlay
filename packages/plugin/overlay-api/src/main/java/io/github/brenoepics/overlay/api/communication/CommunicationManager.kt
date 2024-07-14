package io.github.brenoepics.overlay.api.communication

import io.github.brenoepics.overlay.api.communication.types.ApiIncomingPacket
import io.github.brenoepics.overlay.api.communication.types.IncomingEvent
import io.github.brenoepics.overlay.api.utils.JsonFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Type

object CommunicationManager {
    private val log: Logger = LoggerFactory.getLogger(CommunicationManager::class.java)

    @JvmStatic
    fun <GameClient> onMessage(jsonPayload: String, sender: GameClient) {
        try {
            val heading = JsonFactory.gson.fromJson(jsonPayload, IncomingEvent::class.java)
            val packetClass = CommunicationDelegate.incomingMessages[heading.header]
            if (heading.header == null || packetClass == null) {
                log.error("Unknown packet header: {}", heading.header)
                return
            }
            val webEvent = packetClass.getDeclaredConstructor().newInstance()
            webEvent.handle(
                sender, JsonFactory.gson.fromJson(heading.data, webEvent.type as Type)
            )
        } catch (e: Exception) {
            log.error("Error while handling incoming types: {}", jsonPayload, e)
        }
    }

    @JvmStatic
    fun getIncomingSize(): Int {
        return CommunicationDelegate.incomingMessages.size
    }

    fun <T : ApiIncomingPacket<*, GameClient>, GameClient> registerMessages(messages: Map<String, Class<T>>) {
        messages.forEach { (key, clazz) ->
            CommunicationDelegate.registerMessage(key, clazz)
        }
    }
}
