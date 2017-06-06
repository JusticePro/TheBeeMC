package me.justicepro.beehub.Commands.Normal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends Command {

	public TestCommand() {
		super("test");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
		}
		return false;
	}
}