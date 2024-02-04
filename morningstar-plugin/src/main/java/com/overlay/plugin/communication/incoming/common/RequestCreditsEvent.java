package com.overlay.plugin.communication.incoming.common;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.outgoing.users.UserCreditsComposer;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;
import com.overlay.plugin.communication.outgoing.common.UpdateCreditsComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

public class RequestCreditsEvent extends IncomingWebMessage<RequestCreditsEvent.JSONRequestCreditsEvent> {

    public RequestCreditsEvent() {
        super(JSONRequestCreditsEvent.class);
    }

    @Override
    public void handle(GameClient client, JSONRequestCreditsEvent message) {
        client.sendResponse(new UserCreditsComposer(client.getHabbo()));
        UpdateCreditsComposer creditsComposer = new UpdateCreditsComposer(client.getHabbo().getHabboInfo().getCredits());
        client.sendResponse(new JavascriptCallbackComposer(creditsComposer));
    }

    static class JSONRequestCreditsEvent {
        boolean idk;
    }
}
