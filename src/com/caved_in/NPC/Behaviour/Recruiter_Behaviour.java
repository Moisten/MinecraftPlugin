package com.caved_in.NPC.Behaviour;

import org.bukkit.entity.Player;

import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.thinking.EnterSightBehavior;
import de.kumpelblase2.remoteentities.api.thinking.InteractBehavior;

import com.caved_in.TotalWar;
import com.caved_in.TotalWar.Faction;
import com.caved_in.Items.*;

public class Recruiter_Behaviour extends EnterSightBehavior {
	
	private Faction Faction;
	
	private String[] Templar_Messages = new String[] {"Hello Warrior, would you like to join the Templar Army!?","You're lucky there's other around, pagan, I'd kill you otherwise!",
			"Just use the billboard over there, and grab yourself a recruitment form!","Welcome Back, Brother!"};
	
	private String[] Pagan_Messages = new String[] {"Greetings Friend, would you like to join the Pagan Regime?","Be a shame if they find a dead Templar in the river...",
			"Just use the billboard over there, and you'll join the regime!","Nice to meet again, Friend!"};
	
	private String Templar_Welcome_Neutral = Templar_Messages[0];
	private String Templar_Recruit_Neutral = Templar_Messages[2];
	private String Templar_Greet_Templar = Templar_Messages[3];
	private String Templar_Threaten_Pagan = Templar_Messages[1];
	
	private String Pagan_Welcome_Neutral = Pagan_Messages[0];
	private String Pagan_Recruit_Neutral = Pagan_Messages[2];
	private String Pagan_Greet_Pagan = Pagan_Messages[3];
	private String Pagan_Threaten_Templar = Pagan_Messages[1];

	public Recruiter_Behaviour(RemoteEntity inEntity, Faction NPCFaction)
	{
		super(inEntity);
		this.Faction = NPCFaction;
	}

	@Override
	public void onEnterSight(Player Player)
	{
		//Different Events for each recruiter type, and player type
		if (this.Faction.equals(Faction.TEMPLAR))
		{
			if (TotalWar.isTemplar(Player))
			{
				Player.sendMessage(Templar_Greet_Templar);
			}
			else if (TotalWar.isPagan(Player))
			{
				Player.sendMessage(Templar_Threaten_Pagan);
			}
			else
			{
				Player.sendMessage(Templar_Welcome_Neutral);
				Player.sendMessage(Templar_Recruit_Neutral);
			}
		}
		else if (this.Faction.equals(Faction.PAGAN))
		{
			if (TotalWar.isPagan(Player))
			{
				Player.sendMessage(Pagan_Greet_Pagan);
			}
			else if (TotalWar.isTemplar(Player))
			{
				Player.sendMessage(Pagan_Threaten_Templar);
			}
			else
			{
				Player.sendMessage(Pagan_Welcome_Neutral);
				Player.sendMessage(Pagan_Recruit_Neutral);
			}
		}
	}
}
