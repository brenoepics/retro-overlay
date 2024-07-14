package io.github.brenoepics.overlay.plugin.packets.incoming;

import com.eu.habbo.messages.incoming.MessageHandler;
import io.github.brenoepics.overlay.api.communication.CommunicationManager;

public class JavascriptCallbackEvent extends MessageHandler {
  @Override
  public void handle() {
    String payload = this.packet.readString();
    CommunicationManager.onMessage(payload, this.client);
  }
}
