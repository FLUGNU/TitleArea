package mrstraw.titlearea;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TitleAreaTabCompleter implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(TitleAreaCommands.typesOfRegions);
        }
        else if (args.length == 2 && args[0].equals(TitleAreaCommands.typesOfRegions.get(0))) {
            return new ArrayList<>(TitleAreaCommands.InterestPointCommands);
        }
        return null;
    }
}
