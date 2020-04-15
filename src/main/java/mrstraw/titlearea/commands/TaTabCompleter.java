package mrstraw.titlearea.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class TaTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> ValidCommands = new ArrayList<>();
        if (args.length == 1){
            return TaCommands.Commands.getChildrenData();
        }

        TaTreeNode nodeOfPrefix = TaCommands.Commands.getElement(args[args.length - 2]);
        if ((nodeOfPrefix == null) || (nodeOfPrefix.getChildrenData() == null)){
            return new ArrayList<>();
        }
        for (String currentCommand: nodeOfPrefix.getChildrenData()){
            if (currentCommand.startsWith(args[args.length - 1])){
                ValidCommands.add(currentCommand);
            }
        }
        return ValidCommands;
    }
}

