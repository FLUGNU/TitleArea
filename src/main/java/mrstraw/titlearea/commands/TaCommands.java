package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TaCommands implements CommandExecutor {

    public static TaTreeNode Commands;

    static {

        ArrayList<String> PointSuggestions = new ArrayList<>(InterestPoint.getListPoint().keySet());

        Commands = new TaTreeNode("TitleArea");
        Commands.addChild("create");
        Commands.addChild("delete").addChildren(PointSuggestions);
        Commands.addChild("list");
        Commands.addChild("info").addChildren(PointSuggestions);
        Commands.addChild("modify").addChildren(PointSuggestions);
        Commands.addChild("show").addChildren(PointSuggestions);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("No argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'"));
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
        else if(args[0].equalsIgnoreCase("Info")) {
            new CommandInfo(sender, args);
        }
        else if(args[0].equalsIgnoreCase("Show")) {
            new CommandShow(sender, args);
        }

    //------------------------------------------------------------------------------------------------------------------

        else {
            sender.sendMessage(sendTitleArea("Bad argument, do :\n/help TitleArea"));
            return true;
        }
        return false;
    }
    public static void update(){
        ArrayList<String> PointSuggestions = new ArrayList<>(InterestPoint.getListPoint().keySet());

        Commands = new TaTreeNode("TitleArea");
        Commands.addChild("create");
        Commands.addChild("delete").addChildren(PointSuggestions);
        Commands.addChild("list");
        Commands.addChild("info").addChildren(PointSuggestions);
        Commands.addChild("modify").addChildren(PointSuggestions);
        Commands.addChild("show").addChildren(PointSuggestions);
    }
}
