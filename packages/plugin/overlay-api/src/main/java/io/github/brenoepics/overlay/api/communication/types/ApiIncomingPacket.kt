package io.github.brenoepics.overlay.api.communication.types

abstract class ApiIncomingPacket<T : IncomingMessage, GameClient> protected constructor(val type: Class<T>) {
    abstract fun handle(client: GameClient?, message: T)
}
