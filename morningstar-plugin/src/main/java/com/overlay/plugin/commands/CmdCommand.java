package com.overlay.plugin.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.overlay.plugin.communication.outgoing.common.CommandsComposer;
import com.overlay.plugin.override_packets.outgoing.JavascriptCallbackComposer;

import java.util.List;

public class CmdCommand extends Command {
    public CmdCommand() {
        super("cmd_commands", new String[]{"cmd", "command", "cmds", "commands"});
    }

    @Override
    public boolean handle(GameClient gameClient, String[] strings) throws Exception {
        List<Command> commands = Emulator.getGameEnvironment().getCommandHandler().getCommandsForRank(gameClient.getHabbo().getHabboInfo().getRank().getId());
        CommandsComposer commandsComposer = new CommandsComposer(commands);
        gameClient.sendResponse(new JavascriptCallbackComposer(commandsComposer));
        return true;
    }
}
