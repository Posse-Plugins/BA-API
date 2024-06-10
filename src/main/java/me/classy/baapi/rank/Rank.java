package me.classy.baapi.rank;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.classy.baapi.utility.Util;

public class Rank implements RankManager {
    private final Player player;

    private final String id;

    public Rank(Player player) {
        this.player = player;
        this.id = player.getPlayer().getUniqueId().toString();
    }

    @Override
    public String getRankPrefix() {
        if (this.player.getPlayer().hasPermission("rank.owner") || this.player.getPlayer().isOp()) {
            return "§c[OWNER] §c";
        }
        if (this.player.getPlayer().hasPermission("rank.coowner")) {
            return "§c[CO OWNER] §c";
        }
        if (this.player.getPlayer().hasPermission("rank.mainadmin")) {
            return "§c[MAIN ADMIN] §";
        }
        if (this.player.getPlayer().hasPermission("rank.admin")) {
            return "§c[ADMIN] §c";
        }
        if (this.player.getPlayer().hasPermission("rank.gm")) {
            return "§2[GM] §2";
        }
        if (this.player.getPlayer().hasPermission("rank.mod")) {
            return "§2[MOD] §2";
        }
        if (this.player.getPlayer().hasPermission("rank.helper")) {
            return "§9[HELPER] §9";
        }
        if (this.player.getPlayer().hasPermission("rank.jrhelper")) {
            return "§9[JR HELPER] §9";
        }
        if (this.player.getPlayer().hasPermission("rank.youtube")) {
            return "§c[§fYOUTUBE§c] §c";
        }
        if (this.player.getPlayer().hasPermission("rank.mvpplusplus")) {
            return "§6[MVP§c++§6] §6";
        }
        if (this.player.getPlayer().hasPermission("rank.mvpplus")) {
            return "§b[MVP§c+§b] §b";
        }
        if (this.player.getPlayer().hasPermission("rank.mvp")) {
            return "§b[MVP] §b";
        }
        if (this.player.getPlayer().hasPermission("rank.vipplus")) {
            return "§a[VIP§6+§a] §a";
        }
        if (this.player.getPlayer().hasPermission("rank.vip")) {
            return "§a[VIP] §a";
        }
        if (this.player.getPlayer().hasPermission("rank.default")) {
            return "§7";
        }
        return null;
    }

    public String getRankSuffix() {
        if (this.player.getPlayer().hasPermission("rank.owner")) {
            return "§cOWNER";
        }
        if (this.player.getPlayer().hasPermission("rank.coowner")) {
            return "§cCO OWNER";
        }
        if (this.player.getPlayer().hasPermission("rank.mainadmin")) {
            return "§cMAIN ADMIN";
        }
        if (this.player.getPlayer().hasPermission("rank.admin")) {
            return "§cADMIN";
        }
        if (this.player.getPlayer().hasPermission("rank.gm")) {
            return "§2GM";
        }
        if (this.player.getPlayer().hasPermission("rank.mod")) {
            return "§2MOD";
        }
        if (this.player.getPlayer().hasPermission("rank.helper")) {
            return "§9HELPER";
        }
        if (this.player.getPlayer().hasPermission("rank.jrhelper")) {
            return "§9JR HELPER";
        }
        if (this.player.getPlayer().hasPermission("rank.youtube")) {
            return "§cYOUTUBE";
        }
        if (this.player.getPlayer().hasPermission("rank.mvpplusplus")) {
            return "§6MVP§c++";
        }
        if (this.player.getPlayer().hasPermission("rank.mvpplus")) {
            return "§bMVP§c+";
        }
        if (this.player.getPlayer().hasPermission("rank.mvp")) {
            return "§bMVP";
        }
        if (this.player.getPlayer().hasPermission("rank.vipplus")) {
            return "VIP§6+";
        }
        if (this.player.getPlayer().hasPermission("rank.vip")) {
            return "§aVIP";
        }
        if (this.player.getPlayer().hasPermission("rank.default")) {
            return "§7Dafault";
        }
        return null;
    }

    public void sendMessage(String s) {
        this.player.getPlayer().sendMessage(Util.setColor(s));
    }

    public void setRank(String rank) {
        Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), "lp user " + this.player.getPlayer().getName() + " group set " + rank);
    }
}
