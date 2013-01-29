package com.caved_in.Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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
import org.bukkit.Material;
import org.bukkit.event.EventPriority;

import com.caved_in.Config.YMLIO;
import com.caved_in.Items.Blade;

import com.caved_in.TotalWar;
import me.ThaH3lper.com.Api.*;
import me.ThaH3lper.com.EpicBoss;
import me.cybermaxke.materialapi.inventory.CustomItemStack;

public class MobDeathListener implements Listener {
	private TotalWar Plugin;
	private Blade Blade;
	//private YMLIO Yml;
	EpicBoss EpicBoss;
	public MobDeathListener(TotalWar Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
		this.EpicBoss = (EpicBoss)Plugin.getServer().getPluginManager().getPlugin("EpicBossRecoded");
	}
	
	@EventHandler
	public void onBossDeath(BossDeathEvent Event)
	{
		Player BossKiller = Event.getPlayer();
		if (new Random().nextInt(100) >= 49)
		{
			int Random_Material = new Random().nextInt(2);
			Material Weapon_Make;
			switch(Random_Material)
			{
			case 0:
				Weapon_Make = Material.GOLD_SWORD;
				Blade = new Blade("blade",Weapon_Make);
				break;
			case 1:
				Weapon_Make = Material.IRON_SWORD;
				Blade = new Blade("blade",Weapon_Make);
				break;
			}
			CustomItemStack is = new CustomItemStack(Blade);
		    BossKiller.getInventory().addItem(is);
		    BossKiller.sendMessage("You've been given a rare item for killing a boss!");
		}
	}
}
