package me.justicepro.beehub.Commands.Staff.Ranks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class RankCommand extends Command {

	public RankCommand() {
		super("setrank");
	}
	
	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
		if (args.length == 1) {
			ArrayList<String> ranks = new ArrayList<>();
			for (Rank rank : Rank.ranks()) {
				ranks.add(rank.getKey());
			}
			return ranks;
		}
		return null;
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp() || player.getName().equalsIgnoreCase("JusticePro")) {
				if (args.length == 1) {
					boolean foundrank = false;
					for (Rank rank : Rank.values()) {
						if ((rank.getKey()).equalsIgnoreCase(args[0])) {
							rank.setRank(player);
							ChatUtils.sendMessage("Ranks", "Set rank to &6" + rank.getKey(), player);
							foundrank = true;
						}
					}
					if (foundrank==false) {
						ChatUtils.sendMessage("Ranks", "Could find rank " + args[0] + ".", player);
					}
				}else if (args.length == 2) {
					boolean foundrank = false;
					for (Rank rank : Rank.values()) {
						if ((rank.getKey()).equalsIgnoreCase(args[0])) {
							@SuppressWarnings("deprecation")
							OfflinePlayer offline = Bukkit.getOfflinePlayer(args[1]);
							if (offline.isOnline()) {
								rank.setRank(offline.getPlayer());
							}else {
								rank.setRank(offline.getName());
							}
							ChatUtils.sendMessage("Ranks", "Set " + offline.getName() + "'s rank to &6" + rank.getKey(), player);
							foundrank = true;
						}
					}
					if (foundrank==false) {
						ChatUtils.sendMessage("Ranks", "Could find rank " + args[0] + ".", player);
					}
				}else {
					ChatUtils.sendMessage("Usage", "/rank <rank> [player].", player);
				}
			}else {
				Rank.OWNER.sendPermissionFail(player);
			}
		}else {
			if (args.length == 2) {
				boolean foundrank = false;
				for (Rank rank : Rank.ranks()) {
					if ((rank.getKey()).equalsIgnoreCase(args[0])) {
						@SuppressWarnings("deprecation")
						OfflinePlayer offline = Bukkit.getOfflinePlayer(args[1]);
						if (offline.isOnline()) {
							rank.setRank(offline.getPlayer());
						}else {
							rank.setRank(offline.getName());
						}
						ChatUtils.sendMessage("Ranks", "Set " + offline.getName() + "'s rank to &6" + rank.getKey(), sender);
						foundrank = true;
					}
				}
				if (foundrank==false) {
					ChatUtils.sendMessage("Ranks", "Could find rank " + args[0] + ".", sender);
				}
			}else {
				ChatUtils.sendMessage("Usage", "/rank <rank> <player>.", sender);
			}
		}
		return false;
	}
}