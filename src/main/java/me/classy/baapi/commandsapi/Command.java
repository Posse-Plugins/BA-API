package me.classy.baapi.commandsapi;

import org.bukkit.command.CommandSender;

public interface Command {
	void execute(CommandSender sender, String[] args);
	String getName();
	String[] getAliases();
	String getDescription();
	String getUsage();
	String getPermission();
	boolean hasPermission(CommandSender sender);
}
