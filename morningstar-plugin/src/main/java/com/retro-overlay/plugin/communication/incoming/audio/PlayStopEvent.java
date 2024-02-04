package com.skeletor.plugin.javascript.communication.incoming.audio;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.skeletor.plugin.javascript.JSPlugin;
import com.skeletor.plugin.javascript.audio.RoomAudioManager;
import com.skeletor.plugin.javascript.audio.RoomPlaylist;
import com.skeletor.plugin.javascript.communication.incoming.IncomingWebMessage;
import com.skeletor.plugin.javascript.communication.outgoing.audio.PlayStopComposer;
import com.skeletor.plugin.javascript.override_packets.outgoing.JavascriptCallbackComposer;

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
