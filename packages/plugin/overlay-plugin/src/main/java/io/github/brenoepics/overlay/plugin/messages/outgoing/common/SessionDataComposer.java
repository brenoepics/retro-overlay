package io.github.brenoepics.overlay.plugin.messages.outgoing.common;

import com.eu.habbo.habbohotel.users.HabboInfo;
import com.google.gson.JsonPrimitive;
import io.github.brenoepics.overlay.plugin.messages.OutgoingWebMessage;

public class SessionDataComposer extends OutgoingWebMessage {
  public SessionDataComposer(HabboInfo info) {
    super("session_data");
    insertData(info.getId(), info.getUsername(), info.getLook(), info.getCredits());
  }

  public SessionDataComposer(int id, String username, String look, int credits) {
    super("session_data");
    insertData(id, username, look, credits);
  }

  private void insertData(int id, String username, String look, int credits) {
    this.data.add("id", new JsonPrimitive(id));
    this.data.add("username", new JsonPrimitive(username));
    this.data.add("look", new JsonPrimitive(look));
    this.data.add("credits", new JsonPrimitive(credits));
  }
}
