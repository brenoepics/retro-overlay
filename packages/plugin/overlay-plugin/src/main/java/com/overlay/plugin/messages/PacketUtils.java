package com.overlay.plugin.messages;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.users.Habbo;
import com.overlay.plugin.messages.outgoing.common.SessionDataComposer;

public class PacketUtils {
  private PacketUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static void updateSessionData(Habbo habbo) {
    if (habbo == null) return;
    GameClient client = habbo.getClient();
    if (client == null) return;

    SessionDataComposer sessionDataComposer = new SessionDataComposer(habbo.getHabboInfo());
    client.sendResponse(sessionDataComposer.compose());
  }
}
