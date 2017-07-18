package me.justicepro.beehub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.justicepro.beehub.Commands.CommandManager;
import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Event.Events;
import me.justicepro.ukit.Scoreboard.SidebarUtils;

public class Plugin extends JavaPlugin {
	
//	public Plugin() {
//		try {
//			File file = new File(getClass().getResource("libs/NoCheatPlus.jar").toURI());
//			Bukkit.getPluginManager().loadPlugin(file);
//		} catch (URISyntaxException | UnknownDependencyException | InvalidPluginException | InvalidDescriptionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void onEnable() {
		CommandManager manager = new CommandManager();
		manager.registerAll();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		
	}
	
	public static void showPlayerScoreboard(Player target) {
		PlayerData data = new PlayerData(target.getName());
		SidebarUtils.showSidebarDisplay(target,
				ChatColor.translateAlternateColorCodes('&', "&b&lArticunoPVP"),
				"",
				ChatColor.translateAlternateColorCodes('&', "&b&lRank"),
				ChatColor.translateAlternateColorCodes('&', "&9" + data.getRank().getKey()),
				" ",
				ChatColor.translateAlternateColorCodes('&', "&e&lTokens"),
				ChatColor.translateAlternateColorCodes('&', "&7" + data.getCoins()),
				"  ",
				ChatColor.translateAlternateColorCodes('&', "&lServer"), 
				ChatColor.translateAlternateColorCodes('&', "&7" + target.getWorld().getName()),
				"   ",
				ChatColor.translateAlternateColorCodes('&', "&6play.ArticunoPVP.com"));
	}
	
	
	
}