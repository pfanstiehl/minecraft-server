// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Entity, EntityLiving, MathHelper, World, 
//            Vec3D, MovingObjectPosition, AxisAlignedBB, NBTTagCompound, 
//            EntityPlayer

public abstract class EntityThrowable extends Entity
{

    private int field_40047_d;
    private int field_40048_e;
    private int field_40045_f;
    private int field_40046_g;
    protected boolean field_40051_a;
    public int field_40049_b;
    protected EntityLiving field_40050_c;
    private int field_40052_h;
    private int field_40053_i;

    public EntityThrowable(World world)
    {
        super(world);
        field_40047_d = -1;
        field_40048_e = -1;
        field_40045_f = -1;
        field_40046_g = 0;
        field_40051_a = false;
        field_40049_b = 0;
        field_40053_i = 0;
        setSize(0.25F, 0.25F);
    }

    protected void entityInit()
    {
    }

    public EntityThrowable(World world, EntityLiving entityliving)
    {
        super(world);
        field_40047_d = -1;
        field_40048_e = -1;
        field_40045_f = -1;
        field_40046_g = 0;
        field_40051_a = false;
        field_40049_b = 0;
        field_40053_i = 0;
        field_40050_c = entityliving;
        setSize(0.25F, 0.25F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        float f = 0.4F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
        motionY = -MathHelper.sin(((rotationPitch + func_40040_d()) / 180F) * 3.141593F) * f;
        func_40043_a(motionX, motionY, motionZ, func_40044_c(), 1.0F);
    }

    public EntityThrowable(World world, double d, double d1, double d2)
    {
        super(world);
        field_40047_d = -1;
        field_40048_e = -1;
        field_40045_f = -1;
        field_40046_g = 0;
        field_40051_a = false;
        field_40049_b = 0;
        field_40053_i = 0;
        field_40052_h = 0;
        setSize(0.25F, 0.25F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    protected float func_40044_c()
    {
        return 1.5F;
    }

    protected float func_40040_d()
    {
        return 0.0F;
    }

    public void func_40043_a(double d, double d1, double d2, float f, 
            float f1)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        field_40052_h = 0;
    }

    public void onUpdate()
    {
        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();
        if(field_40049_b > 0)
        {
            field_40049_b--;
        }
        if(field_40051_a)
        {
            int i = worldObj.getBlockId(field_40047_d, field_40048_e, field_40045_f);
            if(i != field_40046_g)
            {
                field_40051_a = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                field_40052_h = 0;
                field_40053_i = 0;
            } else
            {
                field_40052_h++;
                if(field_40052_h == 1200)
                {
                    setEntityDead();
                }
                return;
            }
        } else
        {
            field_40053_i++;
        }
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        if(!worldObj.singleplayerWorld)
        {
            Entity entity = null;
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;
            for(int k = 0; k < list.size(); k++)
            {
                Entity entity1 = (Entity)list.get(k);
                if(!entity1.canBeCollidedWith() || entity1 == field_40050_c && field_40053_i < 5)
                {
                    continue;
                }
                float f4 = 0.3F;
                AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
                MovingObjectPosition movingobjectposition1 = axisalignedbb.func_706_a(vec3d, vec3d1);
                if(movingobjectposition1 == null)
                {
                    continue;
                }
                double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
                if(d1 < d || d == 0.0D)
                {
                    entity = entity1;
                    d = d1;
                }
            }

            if(entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }
        if(movingobjectposition != null)
        {
            func_40041_a(movingobjectposition);
        }
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
        float f1 = 0.99F;
        float f2 = func_40042_e();
        if(isInWater())
        {
            for(int j = 0; j < 4; j++)
            {
                float f3 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f3, posY - motionY * (double)f3, posZ - motionZ * (double)f3, motionX, motionY, motionZ);
            }

            f1 = 0.8F;
        }
        motionX *= f1;
        motionY *= f1;
        motionZ *= f1;
        motionY -= f2;
        setPosition(posX, posY, posZ);
    }

    protected float func_40042_e()
    {
        return 0.03F;
    }

    protected abstract void func_40041_a(MovingObjectPosition movingobjectposition);

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)field_40047_d);
        nbttagcompound.setShort("yTile", (short)field_40048_e);
        nbttagcompound.setShort("zTile", (short)field_40045_f);
        nbttagcompound.setByte("inTile", (byte)field_40046_g);
        nbttagcompound.setByte("shake", (byte)field_40049_b);
        nbttagcompound.setByte("inGround", (byte)(field_40051_a ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        field_40047_d = nbttagcompound.getShort("xTile");
        field_40048_e = nbttagcompound.getShort("yTile");
        field_40045_f = nbttagcompound.getShort("zTile");
        field_40046_g = nbttagcompound.getByte("inTile") & 0xff;
        field_40049_b = nbttagcompound.getByte("shake") & 0xff;
        field_40051_a = nbttagcompound.getByte("inGround") == 1;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
    }
}
