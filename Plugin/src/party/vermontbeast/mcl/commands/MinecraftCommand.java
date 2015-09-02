package party.vermontbeast.mcl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import party.vermontbeast.mcl.CustomPlayer;

/**
 * Created by OhhPickles on 9/2/2015.
 */
public class MinecraftCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        CustomPlayer player = new CustomPlayer(p);
        if (cmd.getName().equalsIgnoreCase("minecraftlibrary")) {
            if(args.length == 0) {

            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("info")) {
                    p.sendMessage("]--------------[MineCraftLibrary]--------------[");
                    p.sendMessage("Author: OhhPickles [VermontBeast]");
                    p.sendMessage("Contributors: Sharkie[SharkHost]");
                    p.sendMessage("Version: 1.0");

                }
            }
        }
        return true;
    }
}
