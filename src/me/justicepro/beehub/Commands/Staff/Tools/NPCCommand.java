package me.justicepro.beehub.Commands.Staff.Tools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Utils.ChatUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class NPCCommand extends Command {
	
	public NPCCommand() {
		super("npc");
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length > 1) {
						NPCRegistry register = CitizensAPI.getNPCRegistry();
						NPC npc = register.createNPC(EntityType.PLAYER, ChatColor.translateAlternateColorCodes('&', args[1]));
						npc.spawn(player.getLocation());
					}else {
						ChatUtils.sendMessage("Usage", "/npc <create> <playername>", player);
					}
				}else if (args[0].equalsIgnoreCase("remove")) {
					CitizensAPI.getDefaultNPCSelector().getSelected(player).despawn();
					CitizensAPI.getDefaultNPCSelector().getSelected(player).destroy();
					ChatUtils.sendMessage("NPC", "Removed npc", player);
				}
			}else {
				ChatUtils.sendMessage("Usage", "/npc <create|remove> [playername]", player);
			}
		}
		
		return false;
	}
	
}