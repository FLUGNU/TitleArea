package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.command.CommandSender;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandModify {

    public CommandModify(CommandSender sender, String[] args) {

        if (args.length > 4) {
            String pointName = args[1];

    //----------- - redirection - --------------------------------------------------------------------------------------

            //Le point existe
            if (InterestPoint.getListPoint().containsKey(pointName)) {
                InterestPoint pointToChange = InterestPoint.getListPoint().get(pointName);
                String valueToModify = args[2];

                String[] realArgs = new String[args.length-3];
                System.arraycopy(args, 4, realArgs, 0, args.length - 4);

            //--------------------

                if (valueToModify.equalsIgnoreCase("Title")) {
                    modifyTitle(sender, pointToChange, realArgs);
                }
                else if (valueToModify.equalsIgnoreCase("Distance")) {
                    modifyDistance(sender, args);
                }
                else if (valueToModify.equalsIgnoreCase("Radius")) {
                    modifyRadius(sender, args);
                }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

                else  {
                    sender.sendMessage(sendTitleArea("The given value to modify '" + valueToModify + "' does not exist."));
                }
            }
            else {
                sender.sendMessage(sendTitleArea("The given name '" + pointName + "' does not exist."));
            }
        }
        else {
            sender.sendMessage(sendTitleArea("Not enough argument, do :\n/TitlaArea modify [name] [args]"));
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    private void modifyTitle(CommandSender sender, InterestPoint pointName, String[] args) {
        StringBuilder newTitleBuilder = new StringBuilder();
        for(String iArgs : args) {
            newTitleBuilder.append(iArgs);
            newTitleBuilder.append(" ");
        }
        String newTitle = newTitleBuilder.toString();
        pointName.setTitle(newTitle);
        sender.sendMessage(sendTitleArea(
                "Title of point '" + pointName.getName() + "' modify to '" + newTitle + "'"));
    }

    private void modifyDistance(CommandSender sender, String[] args) {

    }

    private void modifyRadius(CommandSender sender, String[] args) {

    }

}
