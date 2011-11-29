// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Entity, EntityPlayer, EntityLiving, MathHelper, 
//            World, Block, Vec3D, AxisAlignedBB, 
//            MovingObjectPosition, DamageSource, NBTTagCompound, ItemStack, 
//            Item, InventoryPlayer

public class EntityArrow extends Entity
{

    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private int inData;
    private boolean inGround;
    public boolean doesArrowBelongToPlayer;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    public boolean isAirborne;

    public EntityArrow(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        isAirborne = false;
        setSize(0.5F, 0.5F);
    }

    public EntityArrow(World world, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        isAirborne = false;
        setSize(0.5F, 0.5F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    public EntityArrow(World world, EntityLiving entityliving, float f)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        isAirborne = false;
        shootingEntity = entityliving;
        doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        setArrowHeading(motionX, motionY, motionZ, f * 1.5F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public void setArrowHeading(double d, double d1, double d2, float f, 
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
        ticksInGround = 0;
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
        }
        int i = worldObj.getBlockId(xTile, yTile, zTile);
        if(i > 0)
        {
            Block.blocksList[i].setBlockBoundsBasedOnState(worldObj, xTile, yTile, zTile);
            AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);
            if(axisalignedbb != null && axisalignedbb.isVecInside(Vec3D.createVector(posX, posY, posZ)))
            {
                inGround = true;
            }
        }
        if(arrowShake > 0)
        {
            arrowShake--;
        }
        if(inGround)
        {
            int j = worldObj.getBlockId(xTile, yTile, zTile);
            int k = worldObj.getBlockMetadata(xTile, yTile, zTile);
            if(j != inTile || k != inData)
            {
                inGround = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                ticksInGround = 0;
                ticksInAir = 0;
                return;
            }
            ticksInGround++;
            if(ticksInGround == 1200)
            {
                setEntityDead();
            }
            return;
        }
        ticksInAir++;
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        if(movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
        Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;
        for(int l = 0; l < list.size(); l++)
        {
            Entity entity1 = (Entity)list.get(l);
            if(!entity1.canBeCollidedWith() || entity1 == shootingEntity && ticksInAir < 5)
            {
                continue;
            }
            float f5 = 0.3F;
            AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f5, f5, f5);
            MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_706_a(vec3d, vec3d1);
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
        if(movingobjectposition != null)
        {
            if(movingobjectposition.entityHit != null)
            {
                float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                int j1 = (int)Math.ceil((double)f1 * 2D);
                if(isAirborne)
                {
                    j1 += rand.nextInt(j1 / 2 + 2);
                }
                DamageSource damagesource = null;
                if(shootingEntity == null)
                {
                    damagesource = DamageSource.causeArrowDamage(this, this);
                } else
                {
                    damagesource = DamageSource.causeArrowDamage(this, shootingEntity);
                }
                if(movingobjectposition.entityHit.attackEntityFrom(damagesource, j1))
                {
                    if(movingobjectposition.entityHit instanceof EntityLiving)
                    {
                        ((EntityLiving)movingobjectposition.entityHit).field_35189_aD++;
                    }
                    worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                    setEntityDead();
                } else
                {
                    motionX *= -0.10000000149011612D;
                    motionY *= -0.10000000149011612D;
                    motionZ *= -0.10000000149011612D;
                    rotationYaw += 180F;
                    prevRotationYaw += 180F;
                    ticksInAir = 0;
                }
            } else
            {
                xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlockId(xTile, yTile, zTile);
                inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
                float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                posX -= (motionX / (double)f2) * 0.05000000074505806D;
                posY -= (motionY / (double)f2) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f2) * 0.05000000074505806D;
                worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                inGround = true;
                arrowShake = 7;
                isAirborne = false;
            }
        }
        if(isAirborne)
        {
            for(int i1 = 0; i1 < 4; i1++)
            {
                worldObj.spawnParticle("crit", posX + (motionX * (double)i1) / 4D, posY + (motionY * (double)i1) / 4D, posZ + (motionZ * (double)i1) / 4D, -motionX, -motionY + 0.20000000000000001D, -motionZ);
            }

        }
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        for(rotationPitch = (float)((Math.atan2(motionY, f3) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f4 = 0.99F;
        float f6 = 0.05F;
        if(isInWater())
        {
            for(int k1 = 0; k1 < 4; k1++)
            {
                float f7 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f7, posY - motionY * (double)f7, posZ - motionZ * (double)f7, motionX, motionY, motionZ);
            }

            f4 = 0.8F;
        }
        motionX *= f4;
        motionY *= f4;
        motionZ *= f4;
        motionY -= f6;
        setPosition(posX, posY, posZ);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inData", (byte)inData);
        nbttagcompound.setByte("shake", (byte)arrowShake);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
        nbttagcompound.setBoolean("player", doesArrowBelongToPlayer);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inData = nbttagcompound.getByte("inData") & 0xff;
        arrowShake = nbttagcompound.getByte("shake") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
        doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.singleplayerWorld)
        {
            return;
        }
        if(inGround && doesArrowBelongToPlayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.arrow, 1)))
        {
            worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.onItemPickup(this, 1);
            setEntityDead();
        }
    }
}
