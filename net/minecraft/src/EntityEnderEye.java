// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Entity, MathHelper, World, EntityItem, 
//            ItemStack, Item, NBTTagCompound, EntityPlayer

public class EntityEnderEye extends Entity
{

    public int field_40062_a;
    private double field_40060_b;
    private double field_40061_c;
    private double field_40058_d;
    private int despawnTimer;
    private boolean shatterOrDrop;

    public EntityEnderEye(World world)
    {
        super(world);
        field_40062_a = 0;
        setSize(0.25F, 0.25F);
    }

    protected void entityInit()
    {
    }

    public EntityEnderEye(World world, double d, double d1, double d2)
    {
        super(world);
        field_40062_a = 0;
        despawnTimer = 0;
        setSize(0.25F, 0.25F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    public void func_40056_a(double d, int i, double d1)
    {
        double d2 = d - posX;
        double d3 = d1 - posZ;
        float f = MathHelper.sqrt_double(d2 * d2 + d3 * d3);
        if(f > 12F)
        {
            field_40060_b = posX + (d2 / (double)f) * 12D;
            field_40058_d = posZ + (d3 / (double)f) * 12D;
            field_40061_c = posY + 8D;
        } else
        {
            field_40060_b = d;
            field_40061_c = i;
            field_40058_d = d1;
        }
        despawnTimer = 0;
        shatterOrDrop = rand.nextInt(5) > 0;
    }

    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        if(!worldObj.singleplayerWorld)
        {
            double d = field_40060_b - posX;
            double d1 = field_40058_d - posZ;
            float f2 = (float)Math.sqrt(d * d + d1 * d1);
            float f3 = (float)Math.atan2(d1, d);
            double d2 = (double)f + (double)(f2 - f) * 0.0025000000000000001D;
            if(f2 < 1.0F)
            {
                d2 *= 0.80000000000000004D;
                motionY *= 0.80000000000000004D;
            }
            motionX = Math.cos(f3) * d2;
            motionZ = Math.sin(f3) * d2;
            if(posY < field_40061_c)
            {
                motionY = motionY + (1.0D - motionY) * 0.014999999664723873D;
            } else
            {
                motionY = motionY + (-1D - motionY) * 0.014999999664723873D;
            }
        }
        float f1 = 0.25F;
        if(isInWater())
        {
            for(int i = 0; i < 4; i++)
            {
                worldObj.spawnParticle("bubble", posX - motionX * (double)f1, posY - motionY * (double)f1, posZ - motionZ * (double)f1, motionX, motionY, motionZ);
            }

        } else
        {
            worldObj.spawnParticle("portal", ((posX - motionX * (double)f1) + rand.nextDouble() * 0.59999999999999998D) - 0.29999999999999999D, posY - motionY * (double)f1 - 0.5D, ((posZ - motionZ * (double)f1) + rand.nextDouble() * 0.59999999999999998D) - 0.29999999999999999D, motionX, motionY, motionZ);
        }
        if(!worldObj.singleplayerWorld)
        {
            setPosition(posX, posY, posZ);
            despawnTimer++;
            if(despawnTimer > 80 && !worldObj.singleplayerWorld)
            {
                setEntityDead();
                if(shatterOrDrop)
                {
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(Item.eyeOfEnder)));
                } else
                {
                    worldObj.playAuxSFX(2003, (int)Math.round(posX), (int)Math.round(posY), (int)Math.round(posZ), 0);
                }
            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
    }

    public float getEntityBrightness(float f)
    {
        return 1.0F;
    }
}
