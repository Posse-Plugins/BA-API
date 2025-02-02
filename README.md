# BA-API

BossBarAPI, ActionBarAPI & TitleAPI for 1.8.X (Very Lightweight)

## Maven Dependency
```xml
<repositories>
    <repository>
	<id>github</id>
	<url>https://maven.pkg.github.com/Posse-Plugins/BA-API</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
	<groupId>me.classy</groupId>
	<artifactId>ba-api</artifactId>
	<version>2.2</version>
    </dependency>
</dependencies>
```

## Gradle Dependency
```groovy
repositories {
	maven {
		url "https://maven.pkg.github.com/Posse-Plugins/BA-API"
	}
}
```

```groovy
dependencies {
	implementation "me.classy:ba-api:2.2"
}
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
```
## Tested Versions

* 1.8
If you test it in other versions make a pull request. Please check [**pull request template.**](https://github.com/Posse-Plugins/BA-API/blob/master/PULL_REQUEST_TEMPLATE.md).

## Authors

- [@ClassyCoder1](https://github.com/ClassyCoder1)
- [@vNoobblade](https://github.com/vNoobblade)

## Credits
- [@vNoobblade](https://github.com/vNoobblade) **for the readme and actionbarapi.**
