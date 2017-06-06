package me.justicepro.beehub.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.justicepro.beehub.Plugin;
import me.justicepro.beehub.Ranks.Rank;

public class PlayerData extends Data {

	public PlayerData(String player) {
		super("playerdata/" + player + ".yml");
	}

	public void setRank(Rank rank) {
		YamlConfiguration config = getConfig();
		config.set("rank", rank.getKey());
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}
	public Rank getRank() {
		YamlConfiguration config = getConfig();
		if (config.contains("rank")) {
			for (Rank rank : Rank.values()) {
				if (config.getString("rank").equalsIgnoreCase(rank.getKey())) {
					return rank;
				}
			}
		}else {
			return Rank.DEFAULT;
		}
		setRank(Rank.ERROR);
		return Rank.ERROR;
	}

	public void setBanned(boolean banned) {
		YamlConfiguration config = getConfig();
		config.set("banned", banned);
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}

	public void setBanReason(String reason) {
		YamlConfiguration config = getConfig();
		config.set("banreason", reason);
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}

	public boolean getBanned() {
		YamlConfiguration config = getConfig();
		if (config.contains("banned")) {
			return config.getBoolean("banned");
		}
		return false;
	}

	public String getBanReason() {
		YamlConfiguration config = getConfig();
		if (config.contains("banreason")) {
			return config.getString("banreason");
		}
		return null;
	}

	public void set(String key, Object object) {
		YamlConfiguration config = getConfig();
		config.set(key, object);
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}

	public Object get(String key) {
		YamlConfiguration config = getConfig();
		if (!config.contains(key)) {
			return null;
		}
		return config.get(key);
	}

	public void setCoins(int coins) {
		YamlConfiguration config = getConfig();
		config.set("coins", coins);
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}
	public int getCoins() {
		YamlConfiguration config = getConfig();
		if (!config.contains("coins")) {
			setCoins(0);
		}
		return config.getInt("coins");
	}

	public void setCrates(int crates) {
		YamlConfiguration config = getConfig();
		config.set("luckycrates", crates);
		save();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
	}
	public int getCrates() {
		YamlConfiguration config = getConfig();
		if (!config.contains("luckycrates")) {
			setCrates(0);
		}
		return config.getInt("luckycrates");
	}

}