package me.justicepro.beehub.Inventories;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import me.justicepro.beehub.Ranks.Rank;
import me.justicepro.ukit.Inventories.InventoryBuilder;
import me.justicepro.ukit.Inventories.ItemBuilder;

public class InventoryManager {

	public static Inventory getErrorInventory() {
		return new InventoryBuilder(9 * 2, "&cError Troubleshooter")
				.addItems(new ItemBuilder(Material.PAPER)
						.setName("&aI have a rank called &cERROR&a?").build(),
						new ItemBuilder(Material.PAPER).setName("&aI don't have my rank?").build()
						).build();
	}
	
	@SuppressWarnings("deprecation")
	public static Inventory getPunishInventory() {
		return new InventoryBuilder(9 * 2, "&4Punish Menu").
				addItems(new ItemBuilder(Material.GOLD_BLOCK).setName("&b&lHelper").build(),
						new ItemBuilder(DyeColor.RED).setName("&4Warn Player").build(),
						// Skip Lane
						new ItemBuilder(Material.AIR).build(), new ItemBuilder(Material.AIR).build(),
						new ItemBuilder(Material.AIR).build(), new ItemBuilder(Material.AIR).build(),
						new ItemBuilder(Material.AIR).build(), new ItemBuilder(Material.AIR).build(),
						new ItemBuilder(Material.AIR).build(),
						
						new ItemBuilder(Material.GOLD_BLOCK).setName("&6&lMod").build(),
						new ItemBuilder(DyeColor.RED).setName("&4Kick Player").build(),
						new ItemBuilder(DyeColor.RED).setName("&4Ban Player").build(),
						new ItemBuilder(DyeColor.LIME).setName("&aUnban Player").build()).build();
	}

//	public static Inventory getStaffInventory() {
//		return new InventoryBuilder(9 * 1, "&9Staff").addItems(
//				new ItemBuilder("JusticePro").setName(Rank.LEADER.getPrefix() + "JusticePro").build(),
//				new ItemBuilder("Wild_Yogurt").setName(Rank.LEADER.getPrefix() + "Wild_Yogurt").build(),
//				new ItemBuilder("SuperGamersGames").setName(Rank.LEADER.getPrefix() + "SuperGamersGames").build())
//				.build();
//	}

	public static Inventory getBuyInventory() {
		return getShopInventory(true);
	}
	
	public static Inventory getSellInventory() {
		return getShopInventory(false);
	}
	
	public static Inventory getShopInventory(boolean buy) {
		InventoryBuilder builder = null;
		if (buy) {
			builder = new InventoryBuilder(9 * 3, "&eBuy");
			builder.setItem(18, new ItemBuilder(DyeColor.LIME).setName("&aSpecial Rank").buildShopItem(1000000000, false));
		}else {
			builder = new InventoryBuilder(9 * 3, "&eSell");
		}
		builder.addItems(
				new ItemBuilder(Material.DIRT).buildShopItem(1),
				new ItemBuilder(Material.COBBLESTONE).buildShopItem(2),
				new ItemBuilder(Material.STONE).buildShopItem(3),
				new ItemBuilder(Material.WOOD).buildShopItem(4),
				new ItemBuilder(Material.LOG).buildShopItem(5),
				new ItemBuilder(Material.IRON_INGOT).buildShopItem(10),
				new ItemBuilder(Material.GOLD_INGOT).buildShopItem(20),
				new ItemBuilder(Material.DIAMOND).buildShopItem(80),
				new ItemBuilder(Material.EMERALD).buildShopItem(40));
		return builder.build();
	}
	
}