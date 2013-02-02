package com.caved_in.Events;

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
import com.caved_in.OfflineMessages.QuedMessages;

public class TemplarPaganCommandExecutor implements CommandExecutor {
	private TotalWar Plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	private String[] Faction_Names = new String[] {"Templar","Templars","Pagan","Pagans" };
 
	public TemplarPaganCommandExecutor(TotalWar plugin) {
		this.Plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Sender instanceof Player)
		{
			Player Player = (Player)Sender;
			if (Args.length > 0)
			{
				String Faction_Name = Args[0];
				if (Faction_Name.equalsIgnoreCase(Faction_Names[0]) || Faction_Name.equalsIgnoreCase(Faction_Names[1]))
				{
					if (TotalWar.isPagan(Player))
					{
						MakeTemplar(Player);
						MessageAllPagans("&C" + Player.getName() + " has betrayed the Pagans, kill them.");
						MessageAllTemplars("&C" + Player.getName() + " has joined the templars!");
						return true;
					}
					else
					{
						MakeTemplar(Player);
						MessageAllTemplars("&C" + Player.getName() + " has joined the templars!");
						return true;
					}
				}
				else if (Faction_Name.equalsIgnoreCase(Faction_Names[2]) || Faction_Name.equalsIgnoreCase(Faction_Names[3]))
				{
					if (TotalWar.isTemplar(Player))
					{
						MakePagan(Player);
						MessageAllTemplars("&C" + Player.getName() + " has joined the Pagans, kill them!");
						MessageAllPagans("&C" + Player.getName() + " has joined the Pagans!");
						return true;
					}
					else
					{
						MakePagan(Player);
						MessageAllPagans("&C" + Player.getName() + " has joined the Pagans!");
						return true;
					}
				}
				return false;
			}
			return false;
		}
		return false;
	}
	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}
	private void MessageAllTemplars(String Message)
	{
		for(Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			if (TotalWar.permission.has(P, TotalWar.Templar_Permission))
			{
				P.sendMessage(ChatColor.translateAlternateColorCodes('&',Message));
			}
		}
	}
	private void MessageAllPagans(String Message)
	{
		for (Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			if (TotalWar.permission.has(P, TotalWar.Pagan_Permission))
			{
				P.sendMessage(ChatColor.translateAlternateColorCodes('&',Message));
			}
		}
	}
	private void MakePagan(Player Player)
	{
		TotalWar.permission.playerRemove(Player, TotalWar.Neutral_Name_Color);
		TotalWar.permission.playerRemove(Player, TotalWar.Templar_Name_Color);
		TotalWar.permission.playerRemove(Player, TotalWar.Templar_Permission);
		TotalWar.permission.playerAdd(Player,TotalWar.Pagan_Name_Color);
		TotalWar.permission.playerAdd(Player, TotalWar.Pagan_Permission);
	}
	private void MakeTemplar(Player Player)
	{
		TotalWar.permission.playerRemove(Player,TotalWar.Pagan_Name_Color);
		TotalWar.permission.playerRemove(Player, TotalWar.Pagan_Permission);
		TotalWar.permission.playerRemove(Player, TotalWar.Neutral_Name_Color);
		TotalWar.permission.playerAdd(Player, TotalWar.Templar_Name_Color);
		TotalWar.permission.playerAdd(Player, TotalWar.Templar_Permission);
	}
	private void MakeNeutral(Player Player)
	{
		TotalWar.permission.playerRemove(Player, TotalWar.Pagan_Permission);
		TotalWar.permission.playerRemove(Player, TotalWar.Templar_Permission);
		TotalWar.permission.playerRemove(Player,TotalWar.Pagan_Name_Color);
		TotalWar.permission.playerRemove(Player, TotalWar.Templar_Name_Color);
		TotalWar.permission.playerAdd(Player, TotalWar.Neutral_Name_Color);
	}
}