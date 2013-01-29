package com.caved_in.Events;

import org.bukkit.Location;
import org.bukkit.World;

import com.caved_in.TotalWar;

public class WorldEffects {
	private TotalWar Plugin;
	private World World;
	public WorldEffects(TotalWar Plugin, World thisWorld)
	{
		this.Plugin = Plugin;
		this.World = World;
	}
	public void Create_Harmless_Explosion(Location Location)
	{
		World.createExplosion(Location, 0F);
	}
	public void Create_Harmless_Lightning(Location Location)
	{
		World.strikeLightningEffect(Location);
	}
}
