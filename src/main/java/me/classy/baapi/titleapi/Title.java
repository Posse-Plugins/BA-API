package me.classy.baapi.titleapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title {
	
	public void sendToPlayer(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		try {
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			
			Class<?> enumTitleActionClass = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0];
			Class<?> chatComponentClass = getNMSClass("IChatBaseComponent");
			Class<?> packetClass = getNMSClass("PacketPlayOutTitle");
			
			Constructor<?> chatConstructor = getNMSClass("ChatComponentText").getConstructor(String.class);
			
			Object chatTitle = chatConstructor.newInstance(title);
			Object chatSubtitle = chatConstructor.newInstance(subTitle);
			
			Constructor<?> titleConstructor = packetClass.getConstructor(enumTitleActionClass, chatComponentClass, int.class, int.class, int.class);
			
			Object packetTitle = titleConstructor.newInstance(enumTitleActionClass.getField("TITLE").get(null), chatTitle, fadeIn, stay, fadeOut);
			Object packetSubtitle = titleConstructor.newInstance(enumTitleActionClass.getField("SUBTITLE").get(null), chatSubtitle, fadeIn, stay, fadeOut);
			
			sendPacket(playerConnection, packetTitle);
			sendPacket(playerConnection, packetSubtitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			sendToPlayer(p, title, subTitle, fadeIn, stay, fadeOut);
		}
	}
	
	private void sendPacket(Object playerConnection, Object packet) throws Exception {
		playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
	}
	
	private Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
