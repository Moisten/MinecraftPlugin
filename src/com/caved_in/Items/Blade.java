package com.caved_in.Items;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import me.cybermaxke.materialapi.material.CustomMaterial;

public class Blade extends CustomMaterial {
	private String[] Prefixes = new String[] {"Brandons","Jodies","Moistening","JodahKiins","Dans","Sponge-Like",
			"Spongy","Unmoist","Death Bringing","Hobknocking","Charlie Sheens","Kitts","Epic","Delighful","Unique","Not-So-Unique"
			, "Constipated", "James'", "Classy","Shit-Stained","Disco","Violating","Mediocre","Refined"};
	private String[] Weapon_Type = new String[] {"Stick",/*"Neatly Trimmed Pile",*/ "Buster Blade","Greatsword","Katana","Noodle","Paddle",
			"Baton","Straw"};
	private String[] Suffixes = new String[] {"Hobknocking","Ass-Kicking","Doom","Death","Defile","Tickling","Nose Picking","Bacon"
			,"Stabbing","Hax","Hacks","Kindness","Smokes","poop","gas","Virtue","Killing","Butter","Cheese","Cuteness","Cutting","Super Evil","Heroism","Kings","Heros","Evil"
			};
    public Blade(String id, Material material) {
        super(id, material);
        Random_Name();
        Random_Damage();
        if (new Random().nextInt(101) >= 90)
        {
        	Random_Enchantments();
        }
    }
    
    public void Random_Name()
    {
    	Random Random = new Random();
    	String Name_Prefix = Prefixes[Random.nextInt(Prefixes.length)];
    	String Name_Middle = Weapon_Type[Random.nextInt(Weapon_Type.length)];
    	String Name_Suffix = Suffixes[Random.nextInt(Suffixes.length)];
    	this.setName(Name_Prefix + " " + Name_Middle + " of " + Name_Suffix);
    }
    public void Random_Damage()
    {
    	int Damage = (new Random().nextInt(6)) + (new Random().nextInt(6)) + 1;
    	this.setDamage(Damage);
    	this.setLore("Dishes out " + Damage + " damage!");
    }
    
    public void Random_Enchantments()
    {
    	int Tries = 0;
    	while (3 > Tries)
    	{
	    	int Enchantments = new Random().nextInt(7);
	    	switch(Enchantments)
	    	{
		    	case 0:
		    		this.addEnchantment(Enchantment.DAMAGE_ALL, new Random().nextInt(6));
		    		break;
		    	case 1:
		    		this.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, new Random().nextInt(6));
		    		break;
		    	case 2:
		    		this.addEnchantment(Enchantment.DURABILITY, new Random().nextInt(6));
		    		break;
		    	case 3:
		    		this.addEnchantment(Enchantment.DAMAGE_UNDEAD, new Random().nextInt(6));
		    		break;
		    	case 4:
		    		this.addEnchantment(Enchantment.FIRE_ASPECT, new Random().nextInt(6));
		    		break;
		    	case 5:
		    		this.addEnchantment(Enchantment.KNOCKBACK, new Random().nextInt(6));
		    		break;
		    	case 6:
		    		this.addEnchantment(Enchantment.LOOT_BONUS_MOBS, new Random().nextInt(6));
		    		break;
	    	}
	    	if (new Random().nextInt(100) < 93)
	    	{
	    		break;
	    	}
	    	else
	    	{
	    		Tries += 1;
	    	}
    	}
    }
    //Called when damaging a entity.
    @Override
    public void onHit(Player player, LivingEntity entity) {
    	
    }
 
    //Called when you interact right click, left click, ect.
    @Override
    public void onInteract(Player player, Action action, Block block, BlockFace face) {
 
    }
 
    //Called when right clicking a entity.
    @Override
    public void onInteractEntity(Player player, LivingEntity entity) {
 
    }
 
    //Called when placing the block.
    @Override
    public void onBlockPlaced(Player player, Block block) {
 
    }
 
    //Called when breaking the block.
    @Override
    public void onBlockBreak(Player player, Block block) {
 
    }
 
    //Called when damaging the block.
    @Override
    public void onBlockDamage(Player player, Block block) {
    	
    }
 
    //Called when right clicking on the block.
    @Override
    public void onBlockInteract(Player player, Block block) {
 
    }
}
