package me.classy.baapi.holo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AnimatedHologram extends Hologram {

    private final List<ChatColor> colors;

    public AnimatedHologram(JavaPlugin plugin, Location location, List<String> lines, List<ChatColor> colors, boolean showToAll) {
        super(plugin, location, lines, showToAll);
        this.colors = colors;
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
    public Location getLocation() {
        return location;
    }

    @Override
    public void spawn() {
        for (int i = 0; i < getLines().size(); i++) {
            String line = getLines().get(i);
            ChatColor color = colors.get(i % colors.size());
            Location spawnLocation = getLocation().clone().add(0, i * 0.25, 0);
            ArmorStand armorStand = spawnArmorStand(spawnLocation, color + line);
            getArmorStands().put(i, armorStand);
        }
    }

    @Override
    public Object getId() {
        return null;
    }
}
