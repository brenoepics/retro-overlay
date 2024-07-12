package com.overlay.api.communication.types

open class APIMessages {
    private val messages = hashMapOf<String, Class<IncomingPacket<*>>>()
    fun getMessages(): HashMap<String, Class<IncomingPacket<*>>> {
        return messages
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : IncomingPacket<*>> addMessage(key: String, clazz: Class<T>) {
        messages[key] = clazz as Class<IncomingPacket<*>>
    }
}