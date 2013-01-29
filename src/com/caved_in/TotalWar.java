package com.caved_in;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.*;

import com.caved_in.Events.SignListener;
import com.caved_in.Items.ItemCommandExecutor;
import com.caved_in.PlayerStats.StatsCommandExecutor;
import com.caved_in.Target.TargetCommandExecutor;
import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.exceptions.PluginNotEnabledException;

public class TotalWar extends JavaPlugin {
	public static Economy economy = null;
	public static Permission permission = null;
	public static final String Templar_Permission = "Totalwar.Templar";
	public static final String Pagan_Permission = "Totalwar.Pagan";
	public static EntityManager EntityManager;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.saveResource("stats.yml", false);
		this.saveResource("targets.yml", false);
		this.saveResource("quedmessages.yml", false);
		new myPlayerListener(this);
		new SignListener(this);
		setupEconomy();
		setupPermissions();
		try
		{
			EntityManager = RemoteEntities.createManager(this);
		}
		catch (PluginNotEnabledException e)
		{
			e.printStackTrace();
		}
		getCommand("stats").setExecutor(new StatsCommandExecutor(this));
		getCommand("target").setExecutor(new TargetCommandExecutor(this));
		getCommand("itemgen").setExecutor(new ItemCommandExecutor(this));
	}
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
    
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    
	public static String ColorMessage(String Message)
	{
		return ChatColor.translateAlternateColorCodes('&', Message);
	}
	
	public static boolean isTemplar(Player Player)
	{
		return Player.hasPermission(TotalWar.Templar_Permission);
	}
	
	public static boolean isPagan(Player Player)
	{
		return Player.hasPermission(TotalWar.Pagan_Permission);
	}
	
	public static boolean isSameFaction(Player A, Player B)
	{
		if ((isTemplar(A) && isTemplar(B)) || (isPagan(A) && isPagan(B)))
		{
			return true;
		}
		return false;
	}
}
