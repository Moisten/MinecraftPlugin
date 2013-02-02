package com.caved_in.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.caved_in.TotalWar;

public class TemplarPeaganListener implements Listener {
	private TotalWar Plugin;
	private String[] Templar_Sign_Data = new String[] {"Join","The","Templars"};
	private String[] Pagan_Sign_Data = new String[] { "Join","The","Pagans"};
	public TemplarPeaganListener(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	/*@EventHandler
	public void PromoteSignInteract(PlayerInteractEvent Event)
	{
		Action Event_Action = Event.getAction();
		Player Event_Player = Event.getPlayer();
		Block Event_Block = Event.getClickedBlock();
		if (Event_Action == Action.RIGHT_CLICK_BLOCK && Event_Block != null)
		{
			if (Event_Block.getType() == Material.WALL_SIGN || Event_Block.getType() == Material.SIGN || Event_Block.getType() == Material.SIGN_POST)
			{
				Sign Clicked_Sign = (Sign)Event_Block.getState();
				if (isTemplarSign(Clicked_Sign))
				{
					if (TotalWar.isPagan(Event.getPlayer()))
					{
						MakeTemplar(Event.getPlayer());
						MessageAllPagans("&C" + Event.getPlayer().getName() + " has betrayed the Pagans, kill them.");
						MessageAllTemplars("&C" + Event.getPlayer().getName() + " has joined the templars!");
					}
					else
					{
						MakeTemplar(Event.getPlayer());
						MessageAllTemplars("&C" + Event.getPlayer().getName() + " has joined the templars!");
					}
				}
				else if (isPaganSign(Clicked_Sign))
				{
					if (TotalWar.isTemplar(Event.getPlayer()))
					{
						MakePagan(Event.getPlayer());
						MessageAllTemplars("&C" + Event.getPlayer().getName() + " has betrayed the Templars, kill them.");
						MessageAllPagans("&C" + Event.getPlayer().getName() + " has joined the Pagans!");
					}
					else
					{
						MakePagan(Event.getPlayer());
						MessageAllPagans("&C" + Event.getPlayer().getName() + " has joined the Pagans!");
					}
				}
			}
		}
	}
	*/
	private boolean isTemplarSign(Sign Clicked_Sign)
	{
		if (Clicked_Sign.getLine(0).equalsIgnoreCase(Templar_Sign_Data[0]) && Clicked_Sign.getLine(1).equalsIgnoreCase(Templar_Sign_Data[1]) && Clicked_Sign.getLine(2).equalsIgnoreCase(Templar_Sign_Data[2]))
		{
			return true;
		}
		return false;
	}
	
	private boolean isPaganSign(Sign Clicked_Sign)
	{
		if (Clicked_Sign.getLine(0).equalsIgnoreCase(Pagan_Sign_Data[0]) && Clicked_Sign.getLine(1).equalsIgnoreCase(Pagan_Sign_Data[1]) && Clicked_Sign.getLine(2).equalsIgnoreCase(Pagan_Sign_Data[2]))
		{
			return true;
		}
		return false;
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
