package com.caved_in.NPC;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.api.DespawnReason;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.api.features.FeatureSet;
import de.kumpelblase2.remoteentities.api.thinking.Mind;

public class TemplarRecruiter {
	
	private final String NPC_Name = "Templar Recruiter";
	private final String[] Messages = new String[] {"This is the first example message", "This is the second example message!" };
	
	private RemoteEntity Entity_Type;
	private Location Spawn_Location;
	private boolean Stationary = false;
	
	public TemplarRecruiter(RemoteEntity Entity_Type, Location Spawn_Location)
	{
		this.Entity_Type = Entity_Type;
		this.Spawn_Location = Spawn_Location;
	}
	
	public void SetStationary(boolean Stationary)
	{
		this.Stationary = Stationary;
	}
	
	public boolean GetStationary()
	{
		return this.Stationary;
	}
	
	
	
}
