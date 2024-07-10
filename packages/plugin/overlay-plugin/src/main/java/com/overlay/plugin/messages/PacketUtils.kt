package com.overlay.plugin.messages

import com.eu.habbo.habbohotel.users.Habbo
import com.overlay.plugin.messages.outgoing.common.SessionDataComposer

object PacketUtils {
    fun updateSessionData(habbo: Habbo?) {
        if (habbo == null) return
        val client = habbo.client ?: return

        val sessionDataComposer = SessionDataComposer(habbo.habboInfo)
        client.sendResponse(sessionDataComposer.compose())
    }
}
