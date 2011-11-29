// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityThrowable, MovingObjectPosition, DamageSource, Entity, 
//            World, EntityChicken, EntityLiving

public class EntityEgg extends EntityThrowable
{

    public EntityEgg(World world)
    {
        super(world);
    }

    public EntityEgg(World world, EntityLiving entityliving)
    {
        super(world, entityliving);
    }

    public EntityEgg(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
    }

    protected void func_40041_a(MovingObjectPosition movingobjectposition)
    {
        if(movingobjectposition.entityHit != null)
        {
            if(!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, field_40050_c), 0));
        }
        if(!worldObj.singleplayerWorld && rand.nextInt(8) == 0)
        {
            byte byte0 = 1;
            if(rand.nextInt(32) == 0)
            {
                byte0 = 4;
            }
            for(int j = 0; j < byte0; j++)
            {
                EntityChicken entitychicken = new EntityChicken(worldObj);
                entitychicken.func_40132_b_(-24000);
                entitychicken.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
                worldObj.entityJoinedWorld(entitychicken);
            }

        }
        for(int i = 0; i < 8; i++)
        {
            worldObj.spawnParticle("snowballpoof", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
        }

        if(!worldObj.singleplayerWorld)
        {
            setEntityDead();
        }
    }
}
