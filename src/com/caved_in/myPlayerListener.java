package com.caved_in;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventPriority;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;


import com.caved_in.PlayerStats.Stats;
import com.caved_in.PlayerStats.StatsCommandExecutor;
import com.caved_in.Target.Target;
import com.caved_in.Target.TargetCommandExecutor;
import com.caved_in.TotalWar;

public class myPlayerListener implements Listener {
	private TotalWar Plugin;
	public myPlayerListener(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent Event)
	{
		try
		{
			if (!TargetCommandExecutor.Targets.OfflineMessenger().GetMessage(Event.getPlayer().getName()).equals(null))
			{
				for(String Message: TargetCommandExecutor.Targets.OfflineMessenger().GetMessage(Event.getPlayer().getName()))
				{
					Event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
				}
				TargetCommandExecutor.Targets.OfflineMessenger().DeleteMessages(Event.getPlayer().getName());
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
		if(!TargetCommandExecutor.Targets.Contains(Event.getPlayer().getName() + ".Target"))
		{
			TargetCommandExecutor.Targets.ClearTarget(Event.getPlayer());
		}
	}
}
