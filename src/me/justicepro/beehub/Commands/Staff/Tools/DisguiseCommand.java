package me.justicepro.beehub.Commands.Staff.Tools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class DisguiseCommand extends Command {
	
	public DisguiseCommand() {
		super("disguise");
	}

	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			PlayerData data = new PlayerData(player.getName());
			if (Rank.YOUTUBE.hasPermission(player)) {
				if (args.length >= 1) {
					data.set("disguised", true);
					Rank.DEFAULT.setRankPrefix(args[0], player);
					ChatUtils.sendMessage("Disguise", "You've disguised your name as " + args[0], sender);
				}else {
					data.set("disguised", false);
					data.getRank().setRankPrefix(player.getName(), player);
					ChatUtils.sendMessage("Disguise", "You've have undisguised yourself.", sender);
				}
			}else {
				Rank.YOUTUBE.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}