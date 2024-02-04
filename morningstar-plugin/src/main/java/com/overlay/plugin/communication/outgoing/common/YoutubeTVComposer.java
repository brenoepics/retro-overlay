package com.overlay.plugin.communication.outgoing.common;

import com.google.gson.JsonPrimitive;
import com.overlay.plugin.communication.outgoing.OutgoingWebMessage;

public class YoutubeTVComposer extends OutgoingWebMessage {

    public YoutubeTVComposer(String videoId) {
        super("youtube_tv");
        this.data.add("videoId", new JsonPrimitive(videoId));
    }
}
