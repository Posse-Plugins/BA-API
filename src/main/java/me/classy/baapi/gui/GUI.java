package me.classy.baapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GUI {

    public static final Map<Player, AbstractGUI> openGuis = new HashMap<>();

    public static void openGUI(Player player, AbstractGUI gui) {
        Inventory inventory = gui.createInventory();
        player.openInventory(inventory);
        openGuis.put(player, gui);
    }

    public static AbstractGUI getOpenGUI(Player player) {
        return openGuis.get(player);
    }

    public static void closeGUI(Player player) {
        openGuis.remove(player);
    }
}
