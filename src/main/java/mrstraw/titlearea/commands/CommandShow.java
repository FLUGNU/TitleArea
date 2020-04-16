package mrstraw.titlearea.commands;

import mrstraw.titlearea.interestpoint.InterestPoint;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

import static mrstraw.titlearea.TitleArea.sendTitleArea;

public class CommandShow {
    static HashMap<String,Integer> listRunnable=new HashMap<String,Integer>();;

    public CommandShow(CommandSender sender, String[] args) {
        Player player =(Player)sender;
        HashMap<String, InterestPoint> listPoint = InterestPoint.getListPoint();
        InterestPoint interestPoint;
        //if an interest point is present
        if (args.length == 2) {
            //if the interest point specified exist
            if (listPoint.containsKey(args[1])) {
                interestPoint = listPoint.get(args[1]);
                //if the runnable is already set:
                if(listRunnable.containsKey(interestPoint.getName())){
                    cancelShow(interestPoint.getName());
                    player.sendMessage("Hide InterestPoint: "+interestPoint.getName());
                }
                //if the runnable isn't set:
                else{
                    BukkitScheduler scheduler = Bukkit.getScheduler();
                    ShowPointRunnable runnable=new ShowPointRunnable(interestPoint);
                    player.sendMessage("Show InterestPoint: "+interestPoint.getName());
                    Integer id=scheduler.scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("TitleArea"),runnable,0L,10L);
                    listRunnable.put(interestPoint.getName(),id);
                }
            }
            // the given name is not registered
            else {
                sender.sendMessage(sendTitleArea("The given name '" + args[1] + "' does not exist"));
            }
        }
        //p is not specified
        else{
            sender.sendMessage(sendTitleArea("You have to choose a name :\n/TitleArea InterestPoint Show [Name]"));
        }
    }

    public static void cancelShow(String insterestPointName){
        if(listRunnable.containsKey(insterestPointName)){
            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.cancelTask(listRunnable.get(insterestPointName));
            listRunnable.remove(insterestPointName);
        }
    }
}
