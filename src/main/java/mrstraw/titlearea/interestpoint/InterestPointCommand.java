package mrstraw.titlearea.interestpoint;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InterestPointCommand {

    public InterestPointCommand(CommandSender sender, String[] args) {

        if (args.length==1){
            sender.sendMessage("-- TitleArea : Bad argument(s), do '/TitleArea InterestPoint [arg]' or '/help TitleArea'");
        }
        else if(args[1].equals("setNew")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;

                InterestPoint newPoint = new InterestPoint("Maison", p.getLocation());
                InterestPointFiles.getFileInterestPoint().createSection("1", newPoint.serialize());
                InterestPointFiles.saveFileInterestPoint();
                p.sendMessage("Point créé");
            }
            else { sender.sendMessage("-- TitleArea : You need to be a player for that"); }
        }
    }
}

