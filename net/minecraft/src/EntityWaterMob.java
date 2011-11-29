// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityCreature, IAnimals, World, NBTTagCompound, 
//            EntityPlayer

public abstract class EntityWaterMob extends EntityCreature
    implements IAnimals
{

    public EntityWaterMob(World world)
    {
        super(world);
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    public boolean getCanSpawnHere()
    {
        return worldObj.checkIfAABBIsClear(boundingBox);
    }

    public int getTalkInterval()
    {
        return 120;
    }

    protected boolean canDespawn()
    {
        return true;
    }

    protected int getExperiencePoints(EntityPlayer entityplayer)
    {
        return 1 + worldObj.rand.nextInt(3);
    }
}
