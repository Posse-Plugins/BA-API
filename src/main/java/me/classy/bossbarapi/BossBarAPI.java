package me.classy.bossbarapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BossBarAPI extends JavaPlugin {
	
	private static String prefix;
	
	@Override
	public void onEnable() {
		getLogger().info(Util.setColor("&e&m----------------------------------"));
		getLogger().info(getPrefix() + Util.setColor("&ePlugin has been enabled!"));
		getLogger().info(getPrefix() + Util.setColor("&eAuthor: &bClassyCoder"));
		getLogger().info(getPrefix() + Util.setColor("&eGitHub: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(getPrefix() + Util.setColor("&eSupport: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(Util.setColor("&e&m----------------------------------"));
	}

	@Override
	public void onDisable() {
		
	}
	
	public static String getPrefix() {
		prefix = Util.setColor("&b[POSSE-MODERATION] ");
		return prefix;
	}
}
