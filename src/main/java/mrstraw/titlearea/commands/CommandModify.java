package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandModify {

    public CommandModify(CommandSender sender, String[] args) {

        if (args.length > 3) {
            String pointName = args[1];

    //----------- - redirection - --------------------------------------------------------------------------------------

            //Le point existe
            if (InterestPoint.getListPoint().containsKey(pointName)) {
                InterestPoint pointToChange = InterestPoint.getListPoint().get(pointName);
                String valueToModify = args[2];

                String[] realArgs = new String[args.length-3];
                System.arraycopy(args, 3, realArgs, 0, args.length - 3);

            //--------------------

                if (valueToModify.equalsIgnoreCase("Title")) {
                    modifyTitle(sender, pointToChange, realArgs);
                }
                else if (valueToModify.equalsIgnoreCase("Distance")) {
                    modifyDistance(sender, pointToChange, realArgs);
                }
                else if (valueToModify.equalsIgnoreCase("Radius")) {
                    modifyRadius(sender, pointToChange, realArgs);
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

    private void modifyTitle(CommandSender sender, InterestPoint pointToChange, String[] args) {
        StringBuilder newTitleBuilder = new StringBuilder();
        for(String iArgs : args) {
            newTitleBuilder.append(iArgs);
            newTitleBuilder.append(" ");
        }
        String newTitle = newTitleBuilder.toString();
        pointToChange.setTitle(newTitle);
        sender.sendMessage(sendTitleArea(
                "Title of point '" + pointToChange.getName() + "' modify to '" + newTitle + "'"));
    }

    private void modifyDistance(CommandSender sender, InterestPoint pointToChange, String[] args) {
        boolean isADouble = false; //le double est bien un double
        double valeurDouble = 0.0;
        String argument = args[0];

        // Si le sender un seul argument
        if (args.length == 1) {
            // si l'argument est "me"
            if (argument.equalsIgnoreCase("me")) {
                // On regarde si le sender est un joueur
                if (sender instanceof Player) {
                    Player p = ((Player) sender).getPlayer();
                    Vector delta = pointToChange.getLocation().toVector().add(p.getLocation().toVector().multiply(-1));
                    valeurDouble = delta.length();
                    isADouble = true;
                }
                // ce n'est pas un joueur
                else {
                    sender.sendMessage(sendTitleArea("You have to be a player for that"));
                }
            }
            else {
                try {
                    // Test si l'arg est un double.
                    valeurDouble = Double.parseDouble(argument);
                    isADouble = true;
                }
                catch (Exception e) {
                    //Dit au joueur que son argument n'est pas un int
                    sender.sendMessage(sendTitleArea(
                            "The given modification '" + argument + "' is not a Double or 'me'"));
                }
            }
        }
        // Si le sender envoie trop d'argument
        else {
            sender.sendMessage(sendTitleArea("You send too many arguments"));
        }

        if (isADouble) {
            double pointRadius = pointToChange.getRadius();
            // Regarde si n'est pas plus petit que le radius
            if (valeurDouble >= pointRadius) {
                pointToChange.setDistance(valeurDouble);
                sender.sendMessage(sendTitleArea(
                        "Radius of point '" + pointToChange.getName() + "' modify to '" + argument + "'"));
            }
            // Sinon dit au joueur de refaire un chiffre dans la fourchette
            else {
                sender.sendMessage(sendTitleArea(
                        "The given modification '" + argument + "' must not be smaller that the radius (" + pointRadius + ")"));
            }
        }

    }

    private void modifyRadius(CommandSender sender, InterestPoint pointToChange, String[] args) {
        boolean isADouble = false; //le double est bien un double
        double valeurDouble = 0.0;
        String argument = args[0];

        // Si le sender un seul argument
        if (args.length == 1) {
            // si l'argument est "me"
            if (argument.equalsIgnoreCase("me")) {
                // On regarde si le sender est un joueur
                if (sender instanceof Player) {
                    Player p = ((Player) sender).getPlayer();
                    Vector delta = pointToChange.getLocation().toVector().add(p.getLocation().toVector().multiply(-1));
                    valeurDouble = delta.length();
                    isADouble = true;
                }
                // ce n'est pas un joueur
                else {
                    sender.sendMessage(sendTitleArea("You have to be a player for that"));
                }
            }
            else {
                try {
                    // Test si l'arg est un double.
                    valeurDouble = Double.parseDouble(argument);
                    isADouble = true;
                }
                catch (Exception e) {
                    //Dit au joueur que son argument n'est pas un int
                    sender.sendMessage(sendTitleArea(
                            "The given modification '" + argument + "' is not a Double or 'me'"));
                }
            }
        }
        // Si le sender envoie trop d'argument
        else {
            sender.sendMessage(sendTitleArea("You send too many arguments"));
        }

        if (isADouble) {
            double pointDistance = pointToChange.getDistance();
            // Regarde si est dans la fourchette admissible
            if ( (valeurDouble < pointDistance) && (valeurDouble > 0) ) {
                pointToChange.setRadius(valeurDouble);
                sender.sendMessage(sendTitleArea(
                        "Radius of point '" + pointToChange.getName() + "' modify to '" + argument + "'"));
            }
            // Sinon dit au joueur de refaire un chiffre dans la fourchette
            else {
                sender.sendMessage(sendTitleArea(
                        "The given modification '" + argument + "' is not between '0 - " + pointDistance + "'"));
            }
        }

    }

}
