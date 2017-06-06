package me.justicepro.beehub.Commands.Staff.Tools;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;

public class GamemodeCommand extends Command {

	public GamemodeCommand() {
		super("gm");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.BUILDER.inRank(player) || Rank.ADMIN.hasPermission(player)) {
				if (args.length > 0) {
					boolean foundgm = false;
					for (GameMode gm : GameMode.values()) {
						if ((gm.name()).equalsIgnoreCase(args[0])) {
							player.setGameMode(gm);
							ChatUtils.sendMessage("Abilities", "Set gamemode to &6" + gm.name(), player);
							foundgm = true;
						}
					}
					if (foundgm==false) {
						ChatUtils.sendMessage("Abilities", "Could find gamemode " + args[0] + ".", player);
					}
				}else {
					ChatUtils.sendMessage("Usage", "/gamemode <gamemode>.", player);
				}
			}else {
				Rank.BUILDER.sendPermissionFail(player);
			}
		}
		return false;
	}

}