package me.classy.baapi.commandsapi;

import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;

import me.classy.baapi.BAAPI;

public class ICommandExecutor implements CommandExecutor {
	private final CommandRegistery commandRegistery;
	
	public ICommandExecutor(CommandRegistery commandRegistery) {
		this.commandRegistery = commandRegistery;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return commandRegistery.executeCommand(sender, label, args);
	}
}
