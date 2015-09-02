package party.vermontbeast.mcl.methods;

import org.bukkit.configuration.file.FileConfiguration;
import party.vermontbeast.mcl.Pith;

/**
 * Created by OhhPickles on 9/2/2015.
 */
public class SettingsManager {

    public static FileConfiguration getConfig = Pith.getConfig;

    public static void loadDefaults() {
        getConfig.addDefault("ServerAddress", Pith.instance.getServer().getIp());
        getConfig.options().copyDefaults(true);
    }

    public String getServerAddress() {
        return getConfig.getString("ServerAddress");
    }
}
