package me.justicepro.beehub.Commands.Staff.Messaging;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class BroadcastCommand extends Command {
	
	public BroadcastCommand() {
		super("b");
	}
	
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
					ChatUtils.sendMessage("Broadcast", player.getDisplayName() + "&c: &e&l" + message, target);
				}
			}else {
				Rank.ADMIN.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}