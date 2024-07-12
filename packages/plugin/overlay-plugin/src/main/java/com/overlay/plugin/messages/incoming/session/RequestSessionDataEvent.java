package com.overlay.plugin.messages.incoming.session;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.overlay.api.communication.types.IncomingMessage;
import com.overlay.api.communication.types.IncomingPacket;
import com.overlay.plugin.messages.PacketUtils;

public class RequestSessionDataEvent extends IncomingPacket<RequestSessionDataEvent.IncomingData> {
  public RequestSessionDataEvent() {
    super(IncomingData.class);
  }

  @Override
  public void handle(GameClient client, IncomingData message) {
    if (client == null || client.getHabbo() == null) {
      return;
    }

    PacketUtils.updateSessionData(client.getHabbo());
  }

  public static class IncomingData implements IncomingMessage {
    // Empty
  }
}
