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
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import com.caved_in.TotalWar;

public class TemplarPeaganListener implements Listener {
	private TotalWar Plugin;
	private String[] Templar_Sign_Data = new String[] {"Join us in the war!","Fight the evil pagans!","Become a templar today!" };
	private String[] Pagan_Sign_Data = new String[] { "Join us in the fight!","Destroy the evil Templars!","Become a pagan today!" };
	public TemplarPeaganListener(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void NameTag(PlayerReceiveNameTagEvent Event)
	{
		if (TotalWar.permission.has(Event.getNamedPlayer(), TotalWar.Templar_Permission))
		{
			Event.setTag(ChatColor.YELLOW + Event.getNamedPlayer().getName());
		}
		else if (TotalWar.permission.has(Event.getNamedPlayer(), TotalWar.Pagan_Permission))
		{
			Event.setTag(ChatColor.GREEN + Event.getNamedPlayer().getName());
		}
		else
		{
			Event.setTag(ChatColor.ITALIC + Event.getNamedPlayer().getName());
		}
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
				if (isTemplarSign(Clicked_Sign))
				{
					if (TotalWar.isPagan(Event.getPlayer()))
					{
						MessageAllPagans("&C" + Event.getPlayer() + " has betrayed the Pagans, kill them.");
						MessageAllTemplars("&C" + Event.getPlayer() + " has joined the templars!");
						TotalWar.permission.playerRemove(Event.getPlayer(), TotalWar.Pagan_Permission);
						TotalWar.permission.playerAdd(Event.getPlayer(), TotalWar.Templar_Permission);
					}
					else
					{
						TotalWar.permission.playerAdd(Event.getPlayer(), TotalWar.Templar_Permission);
						MessageAllTemplars("&C" + Event.getPlayer() + " has joined the templars!");
					}
				}
				else if (isPaganSign(Clicked_Sign))
				{
					if (TotalWar.isTemplar(Event.getPlayer()))
					{
						MessageAllTemplars("&C" + Event.getPlayer() + " has betrayed the Templars, kill them.");
						MessageAllPagans("&C" + Event.getPlayer() + " has joined the Pagans!");
						TotalWar.permission.playerRemove(Event.getPlayer(), TotalWar.Templar_Permission);
						TotalWar.permission.playerAdd(Event.getPlayer(), TotalWar.Pagan_Permission);
					}
					else
					{
						TotalWar.permission.playerAdd(Event.getPlayer(), TotalWar.Pagan_Permission);
						MessageAllPagans("&C" + Event.getPlayer() + " has joined the Pagans!");
					}
				}
			}
		}
	}
	private boolean isTemplarSign(Sign Clicked_Sign)
	{
		if (Clicked_Sign.getLine(0).equals(Templar_Sign_Data[0]) && Clicked_Sign.getLine(1).equals(Templar_Sign_Data[1]) && Clicked_Sign.getLine(2).equals(Templar_Sign_Data[2]))
		{
			return true;
		}
		return false;
	}
	
	private boolean isPaganSign(Sign Clicked_Sign)
	{
		if (Clicked_Sign.getLine(0).equals(Pagan_Sign_Data[0]) && Clicked_Sign.getLine(1).equals(Pagan_Sign_Data[1]) && Clicked_Sign.getLine(2).equals(Pagan_Sign_Data[2]))
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
				P.sendMessage(Message);
			}
		}
	}
	private void MessageAllPagans(String Message)
	{
		for (Player P : this.Plugin.getServer().getOnlinePlayers())
		{
			if (TotalWar.permission.has(P, TotalWar.Pagan_Permission))
			{
				P.sendMessage(Message);
			}
		}
	}
}
