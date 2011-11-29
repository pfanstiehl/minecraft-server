// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityThrowable, MovingObjectPosition, EntityBlaze, DamageSource, 
//            Entity, World, EntityLiving

public class EntitySnowball extends EntityThrowable
{

    public EntitySnowball(World world)
    {
        super(world);
    }

    public EntitySnowball(World world, EntityLiving entityliving)
    {
        super(world, entityliving);
    }

    public EntitySnowball(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
    }

    protected void func_40041_a(MovingObjectPosition movingobjectposition)
    {
        if(movingobjectposition.entityHit != null)
        {
            byte byte0 = 0;
            if(movingobjectposition.entityHit instanceof EntityBlaze)
            {
                byte0 = 3;
            }
            if(!movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, field_40050_c), byte0));
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
