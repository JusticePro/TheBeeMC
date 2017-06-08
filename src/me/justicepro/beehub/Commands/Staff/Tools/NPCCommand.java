package me.justicepro.beehub.Commands.Staff.Tools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Utils.ChatUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class NPCCommand extends Command {

	public NPCCommand() {
		super("npc");
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length > 1) {
						if (args.length >= 3) {
							NPCRegistry register = CitizensAPI.getNPCRegistry();
							@SuppressWarnings("deprecation")
							NPC npc = register.createNPC(EntityType.fromName(args[2].toUpperCase()), ChatColor.translateAlternateColorCodes('&', args[1]).replaceAll("_", " "));
							npc.spawn(player.getLocation());
							npc.setName(ChatColor.translateAlternateColorCodes('&', args[1]).replaceAll("_", " "));
							npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, true);
						}else {
							NPCRegistry register = CitizensAPI.getNPCRegistry();
							NPC npc = register.createNPC(EntityType.PLAYER, ChatColor.translateAlternateColorCodes('&', args[1]));
							npc.spawn(player.getLocation());
							npc.setName(ChatColor.translateAlternateColorCodes('&', args[1]).replaceAll("_", " "));
							npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, true);
						}
					}else {
						ChatUtils.sendMessage("Usage", "/npc <create> <playername> <type>", player);
					}
				}else if (args[0].equalsIgnoreCase("remove")) {
					CitizensAPI.getDefaultNPCSelector().getSelected(player).despawn();
					CitizensAPI.getDefaultNPCSelector().getSelected(player).destroy();
					ChatUtils.sendMessage("NPC", "Removed npc", player);
				}else if (args[0].equalsIgnoreCase("types")) {
					for (EntityType type : EntityType.values()) {
						ChatUtils.sendMessage("NPC", type.name(), player);
					}
				}else if (args[0].equalsIgnoreCase("removeall")) {
					CitizensAPI.getNPCRegistry().deregisterAll();
					ChatUtils.sendMessage("NPC", "Removed all npcs", player);
				}else if (args[0].equalsIgnoreCase("rename")) {
					if (args.length > 1) {
						NPC npc = CitizensAPI.getDefaultNPCSelector().getSelected(player);
						npc.setName(ChatColor.translateAlternateColorCodes('&', args[1]).replaceAll("_", " "));
						ChatUtils.sendMessage("NPC", "Renamed npc to " + ChatColor.translateAlternateColorCodes('&', args[1]), player);
					}else {
						ChatUtils.sendMessage("Usage", "/npc <rename> <name>", player);
					}
				}
			}else {
				ChatUtils.sendMessage("Usage", "/npc <create|remove|removeall|rename|types>", player);
			}
		}
		return false;
	}

}