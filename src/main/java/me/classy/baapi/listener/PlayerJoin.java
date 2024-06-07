package me.classy.baapi.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import me.classy.baapi.BAAPI;
import me.classy.baapi.actionbarapi.ActionBar;
import me.classy.baapi.bossbarapi.BossBar;
import me.classy.baapi.utility.Util;

import java.io.IOException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
		
		ActionBar actionBar = new ActionBar(BAAPI.getInstance().getConfig().getString(Util.setColor("actionbar-message")), BAAPI.getInstance().getConfig().getInt("actionbar-duration"));
		if (BAAPI.getInstance().getConfig().getBoolean("actionbar-sendtoall") == false) {
			actionBar.sendToPlayer(p);
		} else {
			actionBar.sendToAll();
		}
		
		BossBar bossBar = new BossBar(BAAPI.getInstance().getConfig().getString(Util.setColor("bossbar-message")), BAAPI.getInstance().getConfig().getDouble("bossbar-health"));
		bossBar.setColor(BAAPI.getInstance().getConfig().getString("bossbar-color").toUpperCase());
		bossBar.setStyle(BAAPI.getInstance().getConfig().getString("bossbar-style").toUpperCase());
		if (BAAPI.getInstance().getConfig().getBoolean("bossbar-removefromplayer") == true) {
			bossBar.removeFromPlayer(p);
		} else {
			bossBar.sendToPlayer(p);
		}
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        e.setMotd(BAAPI.getInstance().getConfig().getString(Util.setColor("motd")));
        e.setMaxPlayers(1000);
    }
}
