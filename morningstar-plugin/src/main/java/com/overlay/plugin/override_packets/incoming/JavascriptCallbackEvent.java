package com.overlay.plugin.override_packets.incoming;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.overlay.plugin.JSPlugin;

public class JavascriptCallbackEvent extends MessageHandler {
    @Override
    public void handle(){
        String payload = this.packet.readString();
        JSPlugin.getInstance().getCommunicationManager().onMessage(payload, this.client);
    }
}
