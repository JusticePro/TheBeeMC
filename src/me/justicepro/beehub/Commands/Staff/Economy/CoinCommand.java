package me.justicepro.beehub.Commands.Staff.Economy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class CoinCommand extends Command {

	public CoinCommand() {
		super("token");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.ADMIN.hasPermission(player)) {
				if (args.length >= 1) {
					if (args.length == 1) {
						PlayerData data = new PlayerData(player.getName());
						try {
							data.setCoins(Integer.parseInt(args[0]));
						}catch (Exception e) {
							ChatUtils.sendMessage("ERROR", args[0] + " is not a number.", player);
							return false;
						}
						ChatUtils.sendMessage("Coins", "Set your coins to " + Integer.parseInt(args[0]), player);
					}else {
						PlayerData data = new PlayerData(args[1]);
						try {
							data.setCoins(Integer.parseInt(args[0]));
						}catch (Exception e) {
							ChatUtils.sendMessage("ERROR", args[0] + " is not a number.", player);
							return false;
						}
						ChatUtils.sendMessage("Coins", "Set " + args[1] + "'s coins to " + Integer.parseInt(args[0]), player);
					}
				}else {
					ChatUtils.sendMessage("Usage", "/coin <coins> [player].", player);
				}
			}else {
				Rank.ADMIN.sendPermissionFail(player);
			}
		}else {
			CommandSender player = sender;
			if (args.length >= 1) {
				if (args.length == 1) {
					PlayerData data = new PlayerData(player.getName());
					try {
						data.setCoins(Integer.parseInt(args[0]));
					}catch (Exception e) {
						ChatUtils.sendMessage("ERROR", args[0] + " is not a number.", player);
						return false;
					}
					ChatUtils.sendMessage("Coins", "Set your coins to " + Integer.parseInt(args[0]), player);
				}else {
					PlayerData data = new PlayerData(args[1]);
					try {
						data.setCoins(Integer.parseInt(args[0]));
					}catch (Exception e) {
						ChatUtils.sendMessage("ERROR", args[0] + " is not a number.", player);
						return false;
					}
					ChatUtils.sendMessage("Coins", "Set " + Integer.parseInt(args[0]) + " coins to " + Integer.parseInt(args[0]), player);
				}
			}else {
				ChatUtils.sendMessage("Usage", "/coin <coins> [player].", player);
			}
		}
		return false;
	}
}