package me.classy.baapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class AbstractGUI {

    protected Player player;

    public AbstractGUI(Player player) {
        this.player = player;
    }

    public abstract Inventory createInventory();

    public abstract void handleClick(InventoryClickEvent event);

    public void open() {
        GUI.openGUI(player, this);
    }
}
