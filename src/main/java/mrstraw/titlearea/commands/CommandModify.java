package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPointFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandModify {

    public CommandModify(CommandSender sender, String[] args) {

    //----------- - Commande - -----------------------------------------------------------------------------------------

        FileConfiguration config = InterestPointFiles.getFileInterestPoint();
        //p a donné un nom au point à modifier
        if (args.length == 2) {
            String pointName = args[1];
            // Le nom donné existe
            if (config.contains(pointName)) {
                config.set(pointName, null);
                InterestPointFiles.saveFileInterestPoint();
                sender.sendMessage(sendTitleArea("The interest point '" + pointName + "' has been deleted"));
            }

    //----------- - Erreurs d'arguments - ------------------------------------------------------------------------------

            // Le nom donné n'existe pas
            else {
                sender.sendMessage(sendTitleArea("The given name '" + pointName + "' does not exist"));
            }
        }
        //p n'a pas donné de nom au point à modifier
        else {
            sender.sendMessage(sendTitleArea("You have to choose a name :\n/TitleArea Modifiy [Name]"));
        }

    }

}
