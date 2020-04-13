package mrstraw.titlearea.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TitleAreaTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> ValidCommands = new ArrayList<String>();
        if (args.length == 1){
            return TitleAreaCommands.Commands.getChildrenData();
        }

        CommandTreeNode nodeOfPrefix = TitleAreaCommands.Commands.getElement(args[args.length - 2]);
        if ((nodeOfPrefix == null) || (nodeOfPrefix.getChildrenData() == null)){
            return new ArrayList<String>();
        }
        for (String currentCommand: nodeOfPrefix.getChildrenData()){
            if (currentCommand.startsWith(args[args.length - 1])){
                ValidCommands.add(currentCommand);
            }
        }
        return ValidCommands;
    }
}

