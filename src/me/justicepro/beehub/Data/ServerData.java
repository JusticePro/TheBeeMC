package me.justicepro.beehub.Data;

import java.util.ArrayList;
import java.util.List;

public class ServerData extends Data {

	public ServerData() {
		super("Server.yml");
	}
	
	public void setMotd(String motd) {
		getConfig().set("motd", motd);
		save();
	}
	
	public String getMotd() {
		if (!getConfig().contains("motd")) {
			setMotd("MOTD");
		}
		return getConfig().getString("motd");
	}

	public void setServerList(List<String> servers) {
		getConfig().set("servers", servers);
		save();
	}
	
	public List<String> getServerList() {
		return getConfig().getStringList("servers");
	}
	
	public void setServerName(String name) {
		getConfig().set("servername", name);
		save();
	}
	
	public String getServerName() {
		if (!getConfig().contains("servername")) {
			setServerName("Server-1");
		}
		return getConfig().getString("servername");
	}
	
}