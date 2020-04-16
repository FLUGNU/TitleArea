package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.HashMap;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandInfo {

    public CommandInfo(CommandSender sender, String[] args) {

    //----------- - Commande - -----------------------------------------------------------------------------------------

        //p a donné un nom du point dont il veut l'info
        if (args.length == 2) {
            String pointName = args[1];
            // Le nom donné existe
            if (InterestPoint.getListPoint().containsKey(pointName)) {
                HashMap<String, InterestPoint> listAllPoint = InterestPoint.getListPoint();
                String listText = "Info of point '" + pointName + "' :\n- - - - - - - - - - - - - - - - - -";
                for(InterestPoint iPoint : listAllPoint.values()) {
                    if(iPoint.getName().equals(pointName)) {
                        listText = listText + "\n  Title : " + iPoint.getTitle();
                        listText = listText + "\n  X : " + iPoint.getLocation().getX();
                        listText = listText + "\n  Y : " + iPoint.getLocation().getY();
                        listText = listText + "\n  Z : " + iPoint.getLocation().getZ();
                        listText = listText + "\n  Radius : " + iPoint.getRadius();
                        listText = listText + "\n  Distance : " + iPoint.getDistance();
                    }
                }
                sender.sendMessage(sendTitleArea(listText));
            }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

            // Le nom donné n'existe pas
            else {
                sender.sendMessage(sendTitleArea("The given name '" + pointName + "' does not exist"));
            }
        }
        //p n'a pas donné de nom au point dont il veut l'info
        else {
            sender.sendMessage(sendTitleArea("Too many arguments, do :\n/TitleArea Info [Name]"));
        }

    }
}

