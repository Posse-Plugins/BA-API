package me.classy.baapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.classy.baapi.utility.*;
import me.classy.baapi.holo.*;
import me.classy.baapi.gui.*;

import java.util.List;

public class BAAPI extends JavaPlugin {
	
	private static BAAPI instance;
	private static String prefix;
	private HologramManager hologramManager;
	
	@Override
	public void onEnable() {
		loadConfig();
		
		getLogger().info(Util.setColor("&e&m----------------------------------"));
		getLogger().info(getPrefix() + Util.setColor("&ePlugin has been enabled!"));
		getLogger().info(getPrefix() + Util.setColor("&eAuthor: &bClassyCoder"));
		getLogger().info(getPrefix() + Util.setColor("&eGitHub: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(getPrefix() + Util.setColor("&eSupport: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(Util.setColor("&e&m----------------------------------"));
		
		hologramManager = new HologramManager(this);
		
	}

	@Override
	public void onDisable() {
		
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public static String getPrefix() {
		prefix = Util.setColor("&b[BA-API] ");
		return prefix;
	}
	
	public static BAAPI getInstance() {
		return instance;
	}
}
