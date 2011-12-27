// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityFireball, World, MovingObjectPosition, Entity, 
//            DamageSource, Block, BlockFire, EntityLiving

public class EntitySmallFireball extends EntityFireball
{

    public EntitySmallFireball(World world)
    {
        super(world);
        setSize(0.3125F, 0.3125F);
    }

    public EntitySmallFireball(World world, EntityLiving entityliving, double d, double d1, double d2)
    {
        super(world, entityliving, d, d1, d2);
        setSize(0.3125F, 0.3125F);
    }

    protected void func_40063_a(MovingObjectPosition movingobjectposition)
    {
        if(!worldObj.singleplayerWorld)
        {
            if(movingobjectposition.entityHit != null)
            {
                if(!movingobjectposition.entityHit.isImmuneToFire() && movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 5))
                {
                    movingobjectposition.entityHit.setFire(5);
                }
            } else
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;
                switch(movingobjectposition.sideHit)
                {
                case 1: // '\001'
                    j++;
                    break;

                case 0: // '\0'
                    j--;
                    break;

                case 2: // '\002'
                    k--;
                    break;

                case 3: // '\003'
                    k++;
                    break;

                case 5: // '\005'
                    i++;
                    break;

                case 4: // '\004'
                    i--;
                    break;
                }
                if(worldObj.isAirBlock(i, j, k))
                {
                    worldObj.setBlockWithNotify(i, j, k, Block.fire.blockID);
                }
            }
            setEntityDead();
        }
    }

    public boolean canBeCollidedWith()
    {
        return false;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        return false;
    }
}
