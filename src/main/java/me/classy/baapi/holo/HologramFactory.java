package me.classy.baapi.holo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class HologramFactory {

    private final JavaPlugin plugin;

    public HologramFactory(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public Hologram createHologram(String type, Location location, List<String> lines, boolean showToAll, String name, boolean customNameVisible) {
        Hologram hologram = createHologram(type, location, lines, showToAll);
        hologram.setName(name);
        hologram.setCustomNameVisible(customNameVisible);
        return hologram;
    }

    public Hologram createHologram(String type, Location location, List<String> lines, boolean showToAll) {
        switch (type.toLowerCase()) {
            case "static":
                return new StaticHologram(plugin, location, lines, showToAll);
            case "animated":
                return new AnimatedHologram(plugin, location, lines, Arrays.asList(ChatColor.values()), showToAll);
            default:
                throw new IllegalArgumentException("Invalid hologram type: " + type);
        }
    }
}
