package mrstraw.titlearea;

import mrstraw.titlearea.commands.TaCommands;
import mrstraw.titlearea.commands.TaTabCompleter;
import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.PointEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import mrstraw.titlearea.interestpoint.InterestPointFiles;

public final class TitleArea extends JavaPlugin {
    static { //Pour le deserialize et serialize
        ConfigurationSerialization.registerClass(InterestPoint.class, "InterestPoint");
    }

    public static String sendTitleArea(String sentence) {
        String prefix = ChatColor.BOLD + "" + ChatColor.AQUA + "--------- - TitleArea - ---------" + ChatColor.RESET;
        String sufix = ChatColor.BOLD + "" + ChatColor.AQUA +  "-----------------------------";

        return "\n" + prefix + "\n" + sentence + "\n" + sufix;
    }

    @Override
    public void onDisable() {
        System.out.println("-- TitleArea -- Plugin is now disable");
    }

    @Override
    public void onEnable() {
        System.out.println("-- TitleArea -- Enable plugin");
        InterestPointFiles.setupFileInterestpoint();
        InterestPointFiles.getFileInterestPoint().options().copyDefaults(true);
        InterestPointFiles.saveFileInterestPoint();

        getCommand("TitleArea").setExecutor(new TaCommands());
        getServer().getPluginManager().registerEvents(new PointEvent(), this);
        getCommand("TitleArea").setTabCompleter(new TaTabCompleter());
        System.out.println("-- TitleArea -- Plugin is enable");
    }

}
