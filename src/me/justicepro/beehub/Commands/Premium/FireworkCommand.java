package me.justicepro.beehub.Commands.Premium;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import me.justicepro.beehub.Ranks.Rank;

public class FireworkCommand extends Command {
	
	public FireworkCommand() {
		super("fw");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Rank.VIP.hasPermission(player)) {
				Firework firework = player.getWorld().spawn(player.getEyeLocation(), Firework.class);
				FireworkMeta fireworkmeta = firework.getFireworkMeta();
				fireworkmeta.addEffect(FireworkEffect.builder().with(Type.CREEPER).withColor(Color.GREEN).withFade(Color.AQUA, Color.BLUE, Color.GREEN, Color.MAROON).build());
				fireworkmeta.addEffect(FireworkEffect.builder().with(Type.STAR).withColor(Color.GREEN).withFade(Color.AQUA, Color.BLUE, Color.GREEN, Color.MAROON).build());
				firework.setFireworkMeta(fireworkmeta);
			}else {
				Rank.VIP.sendPermissionFail(player);
			}
		}
		return false;
	}
	
}