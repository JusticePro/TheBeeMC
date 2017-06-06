package me.justicepro.beehub.Commands.Normal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Utils.ChatUtils;

public class BalanceCommand extends Command {
	
	public BalanceCommand() {
		super("bal");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			ChatUtils.sendMessage("Balance", "Balance: " + new PlayerData(player.getName()).getCoins(), player);
		}
		return false;
	}
	
}