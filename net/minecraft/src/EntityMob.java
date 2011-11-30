// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityCreature, IMob, World, DamageSource, 
//            Potion, PotionEffect, Entity, AxisAlignedBB, 
//            MathHelper, EnumSkyBlock, NBTTagCompound

public abstract class EntityMob extends EntityCreature
    implements IMob
{

    protected int attackStrength;

    public EntityMob(World world)
    {
        super(world);
        attackStrength = 2;
        experienceValue = 5;
    }

    public void onLivingUpdate()
    {
        float f = getEntityBrightness(1.0F);
        if(f > 0.5F)
        {
            entityAge += 2;
        }
        super.onLivingUpdate();
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(!worldObj.singleplayerWorld && worldObj.difficultySetting == 0)
        {
            setEntityDead();
        }
    }

    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.func_40211_b(this, 16D);
        if(entityplayer != null && canEntityBeSeen(entityplayer))
        {
            return entityplayer;
        } else
        {
            return null;
        }
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if(super.attackEntityFrom(damagesource, i))
        {
            Entity entity = damagesource.getEntity();
            if(riddenByEntity == entity || ridingEntity == entity)
            {
                return true;
            }
            if(entity != this)
            {
                entityToAttack = entity;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected boolean attackEntityAsMob(Entity entity)
    {
        int i = attackStrength;
        if(func_35184_a(Potion.damageBoost))
        {
            i += 3 << func_35187_b(Potion.damageBoost).getAmplifier();
        }
        if(func_35184_a(Potion.weaknessPotion))
        {
            i -= 2 << func_35187_b(Potion.weaknessPotion).getAmplifier();
        }
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    protected void attackEntity(Entity entity, float f)
    {
        if(attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            attackEntityAsMob(entity);
        }
    }

    protected float getBlockPathWeight(int x, int y, int z)
    {
        return 0.5F - worldObj.getLightBrightness(x, y, z);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    // Is current position darker than various random thresholds?
    protected boolean isDarkEnough()
    {
        int x = MathHelper.floor_double(posX);
        int y = MathHelper.floor_double(boundingBox.minY);
        int z = MathHelper.floor_double(posZ);
        
        if(worldObj.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) > rand.nextInt(32))
        {
            return false;
        }
                
        int lightValue = worldObj.getBlockLightValue(x, y, z);
        
        // When thundering, recalculate lightValue accordingly.
        if(worldObj.getIsThundering())
        {
            int skylightSubtractedBackup = worldObj.skylightSubtracted;
            worldObj.skylightSubtracted = 10;
            lightValue = worldObj.getBlockLightValue(x, y, z);
            worldObj.skylightSubtracted = skylightSubtractedBackup;
        }
        
        return lightValue <= rand.nextInt(8);
    }

    public boolean getCanSpawnHere()
    {
        return isDarkEnough() && super.getCanSpawnHere();
    }
}
