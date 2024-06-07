package me.classy.baapi.actionbarapi;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    private final PacketPlayOutChat packet;
    @Getter
    private final int durationTicks;

    public ActionBar(String text, int durationSeconds) {
        this.packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
        this.durationTicks = (durationSeconds == -1) ? -1 : durationSeconds * 20;
    }

    public void sendToPlayer(Player p) {
        if (p.isOnline()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void sendToAll() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            sendToPlayer(p);
        }
    }

}
