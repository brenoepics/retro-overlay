package com.overlay.plugin.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.RoomChatMessageBubbles;
import com.eu.habbo.habbohotel.users.Habbo;
import com.overlay.plugin.communication.outgoing.eventalert.EventAlertComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

import java.util.Map;

public class EventAlertCommand extends Command {

  public EventAlertCommand(String permission, String[] keys) {
    super(permission, keys);
  }

  @Override
  public boolean handle(GameClient gameClient, String[] strings) throws Exception {
    Habbo staff = gameClient.getHabbo();
      if(staff.getHabboInfo().getCurrentRoom() != null && strings.length >= 2){
          StringBuilder builder = new StringBuilder();

        for (int i = 1; i < strings.length; i++) {
          builder.append(strings[i]);
          builder.append(" ");
        }

        for (Map.Entry<Integer, Habbo> set : Emulator.getGameEnvironment().getHabboManager().getOnlineHabbos().entrySet()) {
          Habbo habbo = set.getValue();
          /*if(habbo.getHabboInfo().getId() != staff.getHabboInfo().getId()){
            if (habbo.getHabboStats().blockStaffAlerts)
              continue;

            EventAlertComposer eventAlertComposer = new EventAlertComposer(staff.getHabboInfo().getUsername(),staff.getHabboInfo().getLook(),habbo.getHabboInfo().getUsername(),builder.toString());
            habbo.getClient().sendResponse(new JavascriptCallbackComposer(eventAlertComposer).compose());
            staff.whisper(Emulator.getTexts().getValue("commands.success.cmd_neweventalert"), RoomChatMessageBubbles.FRANK);
          }
          */
          if (habbo.getHabboStats().blockStaffAlerts)
            continue;

          EventAlertComposer eventAlertComposer = new EventAlertComposer(staff.getHabboInfo().getUsername(),staff.getHabboInfo().getLook(),habbo.getHabboInfo().getUsername(),builder.toString(),staff.getHabboInfo().getCurrentRoom().getId());
          habbo.getClient().sendResponse(new JavascriptCallbackComposer(eventAlertComposer).compose());
          staff.whisper(Emulator.getTexts().getValue("commands.success.cmd_neweventalert"), RoomChatMessageBubbles.FRANK);
        }

      } else {
          staff.whisper(Emulator.getTexts().getValue("commands.warning.cmd_neweventalert"), RoomChatMessageBubbles.FRANK);
      }
    return true;
  }
}
