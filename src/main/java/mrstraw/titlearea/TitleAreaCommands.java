package mrstraw.titlearea;

import mrstraw.titlearea.interestpoint.InterestPointCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TitleAreaCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage("-- TitleArea : Bad argument(s), do '/TitleArea [arg]' or '/help TitleArea'");
        }
        //args.toString().substring(1,args.length); //pour retirer l'args[0]
        else if(args[0].equals("InterestPoint")) {
            new InterestPointCommand(sender, args);
        }
        else {
            sender.sendMessage("-- TitleArea : Bad argument, do '/help TitleArea'");
            return true;
        }
        return false;
    }

    /*public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("InterestPoint");

            return arguments;
        }
        return null;
    }*/

}
