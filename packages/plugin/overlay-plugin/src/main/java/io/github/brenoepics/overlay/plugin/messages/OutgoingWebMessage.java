package io.github.brenoepics.overlay.plugin.messages;

import com.eu.habbo.messages.ServerMessage;
import io.github.brenoepics.overlay.api.communication.types.ApiMessage;
import io.github.brenoepics.overlay.plugin.packets.outgoing.JavascriptCallbackComposer;

public class OutgoingWebMessage extends ApiMessage {
  protected OutgoingWebMessage(String name) {
    super(name);
  }

  public ServerMessage compose() {
    return new JavascriptCallbackComposer(this).compose();
  }
}
