package me.classy.baapi.holo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class StaticHologram extends Hologram {

    public StaticHologram(JavaPlugin plugin, Location location, List<String> lines, boolean showToAll) {
        super(plugin, location, lines, showToAll);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    protected ArmorStand spawnArmorStand(Location location, String text) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, org.bukkit.entity.EntityType.ARMOR_STAND);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));

        if (!showToAll) {
            addPassengersWithinRadius(armorStand);
        }

        return armorStand;
    }

    private void addPassengersWithinRadius(ArmorStand armorStand) {
        for (Player player : location.getWorld().getPlayers()) {
            double distanceSquared = armorStand.getLocation().distanceSquared(player.getLocation());
            if (distanceSquared < 25) {
                armorStand.setPassenger(player);
            }
        }
    }

    @Override
    public void spawn() {
        for (int i = 0; i < getLines().size(); i++) {
            String line = getLines().get(i);
            Location spawnLocation = getLocation().clone().add(0, i * 0.25, 0);
            spawnArmorStand(spawnLocation, line);
        }
    }

    @Override
    public Object getId() {
        return null;
    }
}
