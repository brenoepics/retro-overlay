package com.overlay.api.utils;

import com.google.gson.Gson;

public class JsonFactory {
  private JsonFactory() {}

  private static final Gson gson = new Gson();

  public static Gson getInstance() {
    return gson;
  }
}
