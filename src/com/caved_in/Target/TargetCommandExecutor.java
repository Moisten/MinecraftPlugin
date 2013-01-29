package com.caved_in.Target;

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

public class TargetCommandExecutor implements CommandExecutor {
	public static Target Targets;
	private TotalWar Plugin; // pointer to your main class, unrequired if you don't need methods from the main class
 
	public TargetCommandExecutor(TotalWar plugin) {
		this.Plugin = plugin;
		Targets = new Target(this.Plugin.getDataFolder() + File.separator + "targets.yml",this.Plugin.getDataFolder() + File.separator + "quedmessages.yml");
	}
 
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args)
	{
		if (Args.length >= 1)
		{
			if (Args[0].equalsIgnoreCase("clear") && Sender instanceof Player)
			{
				this.Targets.ClearTarget((Player)Sender);
				ColorMessage((Player)Sender,"You have cleared your target.");
				return true;
			}
			/*
			else if (!Args[0].equalsIgnoreCase("clear") && !Args[0].equalsIgnoreCase("all") && Sender instanceof Player)
			{
				Player Targeted_Player = this.Plugin.getServer().getPlayer(Args[0]);
				if (Targeted_Player != null)
				{
					Targets.SetTarget((Player)Sender, Targeted_Player);
					System.out.println("User Targeted: " + ((Player)Sender).getName() + " -> " + Targeted_Player.getName());
					return true;
				}
				else
				{
					Sender.sendMessage("The player must be online before you can target them.");
					return true;
				}
			}
			else if (Args[0].equalsIgnoreCase("all") && (Sender instanceof ConsoleCommandSender || (Sender instanceof Player && ((Player)Sender).isOp())))
			{
				ArrayList<Player> Players_List = new ArrayList<Player>();
				for(Player P : Plugin.getServer().getOnlinePlayers())
				{
					Random Random = new Random();
					if (!Players_List.contains(P))
					{
						Targets.ClearTarget(P);
						Player Targeting = Plugin.getServer().getOnlinePlayers()[Random.nextInt(Plugin.getServer().getOnlinePlayers().length)];
						if (Targeting != P)
						{
							Targets.SetTarget(P, Targeting);
							Players_List.add(P);
						}
						else
						{
							while(true)
							{
								Player Targeting_ = Plugin.getServer().getOnlinePlayers()[Random.nextInt(Plugin.getServer().getOnlinePlayers().length)];
								if (Targeting_ != P)
								{
									Targets.SetTarget(P, Targeting_);
									Players_List.add(P);
									break;
								}
							}
						}
					}
				}
				this.Plugin.getServer().broadcastMessage("Eliminate your Target, or be eliminated.");
			}
			else
			{
				Sender.sendMessage("Please refer to the documentation for help.");
				return true;
			}
		}
		*/
		else if (Sender instanceof Player && Args.length == 0)
		{
			//this.Targets.GetTarget((Player)Sender);
			if (Targets.GetTarget((Player)Sender).equals(null) || Targets.GetTarget((Player)Sender).equals("") || Targets.GetTarget((Player)Sender).equals(""))
			{
				//Assign_Random_Target((Player)Sender);
				ColorMessage((Player)Sender,"&CYou don't have a target!");
			}
			else if (!Targets.GetTarget((Player)Sender).equals("") && !Targets.GetTarget((Player)Sender).equals(((Player)Sender).getName()))
			{
				ColorMessage((Player)Sender,"&CYour target is: " + "&5" + Targets.GetTarget((Player)Sender));
			}
		}
		}
		return false;
	}
	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}
	/*
	private void Assign_Random_Target(Player InNeed)
	{
		Targets.ClearTarget(InNeed);
		while(true)
		{
			Random Rand = new Random();
			Player Targeting_ = Plugin.getServer().getOnlinePlayers()[Rand.nextInt(Plugin.getServer().getOnlinePlayers().length)];
			if (Targeting_ != InNeed.getPlayer())
			{
				Targets.SetTarget(InNeed.getPlayer(), Targeting_);
				break;
			}
		}
	}
	*/
}