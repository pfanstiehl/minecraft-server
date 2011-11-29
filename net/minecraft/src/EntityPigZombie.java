// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityZombie, World, NBTTagCompound, DamageSource, 
//            EntityPlayer, AxisAlignedBB, Entity, Item, 
//            ItemStack

public class EntityPigZombie extends EntityZombie
{

    private int angerLevel;
    private int randomSoundDelay;
    private static final ItemStack defaultHeldItem;

    public EntityPigZombie(World world)
    {
        super(world);
        angerLevel = 0;
        randomSoundDelay = 0;
        texture = "/mob/pigzombie.png";
        moveSpeed = 0.5F;
        attackStrength = 5;
        isImmuneToFire = true;
    }

    public void onUpdate()
    {
        moveSpeed = entityToAttack == null ? 0.5F : 0.95F;
        if(randomSoundDelay > 0 && --randomSoundDelay == 0)
        {
            worldObj.playSoundAtEntity(this, "mob.zombiepig.zpigangry", getSoundVolume() * 2.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }
        super.onUpdate();
    }

    public boolean getCanSpawnHere()
    {
        return worldObj.difficultySetting > 0 && worldObj.checkIfAABBIsClear(boundingBox) && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.getIsAnyLiquid(boundingBox);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
    }

    protected Entity findPlayerToAttack()
    {
        if(angerLevel == 0)
        {
            return null;
        } else
        {
            return super.findPlayerToAttack();
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        if(entity instanceof EntityPlayer)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32D, 32D, 32D));
            for(int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);
                if(entity1 instanceof EntityPigZombie)
                {
                    EntityPigZombie entitypigzombie = (EntityPigZombie)entity1;
                    entitypigzombie.becomeAngryAt(entity);
                }
            }

            becomeAngryAt(entity);
        }
        return super.attackEntityFrom(damagesource, i);
    }

    private void becomeAngryAt(Entity entity)
    {
        entityToAttack = entity;
        angerLevel = 400 + rand.nextInt(400);
        randomSoundDelay = rand.nextInt(40);
    }

    protected String getLivingSound()
    {
        return "mob.zombiepig.zpig";
    }

    protected String getHurtSound()
    {
        return "mob.zombiepig.zpighurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombiepig.zpigdeath";
    }

    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(2 + i);
        for(int k = 0; k < j; k++)
        {
            dropItem(Item.rottenFlesh.shiftedIndex, 1);
        }

        j = rand.nextInt(2 + i);
        for(int l = 0; l < j; l++)
        {
            dropItem(Item.goldNugget.shiftedIndex, 1);
        }

    }

    protected int getDropItemId()
    {
        return Item.rottenFlesh.shiftedIndex;
    }

    static 
    {
        defaultHeldItem = new ItemStack(Item.swordGold, 1);
    }
}
