package me.classy.baapi.gui.example;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.classy.baapi.gui.AbstractGUI;
import me.classy.baapi.gui.GUI;
import me.classy.baapi.gui.GUIUtils;
import me.classy.baapi.utility.Util;

public class ExampleGUI extends AbstractGUI {

	public ExampleGUI(Player player) {
		super(player);
	}

	@Override
	public Inventory createInventory() {
		Inventory exampleGUI = Bukkit.createInventory(null, 9, ChatColor.GRAY + "Example GUI");

        ItemStack blackPaneGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = blackPaneGlass.getItemMeta();
        meta.setDisplayName(" ");
        blackPaneGlass.setItemMeta(meta);

        for (int i = 0; i < exampleGUI.getSize(); i++) {
            exampleGUI.setItem(i, blackPaneGlass);
        }
		ItemStack itemOne = GUIUtils.creatsGUIItem(Material.ANVIL /* Material */, "Item Name", "Lore line 1", "Lore line 2", "Lore line 3, you can add more");
		GUI.setItem(exampleGUI, 1, itemOne);
		return exampleGUI;
	}

	@Override
	public void handleClick(InventoryClickEvent event) {
		event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

		if (clickedItem.getType() == Material.ANVIL) {
			player.sendMessage("Hello!"); // Simple...
		}
	}
}
