package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TaCommands implements CommandExecutor {

    public static TaTreeNode Commands;

    static {

        ArrayList<String> PointSuggestions = new ArrayList<>(InterestPointFiles.getFileInterestPoint().getKeys(false));

        Commands = new TaTreeNode("TitleArea");
        Commands.addChild("Create");
        Commands.addChild("Delete").addChildren(PointSuggestions);
        Commands.addChild("List");
        Commands.addChild("Info").addChildren(PointSuggestions);
        Commands.addChild("Modify").addChildren(PointSuggestions);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'"));
        }

    //------------------------------------------------------------------------------------------------------------------

        else if(args[0].equalsIgnoreCase("Create")) {
            new CommandCreate(sender, args);
        }
        else if(args[0].equalsIgnoreCase("Modify")) {
            new CommandModify(sender, args);
        }
        else if(args[0].equalsIgnoreCase("Delete")) {
            new CommandDelete(sender, args);
        }
        else if(args[0].equalsIgnoreCase("List")) {
            new CommandList(sender, args);
        }
        else if(args[0].equalsIgnoreCase("info")) {
            new CommandInfo(sender, args);
        }

    //------------------------------------------------------------------------------------------------------------------

        else {
            sender.sendMessage(sendTitleArea("Bad argument, do :\n/help TitleArea"));
            return true;
        }
        return false;
    }
}
