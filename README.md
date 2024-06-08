# BA-API

BossBarAPI & ActionBarAPI for 1.8.X (Very Lightweight)

## Maven
```xml
<repositories>
	<repository>
		<id>github</id>
    <name>GitHub Packages</name>
		<url>https://maven.pkg.github.com/Posse-Plugins/BossBarAPI</url>
	</repository>
</repositories>
```

```xml
<dependency>
	<groupId>me.classy</groupId>
	<artifactId>baapi</artifactId>
	<version>1.0</version>
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
```
## Tested Versions

* 1.8 <br>
If you test it in other versions make a pull request.

## Authors

- [@ClassyCoder1](https://www.github.com/ClassyCoder1)

## Credits
- [@vNoobblade](https://www.github.com/vNoobblade) **for the readme and actionbarapi.**
