package com.overlay.api.communication.types

import com.eu.habbo.habbohotel.gameclients.GameClient

abstract class IncomingPacket<T : IncomingMessage?> protected constructor(val type: Class<T>) {
    abstract fun handle(client: GameClient?, message: T)
}
