package com.overlay.plugin.communication.outgoing.audio;

import com.google.gson.JsonPrimitive;
import com.overlay.plugin.communication.outgoing.OutgoingWebMessage;

public class PlaySongComposer extends OutgoingWebMessage {
    public PlaySongComposer(int index) {
        super("play_song");
        this.data.add("index", new JsonPrimitive(index));
    }
}
