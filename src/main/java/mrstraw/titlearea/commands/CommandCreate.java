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

    //----------- - Commande - -----------------------------------------------------------------------------------------

        //le joueur appel bien : Create, le nom, la variable à modifier, la modification. (pfiouuu)
        if (args.length == 4) {
            String pointName = args[1];
            String pointConstant = args[2];
            String pointModification = args[3];

            if (getListPoint.containsKey(pointName)) {
                InterestPoint pointToChange = getListPoint.get(pointName);
            //---------------

                if (pointConstant.equalsIgnoreCase("Title")) {
                    pointToChange.setTitle(pointName, pointModification);
                }

            //---------------

                else if (pointConstant.equalsIgnoreCase("Distance")) {
                    try {
                        Integer.parseInt(pointModification);
                        pointToChange.setDistance(pointName, pointModification);
                    }
                    catch (Exception e) {
                        sender.sendMessage(sendTitleArea("The given modification '" + pointModification + "' is not a Integer"));
                    }
                }

            //---------------

                else if (pointConstant.equalsIgnoreCase("Radius")) {
                    try {
                        Integer.parseInt(pointModification);
                        pointToChange.setRadius(pointName, pointModification);
                    }
                    catch (Exception e) {
                        sender.sendMessage(sendTitleArea("The given modification '" + pointModification + "' is not a Integer"));
                    }
                }

            //---------------
            }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

            // le nom du point donné n'existe pas
            else {
                sender.sendMessage(sendTitleArea("The given name '" + pointName + "' does not exist"));
            }
        }
        // le joueur n'a pas mis tout les arguments, ou en a trop mis.
        else {
            sender.sendMessage(sendTitleArea("You have to this command in that order :" +
                    "\n/TitleArea Modify [Name] [constant] [modification]"));
        }

    }
}
