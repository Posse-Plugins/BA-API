package me.classy.baapi.staff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.classy.baapi.rank.*;

public class StaffListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cE, only smol e..."));
            return true;
        }

        Player p = (Player) sender;
        StringBuilder staffList = new StringBuilder(ChatColor.translateAlternateColorCodes('&', "&e&lSTAFF LIST\n"));

        for (Player onlinePlayer : p.getServer().getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("staffchat.use")) {
                Rank rank = new Rank(onlinePlayer);
                String prefix = rank.getRankPrefix();
                staffList.append(ChatColor.translateAlternateColorCodes('&', prefix + onlinePlayer.getDisplayName())).append("\n");
            }
        }

        p.sendMessage(staffList.toString());
        return true;
    }
}
