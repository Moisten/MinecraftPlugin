package com.caved_in.Items;

import me.cybermaxke.materialapi.inventory.CustomItemStack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.caved_in.TotalWar;
import com.caved_in.Items.*;

public class ItemCommandExecutor implements CommandExecutor {
	private TotalWar Plugin;
	private Blade Blade;
	public ItemCommandExecutor(TotalWar plugin) {
		this.Plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		if (Sender instanceof Player && Sender.isOp())
		{
			Player Player_Local = (Player)Sender;
			int Random_Material = new Random().nextInt(3);
			Material Weapon_Make;
			switch(Random_Material)
			{
			case 0:
				Weapon_Make = Material.WOOD_SWORD;
				Blade = new Blade("blade",Weapon_Make);
				break;
			case 1:
				Weapon_Make = Material.IRON_SWORD;
				Blade = new Blade("blade",Weapon_Make);
				break;
			case 2:
				Weapon_Make = Material.STONE_SWORD;
				Blade = new Blade("blade",Weapon_Make);
				break;	
			}
			CustomItemStack is = new CustomItemStack(Blade);
		    Player_Local.getInventory().addItem(is);
			return true;
		}
		else{
			Sender.sendMessage("You're not able to use this command");
		}
		return true;
	}
	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}
}