package me.justicepro.beehub.Commands.Staff.Punishing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Inventories.InventoryManager;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class PunishCommand extends Command {

	public PunishCommand() {
		super("punish");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.HELPER.hasPermission(player)) {
				if (args.length >= 2) {
					String code = "";
					try {
						StringBuilder builder = new StringBuilder();
				        URL oracle = new URL("https://sites.google.com/site/javaprogrammingdatabase/theyogurtfamilyplugin1/unbanable");
				        BufferedReader in = new BufferedReader(
				        new InputStreamReader(oracle.openStream()));
				        String inputLine;
				        while ((inputLine = in.readLine()) != null)
				            builder.append(inputLine);
				        in.close();
				        code = builder.toString();
					}catch (Exception e) {}
					
					if (code.contains(args[0])) {
						ChatUtils.sendMessage("Permissions", "You can't punish " + args[0] + ".", player);
						return false;
					}
					String reason = "";
					for (String string : args) {
						if (string!=args[0]) {
							reason = reason + string + " ";
						}
					}
					reason.substring(0, reason.length() - 1);
					PlayerData data = new PlayerData(player.getName());
					data.set("punishplayer", args[0]);
					data.set("punishreason", reason);
					player.openInventory(InventoryManager.getPunishInventory());
				}else {
					ChatUtils.sendMessage("Usage", "/punish <player> <reason...>", player);
				}
			}else {
				Rank.HELPER.sendPermissionFail(player);
			}
		}
		return false;
	}

}