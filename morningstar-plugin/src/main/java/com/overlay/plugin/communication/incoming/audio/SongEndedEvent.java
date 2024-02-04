package com.overlay.plugin.communication.incoming.audio;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.overlay.plugin.JSPlugin;
import com.overlay.plugin.audio.RoomPlaylist;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;
import com.overlay.plugin.communication.outgoing.audio.PlaySongComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

public class SongEndedEvent extends IncomingWebMessage<SongEndedEvent.JSONSongEndedEvent> {

    public SongEndedEvent() {
        super(JSONSongEndedEvent.class);
    }

    @Override
    public void handle(GameClient client, JSONSongEndedEvent message) {
        Room room = client.getHabbo().getHabboInfo().getCurrentRoom();
        if(room == null)
            return;

        if(room.hasRights(client.getHabbo())) {
            RoomPlaylist playlist = JSPlugin.getInstance().getRoomAudioManager().getPlaylistForRoom(room.getId());
            if(playlist.getCurrentIndex() == message.currentIndex) {
                playlist.nextSong();
                playlist.setPlaying(true);
                PlaySongComposer playSongComposer = new PlaySongComposer(playlist.getCurrentIndex());
                room.sendComposer(new JavascriptCallbackComposer(playSongComposer).compose());
                room.sendComposer(playlist.getNowPlayingBubbleAlert().compose());
            }
        }
    }

    static class JSONSongEndedEvent {
        public int currentIndex;
    }
}
