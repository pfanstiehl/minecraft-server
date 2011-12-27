// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, DataWatcher, NBTTagCompound, World, 
//            EntityPlayer, EntitySheep, AxisAlignedBB, Entity, 
//            InventoryPlayer, ItemStack, Item, ItemFood, 
//            MathHelper, DamageSource, EntityArrow, EntityLiving

public class EntityWolf extends EntityAnimal
{

    private boolean looksWithInterest;
    private float field_25038_b;
    private float field_25044_c;
    private boolean isWet;
    private boolean field_25042_g;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntityWolf(World world)
    {
        super(world);
        looksWithInterest = false;
        texture = "/mob/wolf.png";
        setSize(0.8F, 0.8F);
        moveSpeed = 1.1F;
    }

    public int getMaxHealth()
    {
        return !isTamed() ? 8 : 20;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
        dataWatcher.addObject(17, "");
        dataWatcher.addObject(18, new Integer(getEntityHealth()));
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Angry", isAngry());
        nbttagcompound.setBoolean("Sitting", isSitting());
        if(getOwner() == null)
        {
            nbttagcompound.setString("Owner", "");
        } else
        {
            nbttagcompound.setString("Owner", getOwner());
        }
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setAngry(nbttagcompound.getBoolean("Angry"));
        setIsSitting(nbttagcompound.getBoolean("Sitting"));
        String s = nbttagcompound.getString("Owner");
        if(s.length() > 0)
        {
            setOwner(s);
            setIsTamed(true);
        }
    }

    protected boolean canDespawn()
    {
        return isAngry();
    }

