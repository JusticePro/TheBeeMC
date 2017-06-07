package me.justicepro.beehub.Event;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.justicepro.beehub.Plugin;
import me.justicepro.beehub.Data.PlayerData;
import me.justicepro.beehub.Inventories.InventoryManager;
import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.beehub.Utils.ChatUtils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class Events implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();

		if (inv.getName().equalsIgnoreCase(InventoryManager.getErrorInventory().getName())) {
			event.setCancelled(true);
			if (event.getCurrentItem().equals(InventoryManager.getErrorInventory().getItem(0))) {
				if (Rank.ERROR.inRank(player)) {
					ChatUtils.sendMessage("ERROR", "You appear to have a bugged rank. If you have a bugged rank it'll be set to &8[&4&lERROR&8]&r. We're going to set you to Default rank.", player);
					Rank.DEFAULT.setRank(player);
				}else {
					ChatUtils.sendMessage("ERROR", "You don't have a bugged rank.", player);
				}
			}

			if (event.getCurrentItem().equals(InventoryManager.getErrorInventory().getItem(1))) {
				ChatUtils.sendMessage("ERROR", "If you don't have your rank. Contact an Admin.", player);
			}
		}

		if (inv.getName().equalsIgnoreCase(InventoryManager.getPunishInventory().getName())) {
			event.setCancelled(true);
			PlayerData data = new PlayerData(player.getName());
			if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Warn Player")) {
				player.performCommand("hub:warn " + data.get("punishplayer") + " " + data.get("punishreason"));
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Kick Player")) {
				player.performCommand("hub:kick " + data.get("punishplayer") + " " + data.get("punishreason"));
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Ban Player")) {
				player.performCommand("hub:ban " + data.get("punishplayer") + " " + data.get("punishreason"));
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Unban Player")) {
				player.performCommand("hub:unban " + data.get("punishplayer"));
			}
		}

		if (inv.getName().equalsIgnoreCase(InventoryManager.getStaffInventory().getName())) {
			event.setCancelled(true);
		}

		if (inv.getName().equalsIgnoreCase(InventoryManager.getBuyInventory().getName())) {
			event.setCancelled(true);
			PlayerData data = new PlayerData(player.getName());

			if (event.getCurrentItem().getType()!=Material.AIR) {
				int cost = Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(0).split(ChatColor.BLUE + "")[1].split(" ")[0]);

				if (event.getCurrentItem().getType().equals(Material.INK_SACK) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&aSpecial Rank"))) {
					if (data.getCoins() >= 1000000000) {
						if (Rank.SPECIAL.hasPermission(player)) {
							ChatUtils.sendMessage("Shop", "You already have that item.", player);
						}else {
							for (Player target : Bukkit.getOnlinePlayers()) {
								ChatUtils.sendMessage("Shop", player.getDisplayName() + " just bought Special Rank!", target);
							}
							Rank.SPECIAL.setRank(player);
							data.setRank(Rank.SPECIAL);
							data.setCoins(data.getCoins() - 1000000000);
						}
					}else {
						ChatUtils.sendMessage("Shop", "You can't afford that item.", player);
					}
				}else {
					if (event.getClick().isRightClick()) {
						if (data.getCoins() >= cost * 64) {
							data.setCoins(data.getCoins() - cost * 64);
							for (int i = 0; i < 64; i++) {
								player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
							}
							ChatUtils.sendMessage("Shop", "You bought " + event.getCurrentItem().getItemMeta().getDisplayName() + "s" + ChatColor.RESET + " for " + cost * 64 + "$", player);
						}else {
							ChatUtils.sendMessage("Shop", "You can't afford that item.", player);
						}
					}else {
						if (data.getCoins() >= cost) {
							data.setCoins(data.getCoins() - cost);
							player.getInventory().addItem(new ItemStack(event.getCurrentItem().getType()));
							ChatUtils.sendMessage("Shop", "You bought " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + " for " + cost + "$", player);
						}else {
							ChatUtils.sendMessage("Shop", "You can't afford that item.", player);
						}
					}
				}
			}
		}

		if (inv.getName().equalsIgnoreCase(InventoryManager.getSellInventory().getName())) {
			event.setCancelled(true);
			PlayerData data = new PlayerData(player.getName());

			if (event.getCurrentItem().getType()!=Material.AIR) {
				int cost = Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(0).split(ChatColor.BLUE + "")[1].split(" ")[0]);
				if (event.getClick().isRightClick()) {
					if (player.getInventory().contains(event.getCurrentItem().getType(), 64)) {
						data.setCoins(data.getCoins() + cost * 64);
						player.getInventory().removeItem(new ItemStack(event.getCurrentItem().getType(), 64));
						ChatUtils.sendMessage("Shop", "You sold " + event.getCurrentItem().getItemMeta().getDisplayName() + "s" + ChatColor.RESET + " for " + cost * 64 + "$", player);
					}else {
						ChatUtils.sendMessage("Shop", "You don't have enough of that item.", player);
					}
				}else {
					if (player.getInventory().contains(event.getCurrentItem().getType(), 1)) {
						data.setCoins(data.getCoins() + cost);
						player.getInventory().removeItem(new ItemStack(event.getCurrentItem().getType()));
						ChatUtils.sendMessage("Shop", "You sold " + event.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.RESET + " for " + cost + "$", player);
					}else {
						ChatUtils.sendMessage("Shop", "You don't have enough of that item.", player);
					}
				}
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		PlayerData data = new PlayerData(event.getPlayer().getName());
		for (Player player : event.getPlayer().getWorld().getPlayers()) {
			player.sendMessage(event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + event.getMessage());
		}
		event.setCancelled(true);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		PlayerData data = new PlayerData(event.getPlayer().getName());
		Player player = event.getPlayer();
		data.getRank().setRank(player);
		for (Player target : Bukkit.getOnlinePlayers()) {
			PacketPlayOutPlayerListHeaderFooter headerfooter = new PacketPlayOutPlayerListHeaderFooter();
			try {
				Field header = headerfooter.getClass().getDeclaredField("a");
				Field footer = headerfooter.getClass().getDeclaredField("b");
				header.setAccessible(true);
				footer.setAccessible(true);
				header.set(headerfooter, ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "\"&eThe&lBeeMC\n&bHome of &aSuper Fun&b Minigames\"")));
				footer.set(headerfooter, ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "\"&a&lstore.BeeMC.com\"")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			((CraftPlayer) target).getHandle().playerConnection.sendPacket(headerfooter);
		}
		if (data.getBanned()) {
			if (data.getBanReason() != null) {
				player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&aYou've been banned.\n&6Reason: \"" + data.getBanReason() + "\""));
			}else {
				player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&aYou've been banned."));
			}

		}
		data.getRank().setRank(player);
		for (Player target : Bukkit.getOnlinePlayers()) {
			if (target.getWorld()==player.getWorld()) {
				target.showPlayer(player);
				player.showPlayer(target);
			}else {
				target.hidePlayer(player);
				player.hidePlayer(target);
			}
		}
		for (Player target1 : player.getWorld().getPlayers()) {
			ChatUtils.sendJoinMessage(player.getDisplayName(), target1);
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		Player player = event.getPlayer();
		for (Player target : player.getWorld().getPlayers()) {
			ChatUtils.sendLeaveMessage(player.getDisplayName(), target);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String world = player.getWorld().getName();
		boolean allowBuild = world.equalsIgnoreCase("FreeBuild-1");
		if (!allowBuild) {
			if (Rank.BUILDER.hasPermission(player)) {
				if (!player.getGameMode().equals(GameMode.CREATIVE)) {
					ChatUtils.sendMessage("Permissions", "You must be in creative to break blocks.", player);
					event.setCancelled(true);
				}
			}else {
				Rank.BUILDER.sendPermissionFail(player);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		String world = player.getWorld().getName();
		boolean allowBuild = world.equalsIgnoreCase("FreeBuild-1");
		if (!allowBuild) {
			if (Rank.BUILDER.hasPermission(player)) {
				if (!player.getGameMode().equals(GameMode.CREATIVE)) {
					ChatUtils.sendMessage("Permissions", "You must be in creative to place blocks.", player);
					event.setCancelled(true);
				}
			}else {
				Rank.BUILDER.sendPermissionFail(player);
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		for (Player target : Bukkit.getOnlinePlayers()) {
			Plugin.showPlayerScoreboard(target);
		}
		for (Player target : Bukkit.getOnlinePlayers()) {
			PlayerData data = new PlayerData(target.getName());
			if (player==target) {
				data.getRank().setRankPrefix(player.getName(), player);
			}
			if (target.getWorld()==player.getWorld()) {
				target.showPlayer(player);
				player.showPlayer(target);
			}else {
				target.hidePlayer(player);
				player.hidePlayer(target);
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		if (event.getMessage().toLowerCase().startsWith("/npc")) {
			String message = event.getMessage();
			event.setMessage("/hub:npc" + message.substring("/npc".length()));
		}
	}
	
}
