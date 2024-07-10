package com.overlay.plugin.messages;

import com.eu.habbo.messages.ServerMessage;
import com.overlay.api.communication.types.ApiMessage;
import com.overlay.plugin.packets.outgoing.JavascriptCallbackComposer;

public class OutgoingWebMessage extends ApiMessage {
  protected OutgoingWebMessage(String name) {
    super(name);
  }

  public ServerMessage compose() {
    return new JavascriptCallbackComposer(this).compose();
  }
}
