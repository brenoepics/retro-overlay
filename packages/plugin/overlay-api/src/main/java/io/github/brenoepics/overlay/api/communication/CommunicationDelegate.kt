package io.github.brenoepics.overlay.api.communication

import io.github.brenoepics.overlay.api.communication.types.ApiIncomingPacket
import gnu.trove.map.hash.THashMap

object CommunicationDelegate {
    internal val incomingMessages: THashMap<String, Class<ApiIncomingPacket<*, Any?>>> = THashMap()

    /**
     * Registers a types class with a specific key for handling incoming messages. This method
     * allows the dynamic association of types with their handlers, facilitating the
     * processing of incoming messages based on their type identifier.
     *
     *
     * The method uses an unchecked cast to store the types class in the incomingMessages map.
     * This cast is necessary due to Java's type erasure and generics system but is considered safe
     * under the assumption that the method is called with correct parameters that adhere to the
     * [ApiIncomingPacket] type constraint.
     *
     *
     * It is crucial that this method is used carefully, with the caller ensuring that the type
     * parameter `T` is indeed a subclass of [ApiIncomingPacket] that matches the expected
     * types structure. Incorrect usage could lead to runtime errors, such as [ ], during types handling.
     *
     * @param key the unique identifier for the types type, used to retrieve the corresponding
     * types class for incoming types handling.
     * @param message the [Class] object of the types handler, which must extend [     ]. This class is used to instantiate and handle the incoming types.
     * @param <T> the type of the incoming types, extending [ApiIncomingPacket], which ensures
     * that only valid types can be registered.
    </T> */
    @Suppress("UNCHECKED_CAST")
    fun <T : ApiIncomingPacket<*, GameClient>, GameClient> registerMessage(key: String, message: Class<T>) {
        incomingMessages[key] = message as Class<ApiIncomingPacket<*, Any?>>
    }
}
