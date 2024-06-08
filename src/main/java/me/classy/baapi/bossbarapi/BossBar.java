package me.classy.baapi.bossbarapi;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.UUID;

public class BossBar {
    private final UUID uuid;
    private String title;
    private double health;
    private String color;
    private String style;

    public BossBar(String title, double health) {
        this.title = title;
        this.health = health;
        this.uuid = UUID.randomUUID();
        this.color = "PURPLE";
        this.style = "SOLID";
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void sendToPlayer(Player p) {
        try {
            Object entityWither = createWither(p);
            Object packet = createSpawnPacket(entityWither);
            sendPacket(p, packet);

            Object metaPacket = createMetadataPacket(entityWither);
            sendPacket(p, metaPacket);

            updateHealth(p, this.health);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void sendToAll() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			sendToPlayer(p);
		}
	}

    public void updateHealth(Player p, double health) {
        try {
            this.health = health;
            Object packet = createUpdateHealthPacket(p);
            sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFromPlayer(Player p) {
        try {
            Object packet = createRemovePacket(p);
            sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object createWither(Player p) throws Exception {
        Class<?> entityWitherClass = getNMSClass("EntityWither");
        Constructor<?> constructor = entityWitherClass.getConstructor(getNMSClass("World"));
        Object world = getCraftPlayer(p).getClass().getMethod("getHandle").invoke(getCraftPlayer(p));
        Object entityWither = constructor.newInstance(world);

        entityWitherClass.getMethod("setCustomName", String.class).invoke(entityWither, this.title);
        entityWitherClass.getMethod("setHealth", float.class).invoke(entityWither, this.health * 2);
        entityWitherClass.getMethod("setInvisibls", boolean.class).invoke(entityWither, true);

        return entityWither;
    }

    private Object createSpawnPacket(Object entityWither) throws Exception {
        Class<?> packetClass = getNMSClass("PacketPlayOutSpawnEntityLiving");
        Constructor<?> constructor = packetClass.getConstructor(getNMSClass("EntityLiving"));

        return constructor.newInstance(entityWither);
    }

    private Object createMetadataPacket(Object entityWither) throws Exception {
        Class<?> packetClass = getNMSClass("PacketPlayOutSpawnEntityMetadata");
        Constructor<?> constructor = packetClass.getConstructor(int.class, getNMSClass("DataWatcher"), boolean.class);

        return constructor.newInstance(getEntityId(entityWither), getDataWatcher(entityWither), true);
    }

    private Object createUpdateHealthPacket(Player p) throws Exception {
        Class<?> packetClass = getNMSClass("PacketPlayOutSpawnEntityMetadata");
        Constructor<?> constructor = packetClass.getConstructor(int.class, getNMSClass("DataWatcher"), boolean.class);
        Object entityWither = createWither(p);
        entityWither.getClass().getMethod("setHealth").invoke(entityWither, this.health * 2);

        return constructor.newInstance(getEntityId(entityWither), getDataWatcher(entityWither), true);
    }

    private Object createRemovePacket(Player p) throws Exception {
        Class<?> packetClass = getNMSClass("PacketPlayOutEntityDestroy");
        Constructor<?> constructor = packetClass.getConstructor(int[].class);
        Object entityWither = createWither(p);

        return constructor.newInstance(new int[]{getEntityId(entityWither)});
    }

    private void sendPacket(Player p, Object packet) throws Exception {
        Object handle = getCraftPlayer(p).getClass().getMethod("getHandle").invoke(getCraftPlayer(p));
        Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
        playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
    }

    public Object getCraftPlayer(Player p) {
        return p instanceof CraftPlayer ? p : null;
    }

    private int getEntityId(Object entityWither) throws Exception {
        return (int) entityWither.getClass().getMethod("getId").invoke(entityWither);
    }

    private Object getDataWatcher(Object entityWither) throws Exception {
        return entityWither.getClass().getMethod("getDataWatcher").invoke(entityWither);
    }

    private Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server.v1_8_R3" + name);
    }
}
