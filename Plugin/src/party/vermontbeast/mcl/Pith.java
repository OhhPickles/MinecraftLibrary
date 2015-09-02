package party.vermontbeast.mcl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import party.vermontbeast.mcl.methods.SettingsManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by OhhPickles on 9/2/2015.
 */
public class Pith extends JavaPlugin {

    public static Plugin instance;

    public static File config;

    public static FileConfiguration getConfig;
    public void onEnable() {
        instance = this;

        config = new File(getDataFolder(), "config.yml");
        getConfig = YamlConfiguration.loadConfiguration(config);
        SettingsManager.loadDefaults();


    }

    public void saveConfig() {
        try {
            getConfig().save(config);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onDisable() {

    }
}
