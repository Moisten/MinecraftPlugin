package com.caved_in.NPC;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.caved_in.TotalWar;
import com.caved_in.TotalWar.Faction;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.api.DespawnReason;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.api.features.FeatureSet;
import de.kumpelblase2.remoteentities.api.thinking.Mind;
import de.kumpelblase2.remoteentities.api.thinking.goals.DesireLookAtNearest;
import de.kumpelblase2.remoteentities.nms.*;

public class Recruiter {
	
	private String NPC_Name = "";
	
	private RemoteEntityType Entity_Type;
	private RemoteEntity Recruiter_Entity;
	private Location Spawn_Location;
	private Faction Recruiter_Faction;
	private boolean Stationary = false;
	
	public Recruiter(RemoteEntityType Entity_Type, Location Spawn_Location,String NPC_Name, Faction Faction)
	{
		this.Entity_Type = Entity_Type;
		this.Spawn_Location = Spawn_Location;
		this.NPC_Name = NPC_Name;
		this.Recruiter_Faction = Faction;
		//this.Recruiter_Entity = TotalWar.EntityManager.createEntity(Entity_Type, Spawn_Location);
	}
	
	public void SetStationary(boolean Stationary)
	{
		this.Stationary = Stationary;
	}
	
	public boolean GetStationary()
	{
		return this.Stationary;
	}
	
	private void SetupDesire()
	{
		//this.Recruiter_Entity.getMind().addActionDesire(new DesireLookAtNearest(Recruiter_Entity,Player.class,8f), 1);
	}
		
}
