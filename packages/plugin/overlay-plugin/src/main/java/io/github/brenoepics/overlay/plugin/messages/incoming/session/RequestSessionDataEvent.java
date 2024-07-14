package io.github.brenoepics.overlay.plugin.messages.incoming.session;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import io.github.brenoepics.overlay.api.communication.types.IncomingMessage;
import io.github.brenoepics.overlay.plugin.messages.PacketUtils;
import io.github.brenoepics.overlay.plugin.messages.incoming.IncomingPacket;

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
