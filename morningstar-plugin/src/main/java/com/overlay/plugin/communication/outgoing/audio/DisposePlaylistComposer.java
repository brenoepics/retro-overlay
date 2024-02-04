package com.overlay.plugin.communication.outgoing.audio;

import com.overlay.plugin.communication.outgoing.OutgoingWebMessage;

public class DisposePlaylistComposer extends OutgoingWebMessage {
    public DisposePlaylistComposer() {
        super("dispose_playlist");
    }
}
