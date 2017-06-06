package me.justicepro.beehub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.justicepro.beehub.Commands.CommandManager;
import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Event.Events;
import me.justicepro.ukit.Scoreboard.SidebarUtils;

public class Plugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		CommandManager manager = new CommandManager();
		manager.registerAll();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
	}
	
	public static void showPlayerScoreboard(Player target) {
		PlayerData data = new PlayerData(target.getName());
		SidebarUtils.showSidebarDisplay(target,
				ChatColor.translateAlternateColorCodes('&', "&6&lThe&e&lBee"),
				"",
				ChatColor.translateAlternateColorCodes('&', "&c&lRank"),
				ChatColor.translateAlternateColorCodes('&', "&9" + data.getRank().getKey()),
				" ",
				ChatColor.translateAlternateColorCodes('&', "&a&lTokens"),
				ChatColor.translateAlternateColorCodes('&', "&7" + data.getCoins()),
				"  ",
				ChatColor.translateAlternateColorCodes('&', "&b&lLuckyCrates"),
				ChatColor.translateAlternateColorCodes('&', "&7" + data.getCrates() + " "),
				"   ",
				ChatColor.translateAlternateColorCodes('&', "&lServer"), 
				ChatColor.translateAlternateColorCodes('&', "&7" + target.getWorld().getName()),
				"    ",
				ChatColor.translateAlternateColorCodes('&', "&8&l-----------"),
				ChatColor.translateAlternateColorCodes('&', "&6play.&eTheBee&6.com"));
	}
	
	
	
}