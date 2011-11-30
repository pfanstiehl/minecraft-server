// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            TileEntity, World, EntityList, EntityLiving, 
//            AxisAlignedBB, NBTTagCompound

public class TileEntityMobSpawner extends TileEntity
{

	public int delay;
	private String mobID;
	public double yaw;
	public double yaw2;

	public TileEntityMobSpawner()
	{
		delay = -1;
		yaw2 = 0.0D;
		mobID = "Pig";
		delay = 20;
	}

	public void setMobID(String s)
	{
		mobID = s;
	}

	// Is any player within 16m from the centre of this mob spawner?
	public boolean anyPlayerInRange()
	{
		return worldObj.getClosestPlayer((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, 16D) != null;
	}

	// A mob spawner will periodically (but with intervals of random length) enter a state where it will
	// attempt to spawn mobs upon every update. The mob spawner will leave this state if there are too many
	// mobs of its type in a certain area or if it successfully spawns at least one mob.
	public void updateEntity()
	{
		yaw2 = yaw;

		// Do nothing if no player is in range of this mob spawner.
		if(!anyPlayerInRange())
		{
			return;
		}
		
		// Show some smoke and flames around this mob spawner and spin the mob inside.
		double effectsX = (float)xCoord + worldObj.rand.nextFloat();
		double effectsY = (float)yCoord + worldObj.rand.nextFloat();
		double effectsZ = (float)zCoord + worldObj.rand.nextFloat();
		worldObj.spawnParticle("smoke", effectsX, effectsY, effectsZ, 0.0D, 0.0D, 0.0D);
		worldObj.spawnParticle("flame", effectsX, effectsY, effectsZ, 0.0D, 0.0D, 0.0D);
		for(yaw += 1000F / ((float)delay + 200F); yaw > 360D;)
		{
			yaw -= 360D;
			yaw2 -= 360D;
		}

		if(!worldObj.singleplayerWorld)
		{
			
			// Only try spawning mobs if delay is 0. Decrement delay if above 0, reset if below.
			if(delay == -1)
			{
				updateDelay();
			}
			if(delay > 0)
			{
				delay--;
				return;
			}
			
			// Attempt to spawn a mob 4 times.
			byte attempts = 4;
			for(int i = 0; i < attempts; i++)
			{
				EntityLiving mob = (EntityLiving)EntityList.createEntityInWorld(mobID, worldObj);
				
				if(mob == null)
				{
					return;
				}
				
				// Abort spawning and reset delay if there are more than 6 mobs of this mob spawner's mob type
				// in an axis aligned box with dimensions 17x9x17 centred around this mob spawner.
				int mobTypeCount = worldObj.getEntitiesWithinAABB(mob.getClass(), AxisAlignedBB.getBoundingBoxFromPool(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(8D, 4D, 8D)).size();
				if(mobTypeCount >= 6)
				{
					updateDelay();
					return;
				}
				
				if(mob == null)
				{
					continue;
				}
				
				// Pick a spot within an axis aligned box of dimensions 8x3x8 centred horizontally at the more
				// negative x and z corner of this mob spawner and centred vertically at this mob spawner block.
				// Note that the probability distribution of horizontal position does not approximate a uniform
				// distribution. Both the x and z positions approximate a continuous triagular distribution
				// (a distribution of the absolute difference of two standard uniform variables). Intuitively,
				// Uber5001 pointed out that the same effect happens when you take the distribution of the
				// difference of two dice rolls. From a simplistic perspective, the mob's position is more likely
				// to be close horizontally to the spawner.
				double mobX = (double)xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 4D;
				double mobY = (yCoord + worldObj.rand.nextInt(3)) - 1;
				double mobZ = (double)zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 4D;
				mob.setLocationAndAngles(mobX, mobY, mobZ, worldObj.rand.nextFloat() * 360F, 0.0F);
				
				// If mob can spawn, spawn it, play sound, and display explosion.
				if(mob.getCanSpawnHere())
				{
					worldObj.entityJoinedWorld(mob);
					worldObj.playAuxSFX(2004, xCoord, yCoord, zCoord, 0);
					mob.spawnExplosionParticle();
					updateDelay();
				}
			}

		}
		super.updateEntity();
	}

	// Reset the delay to a random value uniformly distributed between 200 and 800.
	private void updateDelay()
	{
		delay = 200 + worldObj.rand.nextInt(600);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		mobID = nbttagcompound.getString("EntityId");
		delay = nbttagcompound.getShort("Delay");
	}

	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setString("EntityId", mobID);
		nbttagcompound.setShort("Delay", (short)delay);
	}
}
