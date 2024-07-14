package io.github.brenoepics.overlay.plugin.messages.incoming

import com.eu.habbo.habbohotel.gameclients.GameClient
import io.github.brenoepics.overlay.api.communication.types.ApiIncomingPacket
import io.github.brenoepics.overlay.api.communication.types.IncomingMessage

abstract class IncomingPacket<T : IncomingMessage>(type: Class<T>) : ApiIncomingPacket<T, GameClient>(type)