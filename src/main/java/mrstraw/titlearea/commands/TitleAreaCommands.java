package mrstraw.titlearea;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointCommand;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TitleAreaCommands implements CommandExecutor {

    public static CommandTreeNode Commands;

    static {

        ArrayList<String> InterestPointDeleteSuggestions = new ArrayList<String>(InterestPointFiles.getFileInterestPoint().getKeys(false));

        Commands = new CommandTreeNode("TitleArea");
        CommandTreeNode InterestPointCommands = Commands.addChild("InterestPoint");
        InterestPointCommands.addChild("SetNew");
        InterestPointCommands.addChild("Delete").addChildren(InterestPointDeleteSuggestions);
        InterestPointCommands.addChild("List");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'",true));
        }
        else if(args[0].equalsIgnoreCase("InterestPoint")) {
            new InterestPointCommand(sender, args);
        }
        else {
            sender.sendMessage(sendTitleArea("Bad argument, do :\n'/help TitleArea'",true));
            return true;
        }
        return false;
    }
}
