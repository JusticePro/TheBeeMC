package me.justicepro.beehub.Commands.Staff.Punishing;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Resources.Online.Punish;
import me.justicepro.beehub.Utils.ChatUtils;

public class WarnCommand extends Command {
	
	public WarnCommand() {
		super("warn");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.HELPER.hasPermission(player)) {
				if (args.length >= 2) {
					String message = "";
					for (String arg : args) {
						if (arg!=args[0]) {
							message = message + arg + " ";
						}
					}
					if (!Punish.punishable(args[0])) {
						ChatUtils.sendMessage("Permissions", "You can't punish " + args[0] + ".", player);
						return false;
					}
					PlayerData data1 = new PlayerData(player.getName());
					if (data1.getRank().hasPermission(args[0])) {
						ChatUtils.sendMessage("Permissions", "You cannot punish anyone with your rank or higher.", player);
					}else {
						if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
							for (Player target : Bukkit.getOnlinePlayers()) {
								ChatUtils.sendMessage("Punish", player.getDisplayName() + " has warned " + Bukkit.getPlayer(args[0]).getDisplayName() + " with reason \"" + message.substring(0, message.length() - 1) + "\".", target);
							}
						}else {
							ChatUtils.sendMessage("Punish", args[0] + " is offline.", player);
						}
					}
				}else {
					ChatUtils.sendMessage("Usage", "/warn <player> <reason>.", player);
				}
			}else {
				Rank.HELPER.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}