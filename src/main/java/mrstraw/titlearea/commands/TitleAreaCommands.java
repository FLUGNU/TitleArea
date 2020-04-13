package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPointCommand;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TitleAreaCommands implements CommandExecutor {

    public static CommandTreeNode Commands;
    private static ArrayList<String> InterestPointDeleteSuggestions;

    static {

        InterestPointDeleteSuggestions = new ArrayList<String>(InterestPointFiles.getFileInterestPoint().getKeys(false));

        Commands = new CommandTreeNode("TitleArea");
        CommandTreeNode InterestPointCommands = Commands.addChild("InterestPoint");
        InterestPointCommands.addChild("SetNew");
        InterestPointCommands.addChild("Delete").addChildren(InterestPointDeleteSuggestions);
        InterestPointCommands.addChild("List");

    }

    public static void refreshInterestPointsList(){
        InterestPointDeleteSuggestions = new ArrayList<String>(InterestPointFiles.getFileInterestPoint().getKeys(false));
        Commands.getElement("Delete").resetChildren();
        Commands.getElement("Delete").addChildren(InterestPointDeleteSuggestions);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'",true));
        }
        //args.toString().substring(1,args.length); //pour retirer l'args[0]
        else if(args[0].equalsIgnoreCase("InterestPoint")) {
            new InterestPointCommand(sender, args);
            refreshInterestPointsList();
        }
        else {
            sender.sendMessage(sendTitleArea("Bad argument, do :\n'/help TitleArea'",true));
            return true;
        }
        return false;
    }
}
