package com.caved_in.Events;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.caved_in.TotalWar;
import com.caved_in.PlayerStats.StatsCommandExecutor;
import com.caved_in.Target.TargetCommandExecutor;

public class PlayerListener implements Listener {
	private TotalWar Plugin;
	
	public PlayerListener(TotalWar Plugin)
	{
		this.Plugin = Plugin;
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler
	public void OnPlayerDeath(PlayerDeathEvent Event)
	{
		Player Player = (Player)Event.getEntity();
		Player Killer = Player.getKiller();
		if (Killer != null && TargetCommandExecutor.Targets.GetTarget(Killer).equals(Player.getName()))
		{
			Player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cYour Target has eliminated you."));
			StatsCommandExecutor.Stats.TargetKill(Killer);
			EconomyResponse Response = TotalWar.economy.depositPlayer(Killer.getName(), TargetCommandExecutor.Targets.GetTargetWorth(Killer));
			if (Response.transactionSuccess())
			{
				Killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour reward for killing " + Player.getName() + " is $" + TargetCommandExecutor.Targets.GetTargetWorth(Killer)));
				TargetCommandExecutor.Targets.ClearTarget(Killer);
			}
			StatsCommandExecutor.Stats.AddDeath(Player);
		}
	}
	
	@EventHandler
	public void PlayerDamagePlayer(EntityDamageByEntityEvent Event)
	{
		Entity Entity_Attacker = Event.getDamager();
		Entity Entity_Defender = Event.getEntity();
		if (Entity_Attacker instanceof Player && Entity_Defender instanceof Player)
		{
			Player Attacker = (Player)Entity_Attacker;
			Player Defender = (Player)Entity_Defender;
			if (isSameFaction(Attacker,Defender))
			{
				Attacker.sendMessage(TotalWar.ColorMessage("&CYou can't attack a person of the same faction!"));
				Event.setCancelled(true);
			}
		}
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
	
	private boolean isTemplar(Player Player)
	{
		return TotalWar.permission.has(Player, TotalWar.Templar_Permission);
	}
	
	private boolean isPagan(Player Player)
	{
		return TotalWar.permission.has(Player, TotalWar.Pagan_Permission);
	}
	
	private boolean isSameFaction(Player A, Player B)
	{
		if ((isTemplar(A) && isTemplar(B)) || (isPagan(A) && isPagan(B)))
		{
			return true;
		}
		return false;
	}
}
