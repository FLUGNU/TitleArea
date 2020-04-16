package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandList {

    public CommandList(CommandSender sender, String[] args) {

    //----------- - Commande - -----------------------------------------------------------------------------------------

        // Si le sender ne precise pas de monde
        if (args.length == 1) {
            Set<String> listKey = InterestPoint.getListPoint().keySet();

            // Si il y n'y a pas de point d'interet du tout
            if (listKey.size() == 0) {
                sender.sendMessage(sendTitleArea("There is no point"));
            }
            // Si il y a des points d'interet
            else {
                String listText = "List of all point :";
                for(String iKay : listKey) {
                    listText = listText + "\n  - " + iKay;
                }
                sender.sendMessage(sendTitleArea(listText));
            }
        }

        // Si le sender precise le monde
        else if (args.length == 2) {
            String worldName = args[1];
            HashMap<String, InterestPoint> listAllPoint = InterestPoint.getListPoint();
            String listText = "";
            for(InterestPoint iPoint : listAllPoint.values()) {
               String worldiPoint = iPoint.getLocation().getWorld().getName();
                if(worldiPoint.equals(worldName)) {
                    listText = listText + "  - " + iPoint.getName() + "\n";
                }
            }
            if (listText.equals("")) {
                listText = "No point found in the '" + worldName + "' world";
            }
            else {
                listText = "List of points in the '" + worldName + "' world :\n" + listText;
            }
            sender.sendMessage(sendTitleArea(listText));
        }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

        else {
            sender.sendMessage(sendTitleArea("Too many arguments, do :\n/TitleArea new [name]"));
        }

    }
}
