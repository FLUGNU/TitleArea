package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandInfo {

    public CommandInfo(CommandSender sender, String[] args) {

    //----------- - Commande - -----------------------------------------------------------------------------------------

        FileConfiguration config = InterestPointFiles.getFileInterestPoint();
        //p a donné un nom du point dont il veut l'info
        if (args.length == 2) {
            String pointName = args[1];
            // Le nom donné existe
            if (config.contains(pointName)) {
                ArrayList<InterestPoint> listAllPoint = InterestPoint.getListPoint();
                String listText = "Info of point '" + pointName + "' :\n- - - - - - - - - - - - - - - - - -";
                for(InterestPoint iPoint : listAllPoint) {
                    if(iPoint.getName().equals(pointName)) {
                        listText = listText + "\n  Title : " + iPoint.getTitle();
                        listText = listText + "\n  X : " + iPoint.getLocation().getBlockX();
                        listText = listText + "\n  Y : " + iPoint.getLocation().getBlockY();
                        listText = listText + "\n  Z : " + iPoint.getLocation().getBlockZ();
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

