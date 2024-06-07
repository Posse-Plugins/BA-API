package me.classy.baapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.classy.baapi.utility.Util;
import me.classy.baapi.listener.PlayerJoin;

public class BAAPI extends JavaPlugin {
	
	private static BAAPI instance;
	private static String prefix;
	
	@Override
	public void onEnable() {
		loadConfig();
		registerListeners();
		
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
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(), this);
	}
	
	public static String getPrefix() {
		prefix = Util.setColor("&b[POSSE-MODERATION] ");
		return prefix;
	}
	
	public static BAAPI getInstance() {
		return instance;
	}
}
