package me.classy.baapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import me.classy.baapi.utility.*;
import me.classy.baapi.holo.*;
import me.classy.baapi.gui.*;
import me.classy.baapi.commandsapi.*;

import java.util.List;

public class BAAPI extends JavaPlugin {
	
	private static BAAPI instance;
	private static String prefix;
	private HologramManager hologramManager;
	private CommandRegistery commandRegistery;
	private ICommandExecutor iCommandExecutor;
	
	@Override
	public void onEnable() {
		loadConfig();
		scoreBoard();
		registerCommands();
		
		getLogger().info(Util.setColor("&e&m----------------------------------"));
		getLogger().info(getPrefix() + Util.setColor("&ePlugin has been enabled!"));
		getLogger().info(getPrefix() + Util.setColor("&eAuthor: &bClassyCoder"));
		getLogger().info(getPrefix() + Util.setColor("&eGitHub: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(getPrefix() + Util.setColor("&eSupport: &b&nhttps://github.com/Posse-Plugins/Posse-Moderation"));
		getLogger().info(Util.setColor("&e&m----------------------------------"));
		
		hologramManager = new HologramManager(this);
		commandRegistery = new CommandRegistery();
		iCommandExecutor = new ICommandExecutor(commandRegistery);
		
	}

	@Override
	public void onDisable() {
		
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void registerCommands() {
		getCommand("ba-api").setExecutor(iCommandExecutor);
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
