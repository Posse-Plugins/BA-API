package me.classy.baapi.staff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ToggleStaffChatCommand implements CommandExecutor {

    public static final Map<Player, Boolean> staffChatEnabled = new HashMap<>();

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

        boolean isEnabled = staffChatEnabled.getOrDefault(p, false);
        staffChatEnabled.put(p, !isEnabled);

        p.sendMessage(ChatColor.AQUA + "[STAFFCHAT] " + (isEnabled ? ChatColor.RED + "Hello, I disabled Staff Chat for you." : ChatColor.GREEN + "Hello, I enabled Staff Chat for you."));
        return true;
    }

    public boolean isStaffChatEnabled(Player p) {
        return staffChatEnabled.getOrDefault(p, false);
    }

}
