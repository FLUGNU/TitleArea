package mrstraw.titlearea.commands;

import org.bukkit.command.CommandSender;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandModify {

    public CommandModify(CommandSender sender, String[] args) {
        sender.sendMessage(sendTitleArea("mange ma bite - set"));
    }

}
