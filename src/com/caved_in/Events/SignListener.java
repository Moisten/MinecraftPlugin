package com.caved_in.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

import com.caved_in.TotalWar;
import com.caved_in.Target.TargetCommandExecutor;

public class SignListener implements Listener {
	private TotalWar Plugin;
	private String[] Sign_Data = new String[] {"Bounty"};
	
	public SignListener(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
		this.Plugin = Plugin;
	}
	@EventHandler
	public void onSignInteract(PlayerInteractEvent Event)
	{
		Action Event_Action = Event.getAction();
		Player Event_Player = Event.getPlayer();
		Block Event_Block = Event.getClickedBlock();
		if (Event_Action == Action.RIGHT_CLICK_BLOCK && Event_Block != null)
		{
			if (Event_Block.getType() == Material.WALL_SIGN || Event_Block.getType() == Material.SIGN || Event_Block.getType() == Material.SIGN_POST)
			{
				Sign Clicked_Sign = (Sign)Event_Block.getState();
				String[] Sign_Text = Clicked_Sign.getLines();
				if (Sign_Text[0].equalsIgnoreCase(Sign_Data[0]))
				{
					OfflinePlayer Target = this.Plugin.getServer().getOfflinePlayer(Sign_Text[1]);
					if (!TargetCommandExecutor.Targets.GetTarget(Event_Player).equals(""))
					{
						Event_Player.sendMessage(TargetCommandExecutor.Targets.GetTarget(Event_Player) + " is your current target, clear this or kill them before taking another");
					}
					else
					{
						if (Target.isOnline())
						{
							if (!TotalWar.isSameFaction(Event_Player,(Player)Target))
							{
								int Target_Worth = Integer.parseInt(Sign_Text[2]);
								TargetCommandExecutor.Targets.SetTarget(Event_Player,(Player)Target,Target_Worth);
								Event_Block.breakNaturally();
							}
							else
							{
								Event_Player.sendMessage(TotalWar.ColorMessage("&CYou can't hunt someone in the same faction as you!"));
							}
						}
						else
						{
							Event_Player.sendMessage(TotalWar.ColorMessage("&CPlease wait until " + Target.getPlayer().getName() + " is online to hunt them"));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void OnSignChange(SignChangeEvent Event)
	{
		String[] Sign_Text = Event.getLines();
		if (Sign_Text[0].equals(Sign_Data[0]))
		{
			OfflinePlayer Target = this.Plugin.getServer().getOfflinePlayer(Sign_Text[1]);
			if (Target.hasPlayedBefore() == true)
			{
				if (Target.isOnline())
				{
					Player Online_Target = (Player)Target.getPlayer();
					int Target_Worth = Integer.parseInt(Sign_Text[2]);
					if (getMoney(Event.getPlayer().getName()) >= Target_Worth)
					{
						giveMoney(Event.getPlayer().getName(), (Target_Worth * -1));
						Event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',"&CYour bounty has been placed, $" + Target_Worth + " has been removed from your balance"));
						Online_Target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&CA $" + Target_Worth + " bounty was just placed on you!"));
					}
					else
					{
						Event.getPlayer().sendMessage("You don't have enough money to post this bounty!");
						Event.setCancelled(true);
					}
				}
				else
				{
					int Target_Worth = Integer.parseInt(Sign_Text[2]);
					if (getMoney(Event.getPlayer().getName()) >= Target_Worth)
					{
						giveMoney(Event.getPlayer().getName(), (Target_Worth * -1));
						Event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',"&CYour bounty has been placed, $" + Target_Worth + " has been removed from your balance"));
						TargetCommandExecutor.Targets.OfflineMessenger().AddMessage(Target.getName(), "&CA $" + Target_Worth + " bounty has been placed on you!");
					}
					else
					{
						Event.getPlayer().sendMessage("You don't have enough money to post this bounty!");
						Event.setCancelled(true);
					}
				}
			}
			else
			{
				Event.getPlayer().sendMessage(TotalWar.ColorMessage("&CYou can't put a bounty on someone who's never played before"));
				Event.setCancelled(true);
			}
		}
	}
	
	public void giveMoney(String playerName, double amount)
	{
		if(TotalWar.economy != null)
		{
			if(amount > 0)
				TotalWar.economy.depositPlayer(playerName, amount);
			else
				TotalWar.economy.withdrawPlayer(playerName, Math.abs(amount));
		}
	}
	public double getMoney(String playerName)
	{
		if(TotalWar.economy != null)
			return TotalWar.economy.getBalance(playerName);
		return 0;
	}

}