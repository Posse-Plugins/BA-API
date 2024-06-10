package me.classy.baapi.commands;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import me.classy.baapi.commandsapi.BaseCommand;
import me.classy.baapi.utility.Util;

public class ECommand extends BaseCommand {
	
	public ECommand() {
		super("example", new String[]{"ex", "e"}, "I made this command for example, if you want to make a command then use this for example.", "/example <message> OR /ex <message> OR /e <message>", "e.command");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length > 0) {
				String message = String.join(" ", args);
				p.sendMessage(Util.setColor("&b[TEST] &f" + message));
			} else {
				p.sendMessage(Util.setColor("&eUsage: &b" + getUsage()));
			}
		} else {
			sender.sendMessage("Nuh uh");
		}
	}
}
