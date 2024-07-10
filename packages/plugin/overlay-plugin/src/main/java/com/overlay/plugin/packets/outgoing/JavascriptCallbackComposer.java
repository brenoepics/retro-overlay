package com.overlay.plugin.packets.outgoing;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import com.overlay.api.communication.types.ApiMessage;
import com.overlay.api.utils.JsonFactory;

public class JavascriptCallbackComposer extends MessageComposer {
  private final ApiMessage webMessage;

  public JavascriptCallbackComposer(ApiMessage webMessage) {
    this.webMessage = webMessage;
  }

  @Override
  protected ServerMessage composeInternal() {
    this.response.init(Outgoing.NuxAlertComposer);
    // replace the / char so the string doesn't get cutoff by the swf
    String jsonMessage = JsonFactory.INSTANCE.getGson().toJson(webMessage).replace("/", "&#47;");
    this.response.appendString("habblet/open/" + jsonMessage);
    return this.response;
  }
}
