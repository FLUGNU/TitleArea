package mrstraw.titlearea.interestpoint;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InterestPointCommand {

    public InterestPointCommand(CommandSender sender, String[] args) {

        FileConfiguration config = InterestPointFiles.getFileInterestPoint();

        if (args.length==1){
            sender.sendMessage("-- TitleArea : Bad argument(s), do '/TitleArea InterestPoint [arg]' or '/help TitleArea'");
        }

        //------------------------------------------------------------------------------------------------------

        else if(args[1].equals("SetNew")) {
            if(sender instanceof Player) { //cette commande ne marche que pour un joueur p.

                Player p = (Player) sender;
                String newKey = String.valueOf(config.getKeys(false).size());
                String pointName;

                if(args.length==2) { pointName = newKey; } //p n'a pas donné de nom au nouveau point.
                else { pointName = args[2]; }              //p à donné un nom au nouveau point.

                InterestPoint newPoint = new InterestPoint(pointName, p.getLocation());
                config.createSection(newKey, newPoint.serialize());
                InterestPointFiles.saveFileInterestPoint();
                p.sendMessage("-- TitleArea : nouveau point '" + pointName + "' créé");
            }
            else { sender.sendMessage("-- TitleArea : You need to be a player for that"); }
        }

        //------------------------------------------------------------------------------------------------------

        else if(args[1].equals("List")) {
            sender.sendMessage("-- TitleArea : liste des points d'interet :");

            ArrayList<InterestPoint> listPoint = InterestPoint.getListPoint();
            for (InterestPoint point : listPoint) {
                sender.sendMessage("                  - " + point.getTitle());
            }
        }

        //------------------------------------------------------------------------------------------------------
    }
}

