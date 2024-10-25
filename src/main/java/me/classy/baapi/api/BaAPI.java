// Please do not touch

package me.classy.baapi.api;

import org.bukkit.entity.Player;

import me.classy.baapi.holo.Hologram;
import me.classy.baapi.gui.AbstractGUI;

public interface BaAPI {
	Hologram getHologramManager();

    void openCustomGUI(Player player, AbstractGUI guiName);
}
