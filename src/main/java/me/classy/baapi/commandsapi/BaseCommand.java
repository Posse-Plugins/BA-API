package me.classy.baapi.commandsapi;

import org.bukkit.command.CommandSender;

public abstract class BaseCommand implements Command {
	private final String name;
	private final String[] aliases;
	private final String description;
	private final String usage;
	private final String permission;
	
	public BaseCommand(String name, String[] aliases, String description, String usage, String permission) {
		this.name = name;
		this.aliases = aliases;
		this.description = description;
		this.usage = usage;
		this.permission = permission;
	}
	
	@Override
	public abstract void execute(CommandSender sender, String[] args);

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String[] getAliases() {
		return aliases;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public boolean hasPermission(CommandSender sender) {
		return permission == null || sender.hasPermission(permission);
	}
}
