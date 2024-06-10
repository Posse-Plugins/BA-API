package me.classy.baapi.commandsapi;

import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.HashMap;

import me.classy.baapi.utility.Util;

public class CommandRegistery {
	private final Map<String, Command> commands = new HashMap<>();
	
	public void registerCommand(Command c) {
		commands.put(c.getName().toLowerCase(), c);
		for (String alias : c.getAliases()) {
			commands.put(alias.toLowerCase(), c);
		}
	}
	
	public Command getCommand(String name) {
		return commands.get(name.toLowerCase());
	}
	
	public boolean executeCommand(CommandSender sender, String name, String[] args) {
		Command c = getCommand(name);
		if (c != null) {
			if (!c.hasPermission(sender)) {
				sender.sendMessage(Util.setColor("&cYou don't have permission to use this command."));
				return true;
			}
			
			if (args.length == 0) {
				sender.sendMessage(Util.setColor("&eUsage: &b" + c.getUsage()));
				return true;
			}
			
			c.execute(sender, args);
			return true;
		}
		return false;
	}
}
