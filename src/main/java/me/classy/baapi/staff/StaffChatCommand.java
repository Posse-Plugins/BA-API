package me.classy.baapi.staff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.classy.baapi.rank.*;

public class StaffChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "e, only smol e...");
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("staffchat.use")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need to be a &9JR HELPER &cto do this."));
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUsage: &b/staffchat <message> OR /sc <message>"));
            return true;
        }

        Rank rank = new Rank(p);
        String prefix = rank.getRankPrefix();
        String message = String.join(" ", args);
        String staffChatMessage = ChatColor.translateAlternateColorCodes('&', "&b[STAFFCHAT] " + prefix + p.getDisplayName() + "&f&r: " + message);

        for (Player onlinePlayer : p.getServer().getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("staffchat.use")) {
                onlinePlayer.sendMessage(staffChatMessage);
            }
        }
        return true;
    }
}
