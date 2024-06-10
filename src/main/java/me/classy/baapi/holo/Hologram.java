package me.classy.baapi.holo;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Hologram {

    protected final JavaPlugin plugin;
    protected final UUID uuid;
    protected final Location location;
    @Getter
    protected final Map<Integer, ArmorStand> armorStands;
    protected final boolean showToAll;
    @Getter
    protected final List<String> lines;
    @Setter
    @Getter
    protected String name;
    @Setter
    @Getter
    protected boolean customNameVisible;

    public Hologram(JavaPlugin plugin, Location location, List<String> lines, boolean showToAll) {
        this.plugin = plugin;
        this.uuid = UUID.randomUUID();
        this.location = location;
        this.showToAll = showToAll;
        this.armorStands = new HashMap<>();
        this.lines = lines;
        this.name = "";
        this.customNameVisible = false;

        spawn();
    }

    public abstract Location getLocation();

    protected abstract ArmorStand spawnArmorStand(Location location, String text);

    public void despawn() {
        for (ArmorStand armorStand : armorStands.values()) {
            armorStand.remove();
        }
        armorStands.clear();
    }

    public void spawn() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Location spawnLocation = location.clone().add(0, i * 0.25, 0);
            ArmorStand armorStand = spawnArmorStand(spawnLocation, line);
            armorStands.put(i, armorStand);
        }
    }

    public void move(Location newLocation) {
        despawn();
        this.location.setX(newLocation.getX());
        this.location.setY(newLocation.getY());
        this.location.setZ(newLocation.getZ());
        spawn();
    }

    public void editLine(int lineNumber, String newLine) {
        ArmorStand armorStand = armorStands.get(lineNumber);
        if (armorStand != null) {
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', newLine));
        }
    }

    public UUID getUUID() {
        return uuid;
    }

    public Map<Integer, ArmorStand> getArmorStands() {
        return armorStands;
    }

    public List<String> getLines() {
        return lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCustomNameVisible() {
        return customNameVisible;
    }

    public void setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;
    }

    public abstract Object getId();
}
