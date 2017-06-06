package me.justicepro.beehub.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.justicepro.ukit.Chat.MessageBuilder;

public class ChatUtils {
	
	@SuppressWarnings("deprecation")
	public static void sendMessage(String prefix, String message, Player player) {
		player.sendMessage(new MessageBuilder().add(" &8| &a" + prefix + " &8| " + message).build());
	}

	public static void sendJoinMessage(String playerDisplayName, Player player) {
		player.sendMessage(new MessageBuilder().add(playerDisplayName + " has joined the game.").build());
	}
	
	public static void sendMessage(String prefix, String message, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add(" &8| &a" + prefix + " &8| " + message).build());
	}

	public static void sendJoinMessage(String playerDisplayName, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add(playerDisplayName + " has joined the game.").build());
	}

	public static void sendLeaveMessage(String playerDisplayName, CommandSender sender) {
		sender.sendMessage(new MessageBuilder().add(playerDisplayName + " has left the game.").build());
	}
	public static void sendLeaveMessage(String playerDisplayName, Player player) {
		player.sendMessage(new MessageBuilder().add(playerDisplayName + " has left the game.").build());
	}
}