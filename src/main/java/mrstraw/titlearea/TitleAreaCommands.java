package mrstraw.titlearea;

import mrstraw.titlearea.interestpoint.InterestPointCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class TitleAreaCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length==0){
            sender.sendMessage(sendTitleArea("Bad argument(s),\ndo '/TitleArea [arg]'\nor '/help TitleArea'",true));
        }
        //args.toString().substring(1,args.length); //pour retirer l'args[0]
        else if(args[0].equalsIgnoreCase("InterestPoint")) {
            new InterestPointCommand(sender, args);
        }
        else {
            sender.sendMessage(sendTitleArea("Bad argument, do :\n'/help TitleArea'",true));
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
