package mrstraw.titlearea.interestpoint;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import static mrstraw.titlearea.TitleArea.sendTitleArea;

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
        setupFileInterestpoint();
        return FileInterestPoint;
    }

    public static void saveFileInterestPoint() {
        try {
            FileInterestPoint.save(file);
        } catch (IOException exception) {
            System.out.println(sendTitleArea("Couldn't save file 'FileInterestPoint.yml'"));
        }
        InterestPoint.setListPoint();
    }

    public static void reloadFileInterestPoint() {
        FileInterestPoint = YamlConfiguration.loadConfiguration(file);
        InterestPoint.setListPoint();
    }
}

