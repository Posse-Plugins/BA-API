package me.classy.baapi.staff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.classy.baapi.rank.*;

public class StaffInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUsage: &b/staffinfo <player> OR /info <player>"));
            return true;
        }

        Player target = sender.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUnable to find " + args[0] + "."));
            return true;
        }

        if (!target.hasPermission("staffchat.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + args[0] + " is not a Staff Member."));
            return true;
        }

        for (Player p : target.getServer().getOnlinePlayers()) {
            Rank rank = new Rank(p);
            String prefix = rank.getRankPrefix();
            String suffix = rank.getRankSuffix();
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------------------------"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lSTAFF INFORMATION"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eName: " + prefix + target.getDisplayName()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eRank: " + suffix));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStatus: " + (target.isOnline() ? "&aOnline" : "&cOffline")));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m--------------------------"));
            return true;
        }
        return false;
    }
}
