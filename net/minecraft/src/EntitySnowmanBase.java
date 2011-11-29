// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityCreature, IAnimals, World, NBTTagCompound

public abstract class EntitySnowmanBase extends EntityCreature
    implements IAnimals
{

    public EntitySnowmanBase(World world)
    {
        super(world);
    }

    protected void fall(float f)
    {
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "none";
    }

    protected String getHurtSound()
    {
        return "none";
    }

    protected String getDeathSound()
    {
        return "none";
    }

    public int getTalkInterval()
    {
        return 120;
    }

    protected boolean canDespawn()
    {
        return false;
    }
}
