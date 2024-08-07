package io.github.brenoepics.overlay.plugin.messages.incoming.common;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.outgoing.rooms.ForwardToRoomComposer;
import io.github.brenoepics.overlay.api.communication.types.IncomingMessage;
import io.github.brenoepics.overlay.plugin.messages.incoming.IncomingPacket;

public class JoinRoomEvent extends IncomingPacket<JoinRoomEvent.JSONJoinRoom> {
  public JoinRoomEvent() {
    super(JSONJoinRoom.class);
  }

  @Override
  public void handle(GameClient client, JSONJoinRoom message) {
    Room eventRoom = Emulator.getGameEnvironment().getRoomManager().getRoom(message.roomId);
    if (eventRoom != null) {
      client.sendResponse(new ForwardToRoomComposer(message.roomId).compose());
    }
  }

  public static class JSONJoinRoom implements IncomingMessage {
    int roomId;
  }
}
