package com.overlay.plugin.communication;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;
import com.overlay.plugin.communication.incoming.audio.*;
import com.overlay.plugin.communication.incoming.common.JoinRoomEvent;
import com.overlay.plugin.communication.incoming.common.MoveAvatarEvent;
import com.overlay.plugin.communication.incoming.common.RequestCreditsEvent;
import com.overlay.plugin.communication.incoming.common.RequestSpinSlotMachineEvent;
import com.overlay.plugin.utils.JsonFactory;
import gnu.trove.map.hash.THashMap;

public class CommunicationManager {
    private final THashMap<String, Class<? extends IncomingWebMessage>> incomingMessages;

    public CommunicationManager() {
        this.incomingMessages = new THashMap<>();
        initializeMessages();
    }

    public void initializeMessages() {
        this.registerMessage("join_room", JoinRoomEvent.class);
    }

    public void registerMessage(String key, Class<? extends IncomingWebMessage> message) {
        this.incomingMessages.put(key, message);
    }

    public THashMap<String, Class<? extends IncomingWebMessage>> getIncomingMessages() {
        return this.incomingMessages;
    }

    public void onMessage(String jsonPayload, GameClient sender) {
        try {
            IncomingWebMessage.JSONIncomingEvent heading = JsonFactory.getInstance().fromJson(jsonPayload, IncomingWebMessage.JSONIncomingEvent.class);
            Class<? extends IncomingWebMessage> message = this.getIncomingMessages().get(heading.header);
            IncomingWebMessage webEvent = message.getDeclaredConstructor().newInstance();
            webEvent.handle(sender, JsonFactory.getInstance().fromJson(heading.data.toString(), webEvent.type));
        } catch(Exception e) {
            Emulator.getLogging().logUndefinedPacketLine("unknown message: " + jsonPayload);
        }
    }

    public void dispose() {
        incomingMessages.clear();
    }
}
