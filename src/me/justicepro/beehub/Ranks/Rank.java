package me.justicepro.beehub.Ranks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Utils.ChatUtils;

public enum Rank {
	
	ERROR("&4&LERROR &4", "Rank.Broken-Rank", true),
	
	/* REGULAR */
	DEFAULT("&9", "Regular"),
	
	/* MISC */
	VIP("&a&lVIP &a", "VIP"),
	PRO("&9&lPRO &9", "PRO"),
	CHAMPION("&e&lCHAMPION &e", "CHAMPION"),
	
	BUILDER("&2&lBUILDER &2", "Builder"),
	
	/* STAFF */
	HELPER("&e&lTRANIEE &e", "Traniee"),
	MOD("&6&lMOD &6", "Mod"),
	SRMOD("&6&lSR.MOD &6", "SR.Mod"),
	YOUTUBE("&c&lYOUTUBE &c", "Youtube"),
	TWITCH("&5&lTWITCH &5", "Twitch"),
	ADMIN("&c&lADMIN &c", "Admin"),
	DEVELOPER("&e&lDEV &e", "Developer"),
	OWNER("&4&lOWNER &4", "Owner")
	
	
	/* END */;
	
	private String prefix;
	private String key;
	private boolean hidden;
	
	private Rank(String key) {
		this.prefix = "";
		this.key = key;
	}
	
	private Rank(String prefix, String key) {
		this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		this.key = key;
	}
	
	private Rank(String prefix, String key, boolean hidden) {
		this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		this.key = key;
		this.hidden = hidden;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setRank(Player player) {
		setRankPrefix(player.getName(), player);
		PlayerData data = new PlayerData(player.getName());
		data.setRank(this);
	}
	
	public void setRankPrefix(String name, Player player) {
		player.setPlayerListName(getPrefix() + name);
		player.setDisplayName(getPrefix() + name + ChatColor.RESET);
	}
	
	public void setRank(String player) {
		PlayerData data = new PlayerData(player);
		data.setRank(this);
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public String getKey() {
		return key;
	}
	
	public void sendPermissionFail(Player player) {
		ChatUtils.sendMessage("Permissions", "You don't have rank " + getPrefix().substring(0, getPrefix().length() - 3) + "&7.", player);
	}
	
	public void sendServerPermissionFail(Player player) {
		ChatUtils.sendMessage("Permissions", "You need &6" + getKey() + "&r to join that Server.", player);
	}
	
	public int index() {
		for (int i = 0; i < Rank.values().length; i++) {
			if (Rank.values()[i].getKey().equalsIgnoreCase(key)) {
				return i;
			}
		}
		return -1;
	}
	public boolean hasPermission(Player player) {
		PlayerData data = new PlayerData(player.getName());
		if (data.getRank().index() >= index()) {
			return true;
		}
		return false;
	}
	public boolean hasPermission(String player) {
		PlayerData data = new PlayerData(player);
		if (data.getRank().index() >= index()) {
			return true;
		}
		return false;
	}
	public boolean inRank(Player player) {
		PlayerData data = new PlayerData(player.getName());
		if (data.getRank().index() == index()) {
			return true;
		}
		return false;
	}
	public boolean inRank(String player) {
		PlayerData data = new PlayerData(player);
		if (data.getRank().index() == index()) {
			return true;
		}
		return false;
	}
	
	
	/* STATIC METHODS */
	
	public static Rank[] ranks() {
		ArrayList<Rank> ranks = new ArrayList<>();
		for (Rank rank : values()) {
			if (rank.hidden==false) {
				ranks.add(rank);
			}
		}
		return (Rank[]) ranks.toArray(new Rank[ranks.size()]);
	}
	
	public static void sendPermissionFail(String rank, Player player) {
		ChatUtils.sendMessage("Permissions", "You don't have rank &6" + rank + "&r.", player);
	}
	
}