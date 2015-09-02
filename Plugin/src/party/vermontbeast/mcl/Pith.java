package party.vermontbeast.mcl;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by OhhPickles on 9/2/2015.
 */
public class Pith extends JavaPlugin {

    public static Plugin instance;

    public void onEnable() {
        instance = this;
    }

    public void onDisable() {

    }
}
