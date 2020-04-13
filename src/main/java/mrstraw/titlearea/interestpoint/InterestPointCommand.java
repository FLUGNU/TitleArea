package mrstraw.titlearea.interestpoint;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.Set;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class InterestPointCommand {

    public InterestPointCommand(CommandSender sender, String[] args) {
        FileConfiguration config = InterestPointFiles.getFileInterestPoint();

        if (args.length==1){
            sender.sendMessage(sendTitleArea("Bad argument(s), do :\n'/TitleArea InterestPoint [arg]' or '/help TitleArea'",true));
        }

    //------------------------------------------------------------------------------------------------------------------

        else if(args[1].equalsIgnoreCase("SetNew")) {
            // Si le sender est un joueur p
            if(sender instanceof Player) {
                Player p = (Player) sender;

                //p n'a pas donné de nom au nouveau point
                if(args.length==2) {
                    p.sendMessage(sendTitleArea("You have to choose a name key :\n/TitleArea InterestPoint SetNew [NameKey]",true));
                }
                //p a donné un nom au nouveau point
                else {
                    // Le nom donné est pris
                    if ( config.contains(args[2]) ) {
                        p.sendMessage(sendTitleArea("Name key '" + args[2] + "' already exist, choose another",true));
                    }
                    // Ce nom donné est libre
                    else {
                        String pointName = args[2];
                        InterestPoint newPoint = new InterestPoint("Title of " + pointName, p.getLocation());
                        config.createSection(pointName, newPoint.serialize());
                        InterestPointFiles.saveFileInterestPoint();
                        p.sendMessage(sendTitleArea("Nouveau point '" + pointName + "' créé",true));
                    }
                }
            }
            // Si le sender n'est pas un joueur
            else { sender.sendMessage(sendTitleArea("You need to be a player for that",true)); }
        }

    //------------------------------------------------------------------------------------------------------------------

        else if(args[1].equalsIgnoreCase("List")) {
            Set<String> listKey = config.getKeys(false);

            // Si il y n'y a pas de point d'interet
            if (listKey.size() == 0) {
                sender.sendMessage(sendTitleArea("There is no interest points",true));
            }
            // Si il y a des points d'interet
            else {
                sender.sendMessage(sendTitleArea("List of interest point :",false));
                for(String nameKey : listKey) {
                    sender.sendMessage("                  - " + nameKey);
                }
                sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA +  "------------------------------");
            }
        }

    //------------------------------------------------------------------------------------------------------------------

        else if(args[1].equalsIgnoreCase("Delete")) {
            //p n'a pas donné de nom au point à supprimer
            if(args.length==2) {
                sender.sendMessage(sendTitleArea("You have to choose a name key :\n/TitleArea InterestPoint Delete [NameKey]",true));
            }
            //p a donné un nom au point à supprimer
            else {
                String path = args[2];
                // Le nom donné existe
                if ( config.contains(path) ) {
                    config.set(path, null);
                    InterestPointFiles.saveFileInterestPoint();
                    sender.sendMessage(sendTitleArea("The interest point '" + path + "' has been deleted",true));
                }
                // Ce nom n'existe pas
                else {
                    sender.sendMessage(sendTitleArea("The given name '" + path + "' does not exist",true));
                }
            }
        }

    //------------------------------------------------------------------------------------------------------------------

    }
}

