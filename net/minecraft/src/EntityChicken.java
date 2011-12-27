// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, World, Item, NBTTagCompound

public class EntityChicken extends EntityAnimal
{

    public boolean field_392_a;
    public float field_391_b;
    public float destPos;
    public float field_394_ae;
    public float field_393_af;
    public float field_390_ai;
    public int timeUntilNextEgg;

    public EntityChicken(World world)
    {
        super(world);
        field_392_a = false;
        field_391_b = 0.0F;
        destPos = 0.0F;
        field_390_ai = 1.0F;
        texture = "/mob/chicken.png";
        setSize(0.3F, 0.7F);
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
    }

    public int getMaxHealth()
    {
        return 4;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        field_393_af = field_391_b;
        field_394_ae = destPos;
        destPos += (double)(onGround ? -1 : 4) * 0.29999999999999999D;
        if(destPos < 0.0F)
        {
            destPos = 0.0F;
        }
        if(destPos > 1.0F)
        {
            destPos = 1.0F;
        }
        if(!onGround && field_390_ai < 1.0F)
        {
            field_390_ai = 1.0F;
        }
        field_390_ai *= 0.90000000000000002D;
        if(!onGround && motionY < 0.0D)
        {
            motionY *= 0.59999999999999998D;
        }
        field_391_b += field_390_ai * 2.0F;
        if(!func_40104_l() && !worldObj.singleplayerWorld && --timeUntilNextEgg <= 0)
        {
            worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            dropItem(Item.egg.shiftedIndex, 1);
            timeUntilNextEgg = rand.nextInt(6000) + 6000;
        }
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
        return "mob.chicken";
    }

    protected String getHurtSound()
    {
        return "mob.chickenhurt";
    }

    protected String getDeathSound()
    {
        return "mob.chickenhurt";
    }

    protected int getDropItemId()
    {
        return Item.feather.shiftedIndex;
    }

    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3) + rand.nextInt(1 + i);
        for(int k = 0; k < j; k++)
        {
            dropItem(Item.feather.shiftedIndex, 1);
        }

        if(isBurning())
        {
            dropItem(Item.chickenCooked.shiftedIndex, 1);
        } else
        {
            dropItem(Item.chickenRaw.shiftedIndex, 1);
        }
    }

    protected EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal)
    {
        return new EntityChicken(worldObj);
    }
}
