package mrstraw.titlearea;

import mrstraw.titlearea.interestpoint.InterestPointCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TitleAreaCommands implements CommandExecutor {

    public final static ArrayList<String> typesOfRegions;
    public final static ArrayList<String> InterestPointCommands;

    static {
        typesOfRegions = new ArrayList<>();
        typesOfRegions.add("InterestPoint");

        InterestPointCommands = new ArrayList<>();
        InterestPointCommands.add("SetNew");
        InterestPointCommands.add("List");
        InterestPointCommands.add("Delete");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s), do '/TitleArea [arg]' or '/help TitleArea'"));
        }
        //args.toString().substring(1,args.length); //pour retirer l'args[0]
        else if(args[0].equalsIgnoreCase("InterestPoint")) {
            new InterestPointCommand(sender, args);
        }
        else {
            sender.sendMessage(sendTitleArea("Bad argument, do '/help TitleArea'"));
            return true;
        }
        return false;
    }
}
