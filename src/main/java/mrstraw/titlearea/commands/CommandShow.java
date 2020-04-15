package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandShow {
    HashMap<ShowPointRunnable,Boolean> listRunnables;

    public CommandShow(CommandSender sender, String[] args) {
        listRunnables=new HashMap<ShowPointRunnable,Boolean>();

        HashMap<String, InterestPoint> listPoint = InterestPoint.getListPoint();
        InterestPoint interestPoint;
        //if an interest point is present
        if (args.length == 1) {
            //if the interest point specified exist
            if (listPoint.containsKey(args[0])) {
                interestPoint = listPoint.get(args[0]);
            }
            // the given name is not registered
            else {
                sender.sendMessage(sendTitleArea("The given name '" + args[0] + "' does not exist"));
            }
        }
        //p is not specified
        else{
            sender.sendMessage(sendTitleArea("You have to choose a name :\n/TitleArea InterestPoint Delete [Name]"));
        }
    }
}
