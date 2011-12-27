// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            EntityDragonBase, DragonPart, DataWatcher, World, 
//            MathHelper, Entity, AxisAlignedBB, Vec3D, 
//            EntityEnderCrystal, DamageSource, EntityLiving, Block, 
//            EntityPlayer, EntityXPOrb, BlockEndPortal

public class EntityDragon extends EntityDragonBase
{

    public double field_40148_a;
    public double field_40146_b;
    public double field_40147_c;
    public double field_40144_d[][];
    public int field_40145_e;
    public DragonPart dragonPartArray[];
    public DragonPart field_40143_g;
    public DragonPart field_40155_h;
    public DragonPart field_40156_i;
    public DragonPart field_40153_j;
    public DragonPart field_40154_k;
    public DragonPart field_40151_l;
    public DragonPart field_40152_m;
    public float field_40149_n;
    public float field_40150_o;
    public boolean field_40160_p;
    public boolean field_40159_q;
    private Entity field_40157_t;
    public int field_40158_r;
    public EntityEnderCrystal field_41039_t;

    public EntityDragon(World world)
    {
        super(world);
        field_40144_d = new double[64][3];
        field_40145_e = -1;
        field_40149_n = 0.0F;
        field_40150_o = 0.0F;
        field_40160_p = false;
        field_40159_q = false;
        field_40158_r = 0;
        field_41039_t = null;
        dragonPartArray = (new DragonPart[] {
            field_40143_g = new DragonPart(this, "head", 6F, 6F), field_40155_h = new DragonPart(this, "body", 8F, 8F), field_40156_i = new DragonPart(this, "tail", 4F, 4F), field_40153_j = new DragonPart(this, "tail", 4F, 4F), field_40154_k = new DragonPart(this, "tail", 4F, 4F), field_40151_l = new DragonPart(this, "wing", 4F, 4F), field_40152_m = new DragonPart(this, "wing", 4F, 4F)
        });
        maxHealth = 200;
        setEntityHealth(maxHealth);
        texture = "/mob/enderdragon/ender.png";
        setSize(16F, 8F);
        noClip = true;
        isImmuneToFire = true;
        field_40146_b = 100D;
        ignoreFrustrumCheck = true;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Integer(maxHealth));
    }

    public double[] func_40139_a(int i, float f)
    {
        if(health <= 0)
        {
            f = 0.0F;
        }
        f = 1.0F - f;
        int j = field_40145_e - i * 1 & 0x3f;
        int k = field_40145_e - i * 1 - 1 & 0x3f;
        double ad[] = new double[3];
        double d = field_40144_d[j][0];
        double d1;
        for(d1 = field_40144_d[k][0] - d; d1 < -180D; d1 += 360D) { }
        for(; d1 >= 180D; d1 -= 360D) { }
        ad[0] = d + d1 * (double)f;
        d = field_40144_d[j][1];
        d1 = field_40144_d[k][1] - d;
        ad[1] = d + d1 * (double)f;
        ad[2] = field_40144_d[j][2] + (field_40144_d[k][2] - field_40144_d[j][2]) * (double)f;
        return ad;
    }

    public void onLivingUpdate()
    {
        field_40149_n = field_40150_o;
        if(!worldObj.singleplayerWorld)
        {
            dataWatcher.updateObject(16, Integer.valueOf(health));
        }
        if(health <= 0)
        {
            float f = (rand.nextFloat() - 0.5F) * 8F;
            float f2 = (rand.nextFloat() - 0.5F) * 4F;
            float f4 = (rand.nextFloat() - 0.5F) * 8F;
            worldObj.spawnParticle("largeexplode", posX + (double)f, posY + 2D + (double)f2, posZ + (double)f4, 0.0D, 0.0D, 0.0D);
            return;
        }
        func_41036_u();
        float f1 = 0.2F / (MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ) * 10F + 1.0F);
        f1 *= (float)Math.pow(2D, motionY);
        if(field_40159_q)
        {
            field_40150_o += f1 * 0.5F;
        } else
        {
            field_40150_o += f1;
        }
        for(; rotationYaw >= 180F; rotationYaw -= 360F) { }
        for(; rotationYaw < -180F; rotationYaw += 360F) { }
        if(field_40145_e < 0)
        {
            for(int i = 0; i < field_40144_d.length; i++)
            {
                field_40144_d[i][0] = rotationYaw;
                field_40144_d[i][1] = posY;
            }

        }
        if(++field_40145_e == field_40144_d.length)
        {
            field_40145_e = 0;
        }
        field_40144_d[field_40145_e][0] = rotationYaw;
        field_40144_d[field_40145_e][1] = posY;
        if(worldObj.singleplayerWorld)
        {
            if(newPosRotationIncrements > 0)
            {
                double d = posX + (newPosX - posX) / (double)newPosRotationIncrements;
                double d2 = posY + (newPosY - posY) / (double)newPosRotationIncrements;
                double d4 = posZ + (newPosZ - posZ) / (double)newPosRotationIncrements;
                double d6;
                for(d6 = newRotationYaw - (double)rotationYaw; d6 < -180D; d6 += 360D) { }
                for(; d6 >= 180D; d6 -= 360D) { }
                rotationYaw += d6 / (double)newPosRotationIncrements;
                rotationPitch += (newRotationPitch - (double)rotationPitch) / (double)newPosRotationIncrements;
                newPosRotationIncrements--;
                setPosition(d, d2, d4);
                setRotation(rotationYaw, rotationPitch);
            }
        } else
        {
            double d1 = field_40148_a - posX;
            double d3 = field_40146_b - posY;
            double d5 = field_40147_c - posZ;
            double d7 = d1 * d1 + d3 * d3 + d5 * d5;
            if(field_40157_t != null)
            {
                field_40148_a = field_40157_t.posX;
                field_40147_c = field_40157_t.posZ;
                double d8 = field_40148_a - posX;
                double d10 = field_40147_c - posZ;
                double d12 = Math.sqrt(d8 * d8 + d10 * d10);
                double d13 = (0.40000000596046448D + d12 / 80D) - 1.0D;
                if(d13 > 10D)
                {
                    d13 = 10D;
                }
                field_40146_b = field_40157_t.boundingBox.minY + d13;
            } else
            {
                field_40148_a += rand.nextGaussian() * 2D;
                field_40147_c += rand.nextGaussian() * 2D;
            }
            if(field_40160_p || d7 < 100D || d7 > 22500D || isCollidedHorizontally || isCollidedVertically)
            {
                func_41037_w();
            }
            d3 /= MathHelper.sqrt_double(d1 * d1 + d5 * d5);
            float f10 = 0.6F;
            if(d3 < (double)(-f10))
            {
                d3 = -f10;
            }
            if(d3 > (double)f10)
            {
                d3 = f10;
            }
            motionY += d3 * 0.10000000149011612D;
            for(; rotationYaw < -180F; rotationYaw += 360F) { }
            for(; rotationYaw >= 180F; rotationYaw -= 360F) { }
            double d9 = 180D - (Math.atan2(d1, d5) * 180D) / 3.1415927410125732D;
            double d11;
            for(d11 = d9 - (double)rotationYaw; d11 < -180D; d11 += 360D) { }
            for(; d11 >= 180D; d11 -= 360D) { }
            if(d11 > 50D)
            {
                d11 = 50D;
            }
            if(d11 < -50D)
            {
                d11 = -50D;
            }
            Vec3D vec3d = Vec3D.createVector(field_40148_a - posX, field_40146_b - posY, field_40147_c - posZ).normalize();
            Vec3D vec3d1 = Vec3D.createVector(MathHelper.sin((rotationYaw * 3.141593F) / 180F), motionY, -MathHelper.cos((rotationYaw * 3.141593F) / 180F)).normalize();
            float f18 = (float)(vec3d1.dotProduct(vec3d) + 0.5D) / 1.5F;
            if(f18 < 0.0F)
            {
                f18 = 0.0F;
            }
            randomYawVelocity *= 0.8F;
            float f19 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ) * 1.0F + 1.0F;
            double d14 = Math.sqrt(motionX * motionX + motionZ * motionZ) * 1.0D + 1.0D;
            if(d14 > 40D)
            {
                d14 = 40D;
            }
            randomYawVelocity += d11 * (0.69999998807907104D / d14 / (double)f19);
            rotationYaw += randomYawVelocity * 0.1F;
            float f20 = (float)(2D / (d14 + 1.0D));
            float f21 = 0.06F;
            moveFlying(0.0F, -1F, f21 * (f18 * f20 + (1.0F - f20)));
            if(field_40159_q)
            {
                moveEntity(motionX * 0.80000001192092896D, motionY * 0.80000001192092896D, motionZ * 0.80000001192092896D);
            } else
            {
                moveEntity(motionX, motionY, motionZ);
            }
            Vec3D vec3d2 = Vec3D.createVector(motionX, motionY, motionZ).normalize();
            float f22 = (float)(vec3d2.dotProduct(vec3d1) + 1.0D) / 2.0F;
            f22 = 0.8F + 0.15F * f22;
            motionX *= f22;
            motionZ *= f22;
            motionY *= 0.9100000262260437D;
        }
        renderYawOffset = rotationYaw;
        field_40143_g.width = field_40143_g.height = 3F;
        field_40156_i.width = field_40156_i.height = 2.0F;
        field_40153_j.width = field_40153_j.height = 2.0F;
        field_40154_k.width = field_40154_k.height = 2.0F;
        field_40155_h.height = 3F;
        field_40155_h.width = 5F;
        field_40151_l.height = 2.0F;
        field_40151_l.width = 4F;
        field_40152_m.height = 3F;
        field_40152_m.width = 4F;
        float f3 = (((float)(func_40139_a(5, 1.0F)[1] - func_40139_a(10, 1.0F)[1]) * 10F) / 180F) * 3.141593F;
        float f5 = MathHelper.cos(f3);
        float f6 = -MathHelper.sin(f3);
        float f7 = (rotationYaw * 3.141593F) / 180F;
        float f8 = MathHelper.sin(f7);
        float f9 = MathHelper.cos(f7);
        field_40155_h.onUpdate();
        field_40155_h.setLocationAndAngles(posX + (double)(f8 * 0.5F), posY, posZ - (double)(f9 * 0.5F), 0.0F, 0.0F);
        field_40151_l.onUpdate();
        field_40151_l.setLocationAndAngles(posX + (double)(f9 * 4.5F), posY + 2D, posZ + (double)(f8 * 4.5F), 0.0F, 0.0F);
        field_40152_m.onUpdate();
        field_40152_m.setLocationAndAngles(posX - (double)(f9 * 4.5F), posY + 2D, posZ - (double)(f8 * 4.5F), 0.0F, 0.0F);
        if(!worldObj.singleplayerWorld)
        {
            func_41033_v();
        }
        if(!worldObj.singleplayerWorld && maxHurtTime == 0)
        {
            func_41034_a(worldObj.getEntitiesWithinAABBExcludingEntity(this, field_40151_l.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D, 0.0D)));
            func_41034_a(worldObj.getEntitiesWithinAABBExcludingEntity(this, field_40152_m.boundingBox.expand(4D, 2D, 4D).offset(0.0D, -2D, 0.0D)));
            func_41035_b(worldObj.getEntitiesWithinAABBExcludingEntity(this, field_40143_g.boundingBox.expand(1.0D, 1.0D, 1.0D)));
        }
        double ad[] = func_40139_a(5, 1.0F);
        double ad1[] = func_40139_a(0, 1.0F);
        float f11 = MathHelper.sin((rotationYaw * 3.141593F) / 180F - randomYawVelocity * 0.01F);
        float f12 = MathHelper.cos((rotationYaw * 3.141593F) / 180F - randomYawVelocity * 0.01F);
        field_40143_g.onUpdate();
        field_40143_g.setLocationAndAngles(posX + (double)(f11 * 5.5F * f5), posY + (ad1[1] - ad[1]) * 1.0D + (double)(f6 * 5.5F), posZ - (double)(f12 * 5.5F * f5), 0.0F, 0.0F);
        for(int j = 0; j < 3; j++)
        {
            DragonPart dragonpart = null;
            if(j == 0)
            {
                dragonpart = field_40156_i;
            }
            if(j == 1)
            {
                dragonpart = field_40153_j;
            }
            if(j == 2)
            {
                dragonpart = field_40154_k;
            }
            double ad2[] = func_40139_a(12 + j * 2, 1.0F);
            float f13 = (rotationYaw * 3.141593F) / 180F + ((func_40141_a(ad2[0] - ad[0]) * 3.141593F) / 180F) * 1.0F;
            float f14 = MathHelper.sin(f13);
            float f15 = MathHelper.cos(f13);
            float f16 = 1.5F;
            float f17 = (float)(j + 1) * 2.0F;
            dragonpart.onUpdate();
            dragonpart.setLocationAndAngles(posX - (double)((f8 * f16 + f14 * f17) * f5), ((posY + (ad2[1] - ad[1]) * 1.0D) - (double)((f17 + f16) * f6)) + 1.5D, posZ + (double)((f9 * f16 + f15 * f17) * f5), 0.0F, 0.0F);
        }

        if(!worldObj.singleplayerWorld)
        {
            field_40159_q = func_40140_a(field_40143_g.boundingBox) | func_40140_a(field_40155_h.boundingBox);
        }
    }

    private void func_41036_u()
    {
        if(field_41039_t != null)
        {
            if(field_41039_t.isDead)
            {
                if(!worldObj.singleplayerWorld)
                {
                    attackEntityFromPart(field_40143_g, DamageSource.explosion, 10);
                }
                field_41039_t = null;
            } else
            if(ticksExisted % 10 == 0 && health < maxHealth)
            {
                health++;
            }
        }
        if(rand.nextInt(10) == 0)
        {
            float f = 32F;
            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityEnderCrystal.class, boundingBox.expand(f, f, f));
            EntityEnderCrystal entityendercrystal = null;
            double d = 1.7976931348623157E+308D;
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                {
                    break;
                }
                Entity entity = (Entity)iterator.next();
                double d1 = entity.getDistanceSqToEntity(this);
                if(d1 < d)
                {
                    d = d1;
                    entityendercrystal = (EntityEnderCrystal)entity;
                }
            } while(true);
            field_41039_t = entityendercrystal;
        }
    }

    private void func_41033_v()
    {
        if(ticksExisted % 20 == 0)
        {
            Vec3D vec3d = getLook(1.0F);
            double d = 0.0D;
            double d1 = -1D;
            double d2 = 0.0D;
        }
    }

    private void func_41034_a(List list)
    {
        double d = (field_40155_h.boundingBox.minX + field_40155_h.boundingBox.maxX) / 2D;
        double d1 = (field_40155_h.boundingBox.minZ + field_40155_h.boundingBox.maxZ) / 2D;
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            Entity entity = (Entity)iterator.next();
            if(entity instanceof EntityLiving)
            {
                double d2 = entity.posX - d;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.addVelocity((d2 / d4) * 4D, 0.20000000298023224D, (d3 / d4) * 4D);
            }
        } while(true);
    }

    private void func_41035_b(List list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);
            if(entity instanceof EntityLiving)
            {
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
            }
        }

    }

    private void func_41037_w()
    {
        field_40160_p = false;
        if(rand.nextInt(2) == 0 && worldObj.playerEntities.size() > 0)
        {
            field_40157_t = (Entity)worldObj.playerEntities.get(rand.nextInt(worldObj.playerEntities.size()));
        } else
        {
            boolean flag = false;
            do
            {
                field_40148_a = 0.0D;
                field_40146_b = 70F + rand.nextFloat() * 50F;
                field_40147_c = 0.0D;
                field_40148_a += rand.nextFloat() * 120F - 60F;
                field_40147_c += rand.nextFloat() * 120F - 60F;
                double d = posX - field_40148_a;
                double d1 = posY - field_40146_b;
                double d2 = posZ - field_40147_c;
                flag = d * d + d1 * d1 + d2 * d2 > 100D;
            } while(!flag);
            field_40157_t = null;
        }
    }

    private float func_40141_a(double d)
    {
        for(; d >= 180D; d -= 360D) { }
        for(; d < -180D; d += 360D) { }
        return (float)d;
    }

    private boolean func_40140_a(AxisAlignedBB axisalignedbb)
    {
        int i = MathHelper.floor_double(axisalignedbb.minX);
        int j = MathHelper.floor_double(axisalignedbb.minY);
        int k = MathHelper.floor_double(axisalignedbb.minZ);
        int l = MathHelper.floor_double(axisalignedbb.maxX);
        int i1 = MathHelper.floor_double(axisalignedbb.maxY);
        int j1 = MathHelper.floor_double(axisalignedbb.maxZ);
        boolean flag = false;
        boolean flag1 = false;
        for(int k1 = i; k1 <= l; k1++)
        {
            for(int l1 = j; l1 <= i1; l1++)
            {
                for(int i2 = k; i2 <= j1; i2++)
                {
                    int j2 = worldObj.getBlockId(k1, l1, i2);
                    if(j2 == 0)
                    {
                        continue;
                    }
                    if(j2 == Block.obsidian.blockID || j2 == Block.whiteStone.blockID || j2 == Block.bedrock.blockID)
                    {
                        flag = true;
                    } else
                    {
                        flag1 = true;
                        worldObj.setBlockWithNotify(k1, l1, i2, 0);
                    }
                }

            }

        }

        if(flag1)
        {
            double d = axisalignedbb.minX + (axisalignedbb.maxX - axisalignedbb.minX) * (double)rand.nextFloat();
            double d1 = axisalignedbb.minY + (axisalignedbb.maxY - axisalignedbb.minY) * (double)rand.nextFloat();
            double d2 = axisalignedbb.minZ + (axisalignedbb.maxZ - axisalignedbb.minZ) * (double)rand.nextFloat();
            worldObj.spawnParticle("largeexplode", d, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        return flag;
    }

    public boolean attackEntityFromPart(DragonPart dragonpart, DamageSource damagesource, int i)
    {
        if(dragonpart != field_40143_g)
        {
            i = i / 4 + 1;
        }
        float f = (rotationYaw * 3.141593F) / 180F;
        float f1 = MathHelper.sin(f);
        float f2 = MathHelper.cos(f);
        field_40148_a = posX + (double)(f1 * 5F) + (double)((rand.nextFloat() - 0.5F) * 2.0F);
        field_40146_b = posY + (double)(rand.nextFloat() * 3F) + 1.0D;
        field_40147_c = (posZ - (double)(f2 * 5F)) + (double)((rand.nextFloat() - 0.5F) * 2.0F);
        field_40157_t = null;
        if((damagesource.getSourceOfDamage() instanceof EntityPlayer) || damagesource == DamageSource.explosion)
        {
            func_40137_e(damagesource, i);
        }
        return true;
    }

    protected void func_40102_ag()
    {
        field_40158_r++;
        if(field_40158_r >= 180 && field_40158_r <= 200)
        {
            float f = (rand.nextFloat() - 0.5F) * 8F;
            float f1 = (rand.nextFloat() - 0.5F) * 4F;
            float f2 = (rand.nextFloat() - 0.5F) * 8F;
            worldObj.spawnParticle("hugeexplosion", posX + (double)f, posY + 2D + (double)f1, posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }
        if(!worldObj.singleplayerWorld && field_40158_r > 150 && field_40158_r % 5 == 0)
        {
            for(int i = 1000; i > 0;)
            {
                int k = EntityXPOrb.getMore(i);
                i -= k;
                worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, k));
            }

        }
        moveEntity(0.0D, 0.10000000149011612D, 0.0D);
        renderYawOffset = rotationYaw += 20F;
        if(field_40158_r == 200)
        {
            for(int j = 10000; j > 0;)
            {
                int l = EntityXPOrb.getMore(j);
                j -= l;
                worldObj.spawnEntityInWorld(new EntityXPOrb(worldObj, posX, posY, posZ, l));
            }

            int i1 = (5 + rand.nextInt(2) * 2) - 1;
            int j1 = (5 + rand.nextInt(2) * 2) - 1;
            if(rand.nextInt(2) == 0)
            {
                i1 = 0;
            } else
            {
                j1 = 0;
            }
            func_41038_a(MathHelper.floor_double(posX), MathHelper.floor_double(posZ));
            onEntityDeath();
            setEntityDead();
        }
    }

    private void func_41038_a(int i, int j)
    {
        int k = worldObj.worldYMax / 2;
        BlockEndPortal.field_41003_a = true;
        int l = 4;
        for(int i1 = k - 1; i1 <= k + 32; i1++)
        {
            for(int j1 = i - l; j1 <= i + l; j1++)
            {
                for(int k1 = j - l; k1 <= j + l; k1++)
                {
                    double d = j1 - i;
                    double d1 = k1 - j;
                    double d2 = MathHelper.sqrt_double(d * d + d1 * d1);
                    if(d2 > (double)l - 0.5D)
                    {
                        continue;
                    }
                    if(i1 < k)
                    {
                        if(d2 <= (double)(l - 1) - 0.5D)
                        {
                            worldObj.setBlockWithNotify(j1, i1, k1, Block.bedrock.blockID);
                        }
                        continue;
                    }
                    if(i1 > k)
                    {
                        worldObj.setBlockWithNotify(j1, i1, k1, 0);
                        continue;
                    }
                    if(d2 > (double)(l - 1) - 0.5D)
                    {
                        worldObj.setBlockWithNotify(j1, i1, k1, Block.bedrock.blockID);
                    } else
                    {
                        worldObj.setBlockWithNotify(j1, i1, k1, Block.endPortal.blockID);
                    }
                }

            }

        }

        worldObj.setBlockWithNotify(i, k + 0, j, Block.bedrock.blockID);
        worldObj.setBlockWithNotify(i, k + 1, j, Block.bedrock.blockID);
        worldObj.setBlockWithNotify(i, k + 2, j, Block.bedrock.blockID);
        worldObj.setBlockWithNotify(i - 1, k + 2, j, Block.torchWood.blockID);
        worldObj.setBlockWithNotify(i + 1, k + 2, j, Block.torchWood.blockID);
        worldObj.setBlockWithNotify(i, k + 2, j - 1, Block.torchWood.blockID);
        worldObj.setBlockWithNotify(i, k + 2, j + 1, Block.torchWood.blockID);
        worldObj.setBlockWithNotify(i, k + 3, j, Block.bedrock.blockID);
        worldObj.setBlockWithNotify(i, k + 4, j, Block.dragonEgg.blockID);
        BlockEndPortal.field_41003_a = false;
    }

    protected void despawnEntity()
    {
    }

    public Entity[] getParts()
    {
        return dragonPartArray;
    }

    public boolean canBeCollidedWith()
    {
        return false;
    }
}
