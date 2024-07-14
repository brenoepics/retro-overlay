package io.github.brenoepics.overlay.api.communication.types

open class ApiIncomingMap<GameClient> {
    private val messages = hashMapOf<String, Class<ApiIncomingPacket<*, GameClient>>>()
    fun getMessages(): HashMap<String, Class<ApiIncomingPacket<*, GameClient>>> {
        return messages
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : ApiIncomingPacket<*, GameClient>> addMessage(key: String, clazz: Class<T>) {
        messages[key] = clazz as Class<ApiIncomingPacket<*, GameClient>>
    }
}