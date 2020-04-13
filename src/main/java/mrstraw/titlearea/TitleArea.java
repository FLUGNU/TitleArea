package mrstraw.titlearea;

import mrstraw.titlearea.interestpoint.InterestPoint;
import mrstraw.titlearea.interestpoint.InterestPointEvent;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import mrstraw.titlearea.interestpoint.InterestPointFiles;

public final class TitleArea extends JavaPlugin {
    static {
        ConfigurationSerialization.registerClass(InterestPoint.class, "InterestPoint");
    }

    @Override
    public void onEnable() {
        System.out.println("-- TitleArea : Enable plugin");
        InterestPointFiles.setupFileInterestpoint();
        InterestPointFiles.getFileInterestPoint().options().copyDefaults(true);
        InterestPointFiles.saveFileInterestPoint();

        getCommand("TitleArea").setExecutor(new TitleAreaCommands());
        getServer().getPluginManager().registerEvents(new InterestPointEvent(), this);

        System.out.println("-- TitleArea : plugin is enable");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
