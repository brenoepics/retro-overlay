package com.overlay.plugin.communication.incoming.audio;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.overlay.plugin.JSPlugin;
import com.overlay.plugin.audio.RoomPlaylist;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;
import com.overlay.plugin.communication.outgoing.audio.AddSongComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

public class AddSongEvent extends IncomingWebMessage<AddSongEvent.JSONAddSong> {

    public AddSongEvent() {
        super(JSONAddSong.class);
    }

    @Override
    public void handle(GameClient client, JSONAddSong message) {
        Room currentRoom = client.getHabbo().getHabboInfo().getCurrentRoom();
        if(currentRoom == null)
            return;
        if(currentRoom.hasRights(client.getHabbo())) {
            RoomPlaylist playlist= JSPlugin.getInstance().getRoomAudioManager().getPlaylistForRoom(currentRoom.getId());
            RoomPlaylist.YoutubeVideo song = new RoomPlaylist.YoutubeVideo(message.name, message.videoId, message.channel);
            playlist.addSong(song);
            AddSongComposer addSongComposer = new AddSongComposer(song);
            currentRoom.sendComposer(new JavascriptCallbackComposer(addSongComposer).compose());
        }
    }

    public static class JSONAddSong {
        public String name;
        public String videoId;
        public String channel;
    }
}
