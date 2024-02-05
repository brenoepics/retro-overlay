package com.overlay.plugin.communication.incoming.common;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.rooms.ForwardToRoomComposer;
import com.overlay.plugin.communication.incoming.IncomingWebMessage;

public class JoinRoomEvent extends IncomingWebMessage<JoinRoomEvent.JSONJoinRoom> {
  public JoinRoomEvent() {
    super(JSONJoinRoom.class);
  }

  @Override
  public void handle(GameClient client, JoinRoomEvent.JSONJoinRoom message) {
    Room eventRoom = Emulator.getGameEnvironment().getRoomManager().getRoom(message.roomId);

    if(eventRoom != null){
        client.sendResponse(new ForwardToRoomComposer(message.roomId).compose());
    }
  }

  static class JSONJoinRoom {
    int roomId;
  }
}
