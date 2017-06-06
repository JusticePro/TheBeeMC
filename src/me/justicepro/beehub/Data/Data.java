package me.justicepro.beehub.Data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class Data {
	
	private YamlConfiguration config;
	private File file;
	
	public Data(File file) {
		this.file = file;
		config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public Data(String file) {
		this.file = new File(Bukkit.getPluginManager().getPlugin("Hub").getDataFolder(), file);
		config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
	public File getFile() {
		return file;
	}
	
	public void save() {
		try {
			config.save(getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}