package party.vermontbeast.mcl;

import org.bukkit.entity.Player;
import party.vermontbeast.mcl.methods.SettingsManager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by OhhPickles on 9/2/2015.
 */
public class CustomPlayer extends SettingsManager {
    Player player;
    public CustomPlayer(Player player) {
        this.player = player;
    }

    public UUID getUuid() {
        return player.getUniqueId();
    }

    public Timestamp getCurrentTimestamp() {
        return new Timestamp(getCurrentDate().getTime());
    }

    public Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    public int getKills() {
        return 0;
    }

    public int getDeaths() {
        return 0;
    }

    public int getMob_kills() {
        return 0;
    }

    public String getServerIP() {
        return getServerAddress();
    }
}
