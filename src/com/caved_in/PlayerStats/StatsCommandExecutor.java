package com.caved_in.PlayerStats;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.caved_in.TotalWar;

public class StatsCommandExecutor implements CommandExecutor {
	public static Stats Stats;
	private TotalWar Plugin; // pointer to your main class, unrequired if you don't need methods from the main class
 
	public StatsCommandExecutor(TotalWar plugin) {
		this.Plugin = plugin;
		this.Stats = new Stats(this.Plugin.getDataFolder() + File.separator + "stats.yml");
	}
 
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if (Args.length >= 1)
		{
			if (Stats.HasStats(Args[0])) {
				Sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Args[0] + " has &c" + Stats.GetKills(Args[0]) + " &2kill-score&f, and &c" + Stats.GetDeaths(Args[0]) + " &4deaths!"));
			}
			else
			{
				Sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&C" + Args[0] + " has no available statistics!"));
			}
		}
		else if (Args.length == 0 && Sender instanceof Player)
		{
			Player Player_Local = (Player)Sender;
			ColorMessage(Player_Local,"You have &c" + Stats.GetKills(Player_Local.getName()) + " &2kill-score&f, and &c" + Stats.GetDeaths(Player_Local.getName()) + " &4deaths!");
			return true;
		}
		else{
			Sender.sendMessage("This command needs to include a playername, or be used by a player if no player name specified.");
		}
		return true;
	}
	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}
}