package me.justicepro.beehub.Commands.Staff.Server;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class CreateServerCommand extends Command {
	
	public CreateServerCommand() {
		super("createserver");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.DEVELOPER.hasPermission(player)) {
				if (args.length > 0) {
					ChatUtils.sendMessage("Server", "Creating server &6" + args[0], player);
					Bukkit.getServer().createWorld(new WorldCreator(args[0]));
					ChatUtils.sendMessage("Server", "Finished creating server &6" + args[0], player);
				}else {
					ChatUtils.sendMessage("Usage", "/createserver <server>", sender);
				}
			}else {
				Rank.DEVELOPER.sendPermissionFail(player);
			}
		}
		return false;
	}
	
	
}