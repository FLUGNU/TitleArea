package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandDelete {

    public CommandDelete(CommandSender sender, String[] args) {

    //----------- - Commande - -----------------------------------------------------------------------------------------

        //p a donné un nom au point à supprimer
        if (args.length == 2) {
            String pointName = args[1];
            // Le nom donné existe
            if (InterestPoint.getListPoint().containsKey(pointName)) {
                InterestPointFiles.getFileInterestPoint().set(pointName, null);
                InterestPointFiles.saveFileInterestPoint();
                CommandShow.cancelShow(pointName);
                sender.sendMessage(sendTitleArea("The interest point '" + pointName + "' has been deleted"));
            }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

            // Le nom donné n'existe pas
            else {
                sender.sendMessage(sendTitleArea("The given name '" + pointName + "' does not exist"));
            }
        }
        //p n'a pas donné de nom au point à supprimer
        else {
            sender.sendMessage(sendTitleArea("You have to choose a name key :\n/TitleArea InterestPoint Delete [NameKey]"));
        }

    }
}