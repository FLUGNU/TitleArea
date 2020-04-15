package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandCreate {

    public CommandCreate(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

    //----------- - Commande - -----------------------------------------------------------------------------------------

            if (args.length == 2) {
                FileConfiguration config = InterestPointFiles.getFileInterestPoint();
                String pointName = args[1];
                // Le nom donné est libre
                if (!config.contains(pointName)) {
                    InterestPoint newPoint = new InterestPoint(pointName, p.getLocation());
                    config.createSection(pointName, newPoint.serialize());
                    InterestPointFiles.saveFileInterestPoint();
                    p.sendMessage(sendTitleArea("Nouveau point '" + pointName + "' créé"));
                }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

                else {
                    p.sendMessage(sendTitleArea("Name key '" + pointName + "' already exist, choose another"));
                }
            }
            else if (args.length == 1) {
                p.sendMessage(sendTitleArea("Bad argument, do :\n/TitleArea new [name]"));
            }
            else {
                p.sendMessage(sendTitleArea("Too many arguments, do :\n/TitleArea new [name]"));
            }
        }
        else {
            sender.sendMessage(sendTitleArea("You have to be a player for that"));
        }
    }
}
