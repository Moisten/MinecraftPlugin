package com.caved_in.PlayerStats;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.caved_in.TotalWar;
import com.caved_in.Config.YMLIO;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class Stats {
	private YMLIO Yml;
	public Stats(String StatLocation) {
		try {
			Yml = new YMLIO(new File(StatLocation));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	//Valid Paths = "Kills" and "Deaths" and "Suicides"
	private int ReadYML(String Player_Name, String Path) {
		int Stat_Value = 0;
		Yml.setForceSave(false);
		Stat_Value = Yml.get(Player_Name + ".Stats." + Path,Stat_Value);
		return Stat_Value;
	}
	public void SetStat(String Player_Name, String Path, int Value) {
		int Loval_Value = Value;
		Yml.setForceSave(true);
		Loval_Value = Yml.get(Player_Name + ".Stats." + Path, Loval_Value);
		Yml.setForceSave(false);
		Yml.save();
	}
	public void AddKill(Player Player)
	{
		int Player_Kills = ReadYML(Player.getName(),"Kills");
		Player_Kills += 1;
		SetStat(Player.getName(),"Kills", Player_Kills);
		Player.sendMessage("Your Kill score is now: " + ReadYML(Player.getName(), "Kills"));
	}
	public void TargetKill(Player Player)
	{
		int Player_Kills = ReadYML(Player.getName(),"Kills");
		Player_Kills += 5;
		SetStat(Player.getName(),"Kills", Player_Kills);
		Player.sendMessage("Your Kill score is now: " + ReadYML(Player.getName(), "Kills"));
	}
	public void AddDeath(Player Player)
	{
		int Player_Deaths = ReadYML(Player.getName(),"Deaths");
		Player_Deaths += 1;
		SetStat(Player.getName(), "Deaths", Player_Deaths);
		Player.sendMessage("Your Death count is now: " + ReadYML(Player.getName(), "Deaths"));
	}
	public int getTargetKills(Player Player)
	{
		return this.ReadYML(Player.getName(), "Targetkills");
	}
	
	public int GetKills(String Player_Name)
	{
		return this.ReadYML(Player_Name, "Kills");
	}
	
	public int GetDeaths(String Player_Name)
	{
		return ReadYML(Player_Name, "Deaths");
	}
	
	public boolean HasStats(String Player_Name)
	{
		if (ReadYML(Player_Name, "Kills") >= 0 || ReadYML(Player_Name, "Deaths") >= 0)
		{
			return true;
		}
		return false;
	}
	
}
