package me.classy.baapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import me.classy.baapi.utility.Util;
import me.classy.baapi.listener.PlayerJoin;
import me.classy.baapi.commands.TitleCommand;
import me.classy.baapi.commands.SubtitleCommand;

import java.util.List;

public class BAAPI extends JavaPlugin {
	
	private static BAAPI instance;
	private static String prefix;
	
	@Override
	public void onEnable() {
		loadConfig();
		scoreBoard();
		registerListeners();
		registerCommands();
		
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
	
	private void registerCommands() {
		getCommand("sendtitle").setExecutor(new TitleCommand());
		getCommand("sendsubtitle").setExecutor(new SubtitleCommand());
	}
	
	public void scoreBoard() {
		String title = getConfig().getString(Util.setColor("scoreboard.title"), Util.setColor("&e&lBA-API"));
		List<String> scores = getConfig().getStringList(Util.setColor("scoreboard.scores"));
		
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(title);
		
		int scoreValue = scores.size();
		for (String line : scores) {
			Score score = objective.getScore(line);
			score.setScore(scoreValue);
			scoreValue--;
		}
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.setScoreboard(scoreboard);
		}
	}
	
	public static String getPrefix() {
		prefix = Util.setColor("&b[POSSE-MODERATION] ");
		return prefix;
	}
	
	public static BAAPI getInstance() {
		return instance;
	}
}
