package me.justicepro.beehub.Commands.Normal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Utils.ChatUtils;

public class MsgCommand extends Command {
	
	public MsgCommand() {
		super("msg");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length >= 2) {
				String message = "";
				for (String arg : args) {
					if (arg!=args[0]) {
						message = message + arg + " ";
					}
				}
				
				if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
					Player target = Bukkit.getPlayer(args[0]);
					ChatUtils.sendMessage("Private Message", player.getDisplayName() + ": " + message, target);
					ChatUtils.sendMessage("Private Message", player.getDisplayName() + ": " + message, player);
				}else {
					ChatUtils.sendMessage("Private Message", args[0] + " is offline.", player);
				}
				
			}else {
				ChatUtils.sendMessage("Usage", "/msg <player> <message...>", sender);
			}
		}
		return false;
	}
}