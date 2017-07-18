package me.justicepro.beehub.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.ukit.Chat.MessageBuilder;

public class ChatUtils {
	
	@SuppressWarnings("deprecation")
	public static void sendMessage(String prefix, String message, Player player) {
		player.sendMessage(new MessageBuilder().add("&9" + prefix + ">&7 " + message).build());
	}

	public static void sendJoinMessage(String playerDisplayName, Player player) {
		player.sendMessage(new MessageBuilder().add("&8Join> &7" + playerDisplayName).build());
	}
	
	public static void sendMessage(String prefix, String message, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add("&9" + prefix + ">&7 " + message).build());
	}

	public static void sendJoinMessage(String playerDisplayName, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add("&8Join> &7" + playerDisplayName).build());
	}

	public static void sendLeaveMessage(String playerDisplayName, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add("&8Quit> &7" + playerDisplayName).build());
	}
	public static void sendLeaveMessage(String playerDisplayName, Player player) {
		player.sendMessage(new MessageBuilder().add("&8Quit> &7" + playerDisplayName).build());
	}
}