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

    public final static CommandTreeNode<String> Commands;

    static {

        ArrayList<String> InterestPointSetNewSuggestions = null;
        ArrayList<String> InterestPointDeleteSuggestions = new ArrayList<String>(InterestPointFiles.getFileInterestPoint().getKeys(false));
        ArrayList<String> InterestPointListSuggestions = null;

        Commands = new CommandTreeNode<String>("TitleArea");
        CommandTreeNode<String> InterestPointCommands = Commands.addChild("InterestPoint");
        InterestPointCommands.addChild("SetNew").addChildren(InterestPointSetNewSuggestions);
        InterestPointCommands.addChild("Delete").addChildren(InterestPointDeleteSuggestions);
        InterestPointCommands.addChild("List").addChildren(InterestPointListSuggestions);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'",true));
        }
        //args.toString().substring(1,args.length); //pour retirer l'args[0]
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
