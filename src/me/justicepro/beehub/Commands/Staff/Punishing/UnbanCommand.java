package me.justicepro.beehub.Commands.Staff.Punishing;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class UnbanCommand extends Command {

	public UnbanCommand() {
		super("unban");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.MOD.hasPermission(player)) {
				if (args.length >= 1) {
					for (Player target : Bukkit.getOnlinePlayers()) {
						ChatUtils.sendMessage("Punish", player.getDisplayName() + " has unbanned " + Bukkit.getOfflinePlayer(args[0]).getName() + ".", target);
					}
					PlayerData data = new PlayerData(args[0]);
					data.setBanned(false);
				}else {
					ChatUtils.sendMessage("Usage", "/unban <player>.", player);
				}
			}else {
				Rank.MOD.sendPermissionFail(player);
			}
		}
		return false;
	}

}