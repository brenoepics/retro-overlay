package com.overlay.plugin.communication.outgoing.eventalert;

import com.google.gson.JsonPrimitive;
import com.overlay.plugin.communication.outgoing.OutgoingWebMessage;

public class EventAlertComposer extends OutgoingWebMessage {
  public EventAlertComposer(String staffUsername, String staffLook, String playerUsername, String eventTitle, int roomId) {
    super("eventAlert");
    this.data.add("staffUsername", new JsonPrimitive(staffUsername));
    this.data.add("staffLook", new JsonPrimitive(staffLook));
    this.data.add("playerUsername", new JsonPrimitive(playerUsername));
    this.data.add("eventTitle", new JsonPrimitive(eventTitle));
    this.data.add("roomId", new JsonPrimitive(roomId));
  }
}
