package me.classy.baapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import me.classy.baapi.titleapi.Title;
import me.classy.baapi.utility.Util;

public class SubtitleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Can't execute the command from console.");
			return true;
		}

		Player p = (Player) sender;

		if (!p.hasPermission("baapi-admin")) {
			p.sendMessage(Util.setColor("&cYou don't have permission to use this command."));
			return true;
		}
		Player target = Bukkit.getPlayer(args[1]);
		Title title = new Title();
		if (args[1] == "all") {
			title.sendToAll(null, args[0], 10, 70, 30);
			p.sendMessage(Util.setColor("Done!"));
			return true;
		} else if (args[1] == target.getName()) {
			title.sendToPlayer(target, null, args[0], 10, 70, 30);
			p.sendMessage(Util.setColor("Done!"));
			return true;
		}
		return false;
	}
}
