// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityCreature, INpc, NBTTagCompound, World

public class EntityVillager extends EntityCreature
    implements INpc
{

    private int profession;

    public EntityVillager(World world)
    {
        this(world, 0);
    }

    public EntityVillager(World world, int i)
    {
        super(world);
        profession = i;
        setTextureByProfession();
        moveSpeed = 0.5F;
    }

    public int getMaxHealth()
    {
        return 20;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Profession", profession);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        profession = nbttagcompound.getInteger("Profession");
        setTextureByProfession();
    }

    private void setTextureByProfession()
    {
        texture = "/mob/villager/villager.png";
        if(profession == 0)
        {
            texture = "/mob/villager/farmer.png";
        }
        if(profession == 1)
        {
            texture = "/mob/villager/librarian.png";
        }
        if(profession == 2)
        {
            texture = "/mob/villager/priest.png";
        }
        if(profession == 3)
        {
            texture = "/mob/villager/smith.png";
        }
        if(profession == 4)
        {
            texture = "/mob/villager/butcher.png";
        }
    }

    protected boolean canDespawn()
    {
        return false;
    }

    protected String getLivingSound()
    {
        return "mob.villager.default";
    }

    protected String getHurtSound()
    {
        return "mob.villager.defaulthurt";
    }

    protected String getDeathSound()
    {
        return "mob.villager.defaultdeath";
    }
}