    protected String getLivingSound()
    {
        if(isAngry())
        {
            return "mob.wolf.growl";
        }
        if(rand.nextInt(3) == 0)
        {
            if(isTamed() && dataWatcher.getWatchableObjectInt(18) < 10)
            {
                return "mob.wolf.whine";
            } else
            {
                return "mob.wolf.panting";
            }
        } else
        {
            return "mob.wolf.bark";
        }
    }

    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return -1;
    }

    protected void updateEntityActionState()
    {
        super.updateEntityActionState();
        if(!hasAttacked && !hasPath() && isTamed() && ridingEntity == null)
        {
            EntityPlayer entityplayer = worldObj.getPlayerEntityByName(getOwner());
            if(entityplayer != null)
            {
                float f = entityplayer.getDistanceToEntity(this);
                if(f > 5F)
                {
                    setPathEntity(entityplayer, f);
                }
            } else
            if(!isInWater())
            {
                setIsSitting(true);
            }
        } else
        if(entityToAttack == null && !hasPath() && !isTamed() && worldObj.rand.nextInt(100) == 0)
        {
            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntitySheep.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
            if(!list.isEmpty())
            {
                setTarget((Entity)list.get(worldObj.rand.nextInt(list.size())));
            }
        }
        if(isInWater())
        {
            setIsSitting(false);
        }
        if(!worldObj.singleplayerWorld)
        {
            dataWatcher.updateObject(18, Integer.valueOf(getEntityHealth()));
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        looksWithInterest = false;
        if(hasCurrentTarget() && !hasPath() && !isAngry())
        {
            Entity entity = getCurrentTarget();
            if(entity instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entity;
                ItemStack itemstack = entityplayer.inventory.getCurrentItem();
                if(itemstack != null)
                {
                    if(!isTamed() && itemstack.itemID == Item.bone.shiftedIndex)
                    {
                        looksWithInterest = true;
                    } else
                    if(isTamed() && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
                    {
                        looksWithInterest = ((ItemFood)Item.itemsList[itemstack.itemID]).getIsWolfsFavoriteMeat();
                    }
                }
            }
        }
        if(!isMultiplayerEntity && isWet && !field_25042_g && !hasPath() && onGround)
        {
            field_25042_g = true;
            timeWolfIsShaking = 0.0F;
            prevTimeWolfIsShaking = 0.0F;
            worldObj.sendTrackedEntityStatusUpdatePacket(this, (byte)8);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        field_25044_c = field_25038_b;
        if(looksWithInterest)
        {
            field_25038_b = field_25038_b + (1.0F - field_25038_b) * 0.4F;
        } else
        {
            field_25038_b = field_25038_b + (0.0F - field_25038_b) * 0.4F;
        }
        if(looksWithInterest)
        {
            numTicksToChaseTarget = 10;
        }
        if(isWet())
        {
            isWet = true;
            field_25042_g = false;
            timeWolfIsShaking = 0.0F;
            prevTimeWolfIsShaking = 0.0F;
        } else
        if((isWet || field_25042_g) && field_25042_g)
        {
            if(timeWolfIsShaking == 0.0F)
            {
                worldObj.playSoundAtEntity(this, "mob.wolf.shake", getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
            prevTimeWolfIsShaking = timeWolfIsShaking;
            timeWolfIsShaking += 0.05F;
            if(prevTimeWolfIsShaking >= 2.0F)
            {
                isWet = false;
                field_25042_g = false;
                prevTimeWolfIsShaking = 0.0F;
                timeWolfIsShaking = 0.0F;
            }
            if(timeWolfIsShaking > 0.4F)
            {
                float f = (float)boundingBox.minY;
                int i = (int)(MathHelper.sin((timeWolfIsShaking - 0.4F) * 3.141593F) * 7F);
                for(int j = 0; j < i; j++)
                {
                    float f1 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    float f2 = (rand.nextFloat() * 2.0F - 1.0F) * width * 0.5F;
                    worldObj.spawnParticle("splash", posX + (double)f1, f + 0.8F, posZ + (double)f2, motionX, motionY, motionZ);
                }

            }
        }
    }

    public float getEyeHeight()
    {
        return height * 0.8F;
    }

    protected int getVerticalFaceSpeed()
    {
        if(isSitting())
        {
            return 20;
        } else
        {
            return super.getVerticalFaceSpeed();
        }
    }

    private void setPathEntity(Entity entity, float f)
    {
        PathEntity pathentity = worldObj.getPathToEntity(this, entity, 16F);
        if(pathentity == null && f > 12F)
        {
            int i = MathHelper.floor_double(entity.posX) - 2;
            int j = MathHelper.floor_double(entity.posZ) - 2;
            int k = MathHelper.floor_double(entity.boundingBox.minY);
            for(int l = 0; l <= 4; l++)
            {
                for(int i1 = 0; i1 <= 4; i1++)
                {
                    if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && worldObj.isBlockNormalCube(i + l, k - 1, j + i1) && !worldObj.isBlockNormalCube(i + l, k, j + i1) && !worldObj.isBlockNormalCube(i + l, k + 1, j + i1))
                    {
                        setLocationAndAngles((float)(i + l) + 0.5F, k, (float)(j + i1) + 0.5F, rotationYaw, rotationPitch);
                        return;
                    }
                }

            }

        } else
        {
            setPathToEntity(pathentity);
        }
    }

    protected boolean isMovementCeased()
    {
        return isSitting() || field_25042_g;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        setIsSitting(false);
        if(entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
        {
            i = (i + 1) / 2;
        }
        if(super.attackEntityFrom(damagesource, i))
        {
            if(!isTamed() && !isAngry())
            {
                if(entity instanceof EntityPlayer)
                {
                    setAngry(true);
                    entityToAttack = entity;
                }
                if((entity instanceof EntityArrow) && ((EntityArrow)entity).shootingEntity != null)
                {
                    entity = ((EntityArrow)entity).shootingEntity;
                }
                if(entity instanceof EntityLiving)
                {
                    List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityWolf.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
                    Iterator iterator = list.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                        {
                            break;
                        }
                        Entity entity1 = (Entity)iterator.next();
                        EntityWolf entitywolf = (EntityWolf)entity1;
                        if(!entitywolf.isTamed() && entitywolf.entityToAttack == null)
                        {
                            entitywolf.entityToAttack = entity;
                            if(entity instanceof EntityPlayer)
                            {
                                entitywolf.setAngry(true);
                            }
                        }
                    } while(true);
                }
            } else
            if(entity != this && entity != null)
            {
                if(isTamed() && (entity instanceof EntityPlayer) && ((EntityPlayer)entity).username.equalsIgnoreCase(getOwner()))
                {
                    return true;
                }
                entityToAttack = entity;
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected Entity findPlayerToAttack()
    {
        if(isAngry())
        {
            return worldObj.getClosestPlayerToEntity(this, 16D);
        } else
        {
            return null;
        }
    }

    protected void attackEntity(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && rand.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f1) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f1) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        } else
        if((double)f < 1.5D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            byte byte0 = 2;
            if(isTamed())
            {
                byte0 = 4;
            }
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), byte0);
        }
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(!isTamed())
        {
            if(itemstack != null && itemstack.itemID == Item.bone.shiftedIndex && !isAngry())
            {
                itemstack.stackSize--;
                if(itemstack.stackSize <= 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                if(!worldObj.singleplayerWorld)
                {
                    if(rand.nextInt(3) == 0)
                    {
                        setIsTamed(true);
                        setPathToEntity(null);
                        setIsSitting(true);
                        setEntityHealth(20);
                        setOwner(entityplayer.username);
                        isNowTamed(true);
                        worldObj.sendTrackedEntityStatusUpdatePacket(this, (byte)7);
                    } else
                    {
                        isNowTamed(false);
                        worldObj.sendTrackedEntityStatusUpdatePacket(this, (byte)6);
                    }
                }
                return true;
            }
        } else
        {
            if(itemstack != null && (Item.itemsList[itemstack.itemID] instanceof ItemFood))
            {
                ItemFood itemfood = (ItemFood)Item.itemsList[itemstack.itemID];
                if(itemfood.getIsWolfsFavoriteMeat() && dataWatcher.getWatchableObjectInt(18) < 20)
                {
                    itemstack.stackSize--;
                    heal(itemfood.getHealAmount());
                    if(itemstack.stackSize <= 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    return true;
                }
            }
            if(entityplayer.username.equalsIgnoreCase(getOwner()))
            {
                if(!worldObj.singleplayerWorld)
                {
                    setIsSitting(!isSitting());
                    isJumping = false;
                    setPathToEntity(null);
                }
                return true;
            }
        }
        return super.interact(entityplayer);
    }

    void isNowTamed(boolean flag)
    {
        String s = "heart";
        if(!flag)
        {
            s = "smoke";
        }
        for(int i = 0; i < 7; i++)
        {
            double d = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            worldObj.spawnParticle(s, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
        }

    }

    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    public String getOwner()
    {
        return dataWatcher.getWatchableObjectString(17);
    }

    public void setOwner(String s)
    {
        dataWatcher.updateObject(17, s);
    }

    public boolean isSitting()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setIsSitting(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 1)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -2)));
        }
    }

    public boolean isAngry()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setAngry(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 2)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -3)));
        }
    }

    public boolean isTamed()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 4) != 0;
    }

    public void setIsTamed(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);
        if(flag)
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 | 4)));
        } else
        {
            dataWatcher.updateObject(16, Byte.valueOf((byte)(byte0 & -5)));
        }
    }

    protected EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal)
    {
        return new EntityWolf(worldObj);
    }
}
