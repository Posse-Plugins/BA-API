# BA-API

BossBarAPI, ActionBarAPI & TitleAPI for 1.8.X (Very Lightweight)

## Maven Dependency
```xml
<repository>
	<id>github</id>
    <name>GitHub Packages</name>
	<url>https://maven.pkg.github.com/Posse-Plugins/BA-API</url>
</repository>
```

```xml
<dependency>
	<groupId>me.classy</groupId>
	<artifactId>ba-api</artifactId>
	<version>2.1</version>
</dependency>
```

## Usage/Examples

```java
// BossBarAPI
BossBar bossBar = new BossBar("Hello World", 100); // Hello World bossbar with full health
bossBar.setColor("PURPLE"); // Purple color (default)
bossBar.setStyle("SOLID"); // Solid style (default)
bossBar.sendToPlayer(p); // Send to a player
bossBar.updateHealth(p, 50) // Changes the health to 50
bossBar.removeFromPlayer(p); // Remove from the player

// ActionBarAPI
ActionBar actionBar = new ActionBar("Hello World", 2000); // Hello World for 100 seconds
actionBar.sendToPlayer(p); // Send the actionbar to a specific player
actionBar.sendToAll(); // Send the actionbar to all the players online on the server

// TitleAPI
Title title = new Title();
title.sentToPlayer(p, "Hello World (Title)", "Hello World (Subtitle)", 10, 70, 20); // Hello World (title) and Hello World (subtitle) for 3.5 seconds (70 ticks)
title.sentToAll("Hello World (Title)", "Hello World (Subtitle)", 10, 70, 20); // Hello World (title) and Hello World (subtitle) for 3.5 seconds (70 ticks) to all players

// Example Command
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
} // This is just an example on how you can create commands using our api.
```
## Tested Versions

* 1.8 <br>
If you test it in other versions make a pull request.

## Authors

- [@ClassyCoder1](https://www.github.com/ClassyCoder1)

## Credits
- [@vNoobblade](https://www.github.com/vNoobblade) **for the readme and actionbarapi.**
