package com.overlay.plugin.utils;

import com.eu.habbo.Emulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRegister {

    public static void databaseRegister(){
      registerTexts();
      checkDatabase();
    }

    private static void registerTexts(){
        newEventAlertTexts();
    }

    private static void newEventAlertTexts(){
      Emulator.getTexts().register("commands.keys.cmd_neweventalert","eventalert");
      Emulator.getTexts().register("commands.description.cmd_neweventalert",":eventalert [event_title]");
      Emulator.getTexts().register("commands.warning.cmd_neweventalert", "You did not specify a title for the event.");
    }


  private static void checkDatabase() {
    boolean reloadPermissions = false;
    reloadPermissions = registerPermission("cmd_neweventalert", "'0', '1', '2'", "0", reloadPermissions);
    if (reloadPermissions)
    {
      Emulator.getGameEnvironment().getPermissionsManager().reload();
    }
  }

    private static boolean registerPermission(String name, String options, String defaultValue, boolean defaultReturn)
    {
      try (Connection connection = Emulator.getDatabase().getDataSource().getConnection())
      {
        try (PreparedStatement statement = connection.prepareStatement("ALTER TABLE  `permissions` ADD  `" + name +"` ENUM(  " + options + " ) NOT NULL DEFAULT  '" + defaultValue + "'"))
        {
          statement.execute();
          return true;
        }
      }
      catch (SQLException ignored)
      {}

      return defaultReturn;
    }
}
