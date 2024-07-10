package com.overlay.api.communication.types;

import com.google.gson.JsonObject;

public abstract class ApiMessage {
  public final String header;
  public final JsonObject data = new JsonObject();

  protected ApiMessage(String name) {
    this.header = name;
  }
}
