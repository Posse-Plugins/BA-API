package me.classy.baapi.holo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HologramManager {

    private static final double MIN_DISTANCE_BETWEEN_HOLOGRAMS = 1.5;
    private final JavaPlugin plugin;
    private final HologramFactory hologramFactory;
    private final Map<Integer, Hologram> hologramMap;

    public HologramManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hologramFactory = new HologramFactory(plugin);
        this.hologramMap = new HashMap<>();
    }

    public Hologram createHologram(int id, String type, Location location, List<String> lines, boolean showToAll, String name, boolean customNameVisible) {
        if (hologramMap.containsKey(id)) {
            plugin.getLogger().warning("Hologram with ID " + id + " already exists.");
            return null;
        }

        Hologram hologram = hologramFactory.createHologram(type, location, lines, showToAll, name, customNameVisible);
        hologramMap.put(id, hologram);
        return hologram;
    }

    public boolean deleteHologram(int id) {
        Hologram hologram = hologramMap.remove(id);
        if (hologram != null) {
            hologram.despawn();
        } else {
            plugin.getLogger().warning("Hologram with ID " + id + " not found.");
        }
        return false;
    }

    public void createHologram(int id, Hologram hologram) {
        if (hologramMap.containsKey(id)) {
            plugin.getLogger().warning("Hologram with ID " + id + " already exists.");
            return;
        }

        hologramMap.put(id, hologram);
    }

    public boolean editHologramLine(int id, int lineNumber, String newLine) {
        Hologram hologram = hologramMap.get(id);
        if (hologram != null) {
            hologram.editLine(lineNumber, newLine);
            return true;
        } else {
            plugin.getLogger().warning("Hologram with ID " + id + " not found.");
            return false;
        }
    }

    public void moveHologram(int id, Location newLocation) {
        Hologram hologram = hologramMap.get(id);
        if (hologram != null) {
            hologram.move(newLocation);
            adjustHologramPosition(hologram);
        } else {
            plugin.getLogger().warning("Hologram with ID " + id + " not found.");
        }
    }

    public void listHolograms(Player player) {
        List<Hologram> holograms = new ArrayList<>(hologramMap.values());
        if (holograms.isEmpty()) {
            player.sendMessage(ChatColor.YELLOW + "No holograms found.");
        } else {
            player.sendMessage(ChatColor.YELLOW + "List of holograms:");
            for (Hologram hologram : holograms) {
                player.sendMessage(ChatColor.GREEN + "- ID: " + hologram.getUUID() + ", Name: " + hologram.getName());
            }
        }
    }

    private void adjustHologramPosition(Hologram movedHologram) {
        for (Hologram hologram : hologramMap.values()) {
            if (hologram.getUUID() != movedHologram.getUUID()) {
                Location hologramLocation = hologram.getLocation();
                double distance = hologramLocation.distance(movedHologram.getLocation());
                if (distance < MIN_DISTANCE_BETWEEN_HOLOGRAMS) {
                    hologram.move(hologramLocation.clone().add(0, distance + MIN_DISTANCE_BETWEEN_HOLOGRAMS, 0));
                }
            }
        }
    }
}
