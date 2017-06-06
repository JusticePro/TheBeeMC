package me.justicepro.beehub.Ranks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Utils.ChatUtils;

public enum Rank {
	
	ERROR("&8[&4&LERROR&8] &4", "Rank.Broken-Rank", true),
	
	/* REGULAR */
	DEFAULT("&9", "Regular"),
	
	/* MISC */
	SPECIAL("&8[&a&lSpecial&8] &2", "Special"),
	GOLD("&8[&e&lGold&8] &e", "Gold"),
	EMERALD("&8[&a&lEmerald&8] &a", "Emerald"),
	DIAMOND("&8[&b&lDiamond&8] &b", "Diamond"),
	MAGMA("&8[&c&lMagma&8] &e", "Magma"),
	
	BUILDER("&8[&b&lBuilder&8] &b", "Builder"),
	
	/* STAFF */
	HELPER("&8[&b&lHelper&8] &b", "Helper"),
	MOD("&8[&6&lMod&8] &6", "Mod"),
	YOUTUBE("&8[&c&lYoutube&8] &c", "Youtube"),
	TWITCH("&8[&5&lTwitch&8] &5", "Twitch"),
	ADMIN("&8[&9&lAdmin&8] &9", "Admin"),
	SERVERHOSTER("&8[&4Server Hoster&8] &c", "Server-Hoster"),
	DEVELOPER("&8[&6&LDeveloper&8] &6", "Developer"),
	LEADER("&8[&4&LLeader&8] &4", "Leader"),
	OWNER("&8[&4&l&LOwner&8] &4", "Owner")
	
	
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
		ChatUtils.sendMessage("Permissions", "You don't have rank &6" + getKey() + "&r.", player);
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