package me.justicepro.beehub.Commands.Normal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Utils.ChatUtils;

public class HubCommand extends Command {
	
	public HubCommand() {
		super("hub");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.teleport(player.getWorld().getSpawnLocation().setDirection(new Vector(0, 0, -180)));
			ChatUtils.sendMessage("Hub", "Teleported to hub.", player);
		}
		return false;
	}
	
}