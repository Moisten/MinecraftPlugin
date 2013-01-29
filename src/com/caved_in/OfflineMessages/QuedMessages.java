package com.caved_in.OfflineMessages;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.InvalidConfigurationException;

import com.caved_in.Config.YMLIO;

public class QuedMessages {
	private YMLIO Yml;
	
	public QuedMessages(String StatLocation) {
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
	
	public String[] GetMessage(String Player_Name)
	{
		String Initial_Grab = "";
		Initial_Grab = Yml.get(Player_Name + ".Messages",Initial_Grab);
		String[] Messages = Initial_Grab.split("|");
		return Messages;
	}
	public void AddMessage(String Player_Name, String Message)
	{
		String Previous_Data = "";
		if (!Yml.get(Player_Name + ".Messages",Previous_Data).equals(null))
		{
			Previous_Data = Yml.get(Player_Name + ".Messages",Previous_Data);
		}
		Previous_Data = Previous_Data + "|" + Message;
		Yml.setForceSave(true);
		Yml.set(Player_Name + ".Messages",Previous_Data);
		Yml.setForceSave(false);
		Yml.save();
	}
	public void DeleteMessages(String Player_Name)
	{
		Yml.setForceSave(true);
		Yml.set(Player_Name + ".Messages", "");
		Yml.setForceSave(false);
		Yml.save();
	}

}
