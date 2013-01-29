package com.caved_in.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.caved_in.Config.YMLIO;
import com.caved_in.OfflineMessages.QuedMessages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import com.caved_in.TotalWar;


public class Target {
	private YMLIO Yml;
	private static QuedMessages OfflineMessenger;
	public Target(String StatLocation,String MessageLocation) {
		try {
			Yml = new YMLIO(new File(StatLocation));
			OfflineMessenger = new QuedMessages(MessageLocation);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public String GetTarget(Player Player) {
		String Target = "";
		Target = Yml.get(Player.getName() + ".Target",Target);
		return Target;
	}
	public int GetTargetWorth(Player Player)
	{
		int TargetWorth = 0;
		TargetWorth = Yml.get(Player.getName() + ".Worth", TargetWorth);
		return TargetWorth;
	}
	public void SetTarget(Player Player, Player Target,int Worth) {
		if (GetTarget(Player).equals(""))
		{
			ColorMessage(Player,"&cYou are hunting " + Target.getName());
			ColorMessage(Target,"&c" + Player.getName() + " is hunting you.");
			Yml.setForceSave(true);
			Yml.set(Player.getName() + ".Target", Target.getName());
			Yml.set(Player.getName() + ".Worth", Worth);
			Yml.save();
			Yml.setForceSave(false);
		}
		else
		{
			ColorMessage(Player,"&cPlease eliminate, or clear your previous target before selecting another.");
		}
	}
	public void SetTarget(Player Player, String Offline_Target, int Worth)
	{
		if (GetTarget(Player).equals(""))
		{
			ColorMessage(Player,"&cYou are hunting " + Offline_Target);
			OfflineMessenger.AddMessage(Offline_Target, "&c" + Player.getName() + " is hunting you.");
			Yml.setForceSave(true);
			Yml.set(Player.getName() + ".Target", Offline_Target);
			Yml.set(Player.getName() + ".Worth", Worth);
			Yml.save();
			Yml.setForceSave(false);
		}
		else
		{
			ColorMessage(Player,"&cPlease eliminate, or clear your previous target before selecting another.");
		}
	}
	public void ClearTarget(Player Player)
	{
		Yml.setForceSave(true);
		Yml.set(Player.getName() + ".Target","");
		Yml.set(Player.getName() + ".Worth", "");
		Yml.save();
		Yml.setForceSave(false);
	}
	public boolean Contains(String Text)
	{
		return this.Yml.contains(Text);
	}
	private void ColorMessage(Player Player, String Message)
	{
		Player.sendMessage(ChatColor.translateAlternateColorCodes('&', Message));
	}
	public static QuedMessages OfflineMessenger()
	{
		return OfflineMessenger;
	}
}
