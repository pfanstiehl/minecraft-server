// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Entity, MathHelper, World, Material, 
//            AxisAlignedBB, EntityPlayer, Block, DamageSource, 
//            NBTTagCompound

public class EntityXPOrb extends Entity
{

    public int xpColor;
    public int xpOrbAge;
    public int unusedPickupCooldown;
    private int xpOrbHealth;
    private int xpValue;

    public EntityXPOrb(World world, double d, double d1, double d2, 
            int i)
    {
        super(world);
        xpOrbAge = 0;
        xpOrbHealth = 5;
        setSize(0.5F, 0.5F);
        yOffset = height / 2.0F;
        setPosition(d, d1, d2);
        rotationYaw = (float)(Math.random() * 360D);
        motionX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F;
        motionY = (float)(Math.random() * 0.20000000000000001D) * 2.0F;
        motionZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F;
        xpValue = i;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public EntityXPOrb(World world)
    {
        super(world);
        xpOrbAge = 0;
        xpOrbHealth = 5;
        setSize(0.25F, 0.25F);
        yOffset = height / 2.0F;
    }

    protected void entityInit()
    {
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(unusedPickupCooldown > 0)
        {
            unusedPickupCooldown--;
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= 0.029999999329447746D;
        if(worldObj.getBlockMaterial(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) == Material.lava)
        {
            motionY = 0.20000000298023224D;
            motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
            motionZ = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
            worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 2.0F + rand.nextFloat() * 0.4F);
        }
        pushOutOfBlocks(posX, (boundingBox.minY + boundingBox.maxY) / 2D, posZ);
        double d = 8D;
        EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, d);
        if(entityplayer != null)
        {
            double d1 = (entityplayer.posX - posX) / d;
            double d2 = ((entityplayer.posY + (double)entityplayer.getEyeHeight()) - posY) / d;
            double d3 = (entityplayer.posZ - posZ) / d;
            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            double d5 = 1.0D - d4;
            if(d5 > 0.0D)
            {
                d5 *= d5;
                motionX += (d1 / d4) * d5 * 0.10000000000000001D;
                motionY += (d2 / d4) * d5 * 0.10000000000000001D;
                motionZ += (d3 / d4) * d5 * 0.10000000000000001D;
            }
        }
        moveEntity(motionX, motionY, motionZ);
        float f = 0.98F;
        if(onGround)
        {
            f = 0.5880001F;
            int i = worldObj.getBlockId(MathHelper.floor_double(posX), MathHelper.floor_double(boundingBox.minY) - 1, MathHelper.floor_double(posZ));
            if(i > 0)
            {
                f = Block.blocksList[i].slipperiness * 0.98F;
            }
        }
        motionX *= f;
        motionY *= 0.98000001907348633D;
        motionZ *= f;
        if(onGround)
        {
            motionY *= -0.89999997615814209D;
        }
        xpColor++;
        xpOrbAge++;
        if(xpOrbAge >= 6000)
        {
            setEntityDead();
        }
    }

    public boolean handleWaterMovement()
    {
        return worldObj.handleMaterialAcceleration(boundingBox, Material.water, this);
    }

    protected void dealFireDamage(int i)
    {
        attackEntityFrom(DamageSource.inFire, i);
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        setBeenAttacked();
        xpOrbHealth -= i;
        if(xpOrbHealth <= 0)
        {
            setEntityDead();
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("Health", (byte)xpOrbHealth);
        nbttagcompound.setShort("Age", (short)xpOrbAge);
        nbttagcompound.setShort("Value", (short)xpValue);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xpOrbHealth = nbttagcompound.getShort("Health") & 0xff;
        xpOrbAge = nbttagcompound.getShort("Age");
        xpValue = nbttagcompound.getShort("Value");
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.singleplayerWorld)
        {
            return;
        }
        if(unusedPickupCooldown == 0 && entityplayer.field_35218_w == 0)
        {
            entityplayer.field_35218_w = 2;
            worldObj.playSoundAtEntity(this, "random.orb", 0.1F, 0.5F * ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.8F));
            entityplayer.onItemPickup(this, 1);
            entityplayer.addExperience(xpValue);
            setEntityDead();
        }
    }

    public int getXpValue()
    {
        return xpValue;
    }

    public static int getMore(int i)
    {
        if(i >= 2477)
        {
            return 2477;
        }
        if(i >= 1237)
        {
            return 1237;
        }
        if(i >= 617)
        {
            return 617;
        }
        if(i >= 307)
        {
            return 307;
        }
        if(i >= 149)
        {
            return 149;
        }
        if(i >= 73)
        {
            return 73;
        }
        if(i >= 37)
        {
            return 37;
        }
        if(i >= 17)
        {
            return 17;
        }
        if(i >= 7)
        {
            return 7;
        }
        return i < 3 ? 1 : 3;
    }
}
