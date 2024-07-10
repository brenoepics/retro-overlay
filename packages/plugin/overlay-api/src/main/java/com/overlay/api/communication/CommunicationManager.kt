package com.overlay.api.communication

import com.eu.habbo.habbohotel.gameclients.GameClient
import com.overlay.api.communication.types.IncomingEvent
import com.overlay.api.utils.JsonFactory
import java.lang.reflect.Type

object CommunicationManager {
    private val log: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(CommunicationManager::class.java)

    @JvmStatic
    fun onMessage(jsonPayload: String, sender: GameClient) {
        try {
            val heading = JsonFactory.getInstance().fromJson(jsonPayload, IncomingEvent::class.java)
            val packetClass = CommunicationDelegate.incomingMessages[heading.header]
            if (heading.header == null || packetClass == null) {
                log.error("Unknown packet header: {}", heading.header)
                return
            }
            val webEvent = packetClass.getDeclaredConstructor().newInstance()
            webEvent.handle(
                sender, JsonFactory.getInstance().fromJson(heading.data, webEvent.type as Type)
            )
        } catch (e: Exception) {
            log.error("Error while handling incoming types: {}", jsonPayload, e)
        }
    }

    @JvmStatic
    fun getIncomingSize(): Int {
        return CommunicationDelegate.incomingMessages.size
    }
}
