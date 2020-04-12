package mrstraw.titlearea.interestpoint;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class InterestPointFiles {

    private static File file;
    private static FileConfiguration FileInterestPoint;

    public static void setupFileInterestpoint() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("TitleArea").getDataFolder(), "FileInterestPoint.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {

            }
        }
        FileInterestPoint = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFileInterestPoint() {
        return FileInterestPoint;
    }

    public static void saveFileInterestPoint() {
        try {
            FileInterestPoint.save(file);
        } catch (IOException exception) {
            System.out.println("-- TitleArea : Couldn't save file 'FileInterestPoint'");
        }
        //InterestPoint.setListPoint();
    }

    public static void reloadFileInterestPoint() {
        FileInterestPoint = YamlConfiguration.loadConfiguration(file);
        //InterestPoint.setListPoint();
    }
}

