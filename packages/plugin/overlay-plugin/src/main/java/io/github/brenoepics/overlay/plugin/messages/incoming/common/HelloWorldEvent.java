package io.github.brenoepics.overlay.plugin.messages.incoming.common;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import io.github.brenoepics.overlay.api.communication.types.IncomingMessage;
import io.github.brenoepics.overlay.plugin.messages.incoming.IncomingPacket;

public class HelloWorldEvent extends IncomingPacket<HelloWorldEvent.IncomingData> {
  public HelloWorldEvent() {
    super(IncomingData.class);
  }

  @Override
  public void handle(GameClient client, IncomingData message) {
    if (client == null || client.getHabbo() == null) {
      return;
    }

    client.getHabbo().whisper("Hello, world! Your number is: " + message.random);
  }

  public static class IncomingData implements IncomingMessage {
    int random;
  }
}
