package mrstraw.titlearea;

import mrstraw.titlearea.commands.TitleAreaCommands;
import mrstraw.titlearea.commands.TitleAreaTabCompleter;
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

    @Override
    public void onEnable() {
        System.out.println("-- TitleArea -- Enable plugin");
        InterestPointFiles.setupFileInterestpoint();
        InterestPointFiles.getFileInterestPoint().options().copyDefaults(true);
        InterestPointFiles.saveFileInterestPoint();

        getCommand("TitleArea").setExecutor(new TitleAreaCommands());
        getServer().getPluginManager().registerEvents(new PointEvent(), this);
        getCommand("TitleArea").setTabCompleter(new TitleAreaTabCompleter());
        System.out.println("-- TitleArea -- Plugin is enable");
    }

    @Override
    public void onDisable() {
        System.out.println("-- TitleArea -- Plugin is now disable");
    }

    public static String sendTitleArea(String sentence, boolean close) {
        String prefix = ChatColor.BOLD + "" + ChatColor.AQUA + "--------- - TitleArea - ---------" + ChatColor.RESET;
        String sufix = ChatColor.BOLD + "" + ChatColor.AQUA +  "------------------------------";
        if (close) {
            return "\n" + prefix + "\n" + sentence + "\n" + sufix;
        }
        else {
            return "\n" + prefix + "\n" + sentence;
        }
    }

}
