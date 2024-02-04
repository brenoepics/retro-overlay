package com.skeletor.plugin.javascript.communication.outgoing.audio;

import com.google.gson.JsonPrimitive;
import com.skeletor.plugin.javascript.communication.outgoing.OutgoingWebMessage;

public class RemoveSongComposer extends OutgoingWebMessage {

    public RemoveSongComposer(int index) {
        super("remove_song");
        this.data.add("index", new JsonPrimitive(index));
    }
}
