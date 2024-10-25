// Please do not touch

package me.classy.baapi.api;

import org.bukkit.entity.Player;

import me.classy.baapi.BAAPI;
import me.classy.baapi.holo.Hologram;
import me.classy.baapi.gui.GUI;
import me.classy.baapi.gui.AbstractGUI;

public class BaAPIImpl implements BaAPI {

    private final GUI gui;
    private final BAAPI plugin;
    private final Hologram hologram;

    public BaAPIImpl(GUI gui, Hologram hologram, BAAPI plugin) {
        this.gui = gui;
        this.hologram = hologram;
        this.plugin = plugin;
    }

    @Override
    public void openCustomGUI(Player player, AbstractGUI guiName) {
        gui.openGUI(player, guiName);
    }

    @Override
    public Hologram getHologramManager() {
        return hologram;
    }
}
