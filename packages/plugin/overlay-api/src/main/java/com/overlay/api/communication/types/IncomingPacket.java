package com.overlay.api.communication.types;

import com.eu.habbo.habbohotel.gameclients.GameClient;

public abstract class IncomingPacket<T extends IncomingMessage> {
  public final Class<T> type;

  protected IncomingPacket(Class<T> type) {
    this.type = type;
  }

  public abstract void handle(GameClient client, T message);
}
