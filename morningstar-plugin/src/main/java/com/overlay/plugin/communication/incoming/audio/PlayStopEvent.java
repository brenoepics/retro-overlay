package com.overlay.plugin.communication.incoming.audio;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.overlay.plugin.JSPlugin;
import com.overlay.plugin.audio.RoomAudioManager;
import com.overlay.plugin.audio.RoomPlaylist;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;
import com.overlay.plugin.communication.outgoing.audio.PlayStopComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

public class PlayStopEvent extends IncomingWebMessage<PlayStopEvent.JSONPlayStopEvent> {
    public PlayStopEvent() {
        super(JSONPlayStopEvent.class);
    }

    @Override
    public void handle(GameClient client, JSONPlayStopEvent message) {
        Room room = client.getHabbo().getHabboInfo().getCurrentRoom();
        if(room == null)
            return;

        if(room.hasRights(client.getHabbo())) {
            RoomPlaylist roomPlaylist = JSPlugin.getInstance().getRoomAudioManager().getPlaylistForRoom(room.getId());
            roomPlaylist.setPlaying(message.play);
            room.sendComposer(new JavascriptCallbackComposer(new PlayStopComposer(message.play)).compose());
            if(message.play) {
                room.sendComposer(roomPlaylist.getNowPlayingBubbleAlert().compose());
            }
        }
    }


    public static class JSONPlayStopEvent {
        public boolean play;
    }
}
