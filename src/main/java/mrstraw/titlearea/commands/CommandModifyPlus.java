package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.command.CommandSender;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandModifyPlus {

    public CommandModifyPlus(CommandSender sender, String[] args) {

        //----------- - Commande - -----------------------------------------------------------------------------------------

        //le joueur appel bien : Create, le nom, la variable à modifier, la modification. (pfiouuu)
        if (args.length >= 4) {
            String pointName = args[1];
            String pointConstant = args[2];
            String pointModification = args[3];
            // Le nom du point donné existe bien
            if (InterestPoint.getListPoint().containsKey(pointName)) {
                InterestPoint pointToChange = InterestPoint.getListPoint().get(pointName);
                //---------------

                if (pointConstant.equalsIgnoreCase("Title")) {
                    //assemble les args pour en faire un seul string
                    for (int i=4; i==args.length; i++) {
                        pointModification = pointModification + " " + args[i];
                    }
                    pointToChange.setTitle(pointModification);
                    sender.sendMessage(sendTitleArea(
                            "Title of point '" + pointName + "' modify to '" + pointModification + "'"));
                }

                //---------------

                else if (pointConstant.equalsIgnoreCase("Distance")) {
                    try {
                        pointToChange.setDistance(Integer.parseInt(pointModification));
                        sender.sendMessage(sendTitleArea(
                                "Distance of point '" + pointName + "' modify to '" + pointModification + "'"));
                    }
                    catch (Exception e) {
                        sender.sendMessage(sendTitleArea(
                                "The given modification '" + pointModification + "' is not a Integer"));
                    }
                }

                //---------------

                else if (pointConstant.equalsIgnoreCase("Radius")) {
                    try {
                        pointToChange.setRadius(Integer.parseInt(pointModification));
                        sender.sendMessage(sendTitleArea(
                                "Radius of point '" + pointName + "' modify to '" + pointModification + "'"));
                    }
                    catch (Exception e) {
                        sender.sendMessage(sendTitleArea(
                                "The given modification '" + pointModification + "' is not a Integer"));
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
    }

}
