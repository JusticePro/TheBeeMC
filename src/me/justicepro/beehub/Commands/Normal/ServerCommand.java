package me.justicepro.beehub.Commands.Normal;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class ServerCommand extends Command {
	
	public ServerCommand() {
		super("goto");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				boolean foundworld = false;
				World foundworld1 = null;
				for (World world : Bukkit.getWorlds()) {
					if (world.getName().equals(args[0])) {
						foundworld = true;
						foundworld1 = world;
					}
				}
				if (foundworld) {
					if (foundworld1.getName().toLowerCase().startsWith("staff") && !Rank.BUILDER.hasPermission(player)) {
						Rank.HELPER.sendServerPermissionFail(player);
					}else {
						player.teleport(new Location(foundworld1, foundworld1.getSpawnLocation().getX(), foundworld1.getSpawnLocation().getY(), foundworld1.getSpawnLocation().getZ()));
						ChatUtils.sendMessage("Server", "Transporting to &6" + foundworld1.getName(), player);
						for (Player target : Bukkit.getOnlinePlayers()) {
							if (target.getWorld()==player.getWorld()) {
								target.showPlayer(player);
								player.showPlayer(target);
							}else {
								target.hidePlayer(player);
								player.hidePlayer(target);
							}
						}
					}
				}else {
					ChatUtils.sendMessage("Server", "Couldn't find server &6" + args[0], player);
				}
			}else {
				ChatUtils.sendMessage("Server", "You currently are on &6" + player.getWorld().getName(), player);
			}
		}
		return false;
	}
	
	
}