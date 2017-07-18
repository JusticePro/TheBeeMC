package me.justicepro.beehub.Commands.Premium;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class FlightCommand extends Command {
	
	public FlightCommand() {
		super("fly");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			PlayerData data = new PlayerData(player.getName());
			if (Rank.ADMIN.hasPermission(player)) {
				if (player.getAllowFlight()) {
					player.setAllowFlight(false);
					ChatUtils.sendMessage("Abilities", "Fly has been disabled.", player);
				}else {
					player.setAllowFlight(true);
					ChatUtils.sendMessage("Abilities", "Fly has been enabled.", player);
				}
			}else {
				Rank.ADMIN.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}