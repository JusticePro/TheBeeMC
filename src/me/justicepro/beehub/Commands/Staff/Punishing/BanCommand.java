package me.justicepro.beehub.Commands.Staff.Punishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Resources.Online.Punish;
import me.justicepro.beehub.Utils.ChatUtils;

public class BanCommand extends Command {
	
	public BanCommand() {
		super("ban");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.MOD.hasPermission(player)) {
				if (args.length >= 2) {
					String message = "";
					for (String arg : args) {
						if (arg!=args[0]) {
							message = message + arg + " ";
						}
					}
					PlayerData data = new PlayerData(args[0]);
					PlayerData data1 = new PlayerData(player.getName());
					
					if (!Punish.punishable(args[0])) {
						ChatUtils.sendMessage("Permissions", "You can't punish " + args[0] + ".", player);
						return false;
					}
					if (data1.getRank().hasPermission(args[0])) {
						ChatUtils.sendMessage("Permissions", "You cannot punish anyone with your rank or higher.", player);
					}else {
						if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
							for (Player target : Bukkit.getOnlinePlayers()) {
								ChatUtils.sendMessage("Punish", player.getDisplayName() + " has banned " + Bukkit.getPlayer(args[0]).getDisplayName() + " with reason \"" + message.substring(0, message.length() - 1) + "\".", target);
							}
							data.setBanned(true);
							data.setBanReason(message.substring(0, message.length() - 1));
							Bukkit.getPlayer(args[0]).kickPlayer(ChatColor.translateAlternateColorCodes('&', "&aYou've been banned by " + player.getDisplayName() + "\n&6Reason: \"" + message.substring(0, message.length() - 1) + "\""));
						}else {
							for (Player target : Bukkit.getOnlinePlayers()) {
								ChatUtils.sendMessage("Punish", player.getDisplayName() + " has banned " + Bukkit.getOfflinePlayer(args[0]).getName() + " with reason \"" + message.substring(0, message.length() - 1) + "\".", target);
							}
							data.setBanned(true);
							data.setBanReason(message.substring(message.length() - 1));
						}
					}
				}else {
					ChatUtils.sendMessage("Usage", "/ban <player> <reason>.", player);
				}
			}else {
				Rank.MOD.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}