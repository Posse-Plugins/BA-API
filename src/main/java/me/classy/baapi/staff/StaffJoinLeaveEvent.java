package me.classy.baapi.staff;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.classy.baapi.rank.*;

public class StaffJoinLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("staffchat.use")) {

            Rank rank = new Rank(p);

            String prefix = rank.getRankPrefix();

            String staffJoinMessage = ChatColor.translateAlternateColorCodes('&', "&b[STAFFCHAT] " + prefix + p.getDisplayName() + " &ejoined!");

            for (Player onlinePlayer : p.getServer().getOnlinePlayers()) {
                if (onlinePlayer.hasPermission("staffchat.use")) {
                    onlinePlayer.sendMessage(staffJoinMessage);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("staffchat.use")) {

            Rank rank = new Rank(p);

            String prefix = rank.getRankPrefix();

            String staffQuitMessage = ChatColor.translateAlternateColorCodes('&', "&b[STAFFCHAT] " + prefix + p.getDisplayName() + " &eleft!");

            for (Player onlinePlayer : p.getServer().getOnlinePlayers()) {
                if (onlinePlayer.hasPermission("staffchat.use")) {
                    onlinePlayer.sendMessage(staffQuitMessage);
                }
            }
        }
    }
}
