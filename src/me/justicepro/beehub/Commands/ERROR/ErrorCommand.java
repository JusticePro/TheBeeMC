package me.justicepro.beehub.Commands.ERROR;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Inventories.InventoryManager;

public class ErrorCommand extends Command {

	public ErrorCommand() {
		super("error");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.openInventory(InventoryManager.getErrorInventory());
		}
		return false;
	}
}