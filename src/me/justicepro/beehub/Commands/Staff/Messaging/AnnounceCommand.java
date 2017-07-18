package me.justicepro.beehub.Commands.Staff.Messaging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class AnnounceCommand extends Command {
	
	public AnnounceCommand() {
		super("announce");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.ADMIN.hasPermission(player)) {
				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}
				for (Player target : Bukkit.getOnlinePlayers()) {
					ChatUtils.sendMessage("Announcement", "&b" + message, target);
					target.sendTitle(ChatColor.translateAlternateColorCodes('&',
							"&eAnnouncement"), message);
				}
			}else {
				Rank.ADMIN.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}