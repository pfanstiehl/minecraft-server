// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            EntityLiving, InventoryPlayer, FoodStats, PlayerCapabilities, 
//            ContainerPlayer, World, ChunkCoordinates, DataWatcher, 
//            ItemStack, Item, EnumAction, Container, 
//            StatList, Vec3D, Potion, PotionEffect, 
//            MathHelper, AxisAlignedBB, Entity, EnchantmentHelper, 
//            EntityItem, Material, NBTTagCompound, NBTTagList, 
//            DamageSource, EntityMob, EntityArrow, EntityCreeper, 
//            EntityGhast, EntityWolf, AchievementList, EnumStatus, 
//            WorldProvider, BlockBed, Block, IChunkProvider, 
//            EntityMinecart, EntityBoat, EntityPig, EntityFishHook, 
//            IInventory, TileEntityFurnace, TileEntityDispenser, TileEntitySign, 
//            TileEntityBrewingStand, StatBase

public abstract class EntityPlayer extends EntityLiving
{

    public InventoryPlayer inventory;
    public Container personalCraftingInventory;
    public Container currentCraftingInventory;
    protected FoodStats foodStats;
    protected int flyToggleTimer;
    public byte field_9152_am;
    public int score;
    public float prevCameraYaw;
    public float cameraYaw;
    public boolean isSwinging;
    public int swingProgressInt;
    public String username;
    public int dimension;
    public int xpCooldown;
    public double field_20047_ay;
    public double field_20046_az;
    public double field_20051_aA;
    public double field_20050_aB;
    public double field_20049_aC;
    public double field_20048_aD;
    protected boolean sleeping;
    public ChunkCoordinates playerLocation;
    private int sleepTimer;
    public float field_22066_z;
    public float field_22067_A;
    private ChunkCoordinates spawnChunk;
    private ChunkCoordinates startMinecartRidingCoordinate;
    public int timeUntilPortal;
    protected boolean inPortal;
    public float timeInPortal;
    public PlayerCapabilities capabilities;
    public int experienceLevel;
    public int experienceTotal;
    public float experience;
    private ItemStack itemInUse;
    private int itemInUseCount;
    protected float speedOnGround;
    protected float speedInAir;
    public EntityFishHook fishEntity;

    public EntityPlayer(World world)
    {
        super(world);
        inventory = new InventoryPlayer(this);
        foodStats = new FoodStats();
        flyToggleTimer = 0;
        field_9152_am = 0;
        score = 0;
        isSwinging = false;
        swingProgressInt = 0;
        xpCooldown = 0;
        timeUntilPortal = 20;
        inPortal = false;
        capabilities = new PlayerCapabilities();
        speedOnGround = 0.1F;
        speedInAir = 0.02F;
        fishEntity = null;
        personalCraftingInventory = new ContainerPlayer(inventory, !world.singleplayerWorld);
        currentCraftingInventory = personalCraftingInventory;
        yOffset = 1.62F;
        ChunkCoordinates chunkcoordinates = world.getSpawnPoint();
        setLocationAndAngles((double)chunkcoordinates.posX + 0.5D, chunkcoordinates.posY + 1, (double)chunkcoordinates.posZ + 0.5D, 0.0F, 0.0F);
        entityType = "humanoid";
        field_9117_aI = 180F;
        fireResistance = 20;
        texture = "/mob/char.png";
    }

    public int getMaxHealth()
    {
        return 20;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, Byte.valueOf((byte)0));
        dataWatcher.addObject(17, Byte.valueOf((byte)0));
    }

    public boolean isUsingItem()
    {
        return itemInUse != null;
    }

    public void stopUsingItem()
    {
        if(itemInUse != null)
        {
            itemInUse.onPlayerStoppedUsing(worldObj, this, itemInUseCount);
        }
        clearItemInUse();
    }

    public void clearItemInUse()
    {
        itemInUse = null;
        itemInUseCount = 0;
        if(!worldObj.singleplayerWorld)
        {
            setEating(false);
        }
    }

    public boolean func_35180_G()
    {
        return isUsingItem() && Item.itemsList[itemInUse.itemID].getAction(itemInUse) == EnumAction.block;
    }

    public void onUpdate()
    {
        if(itemInUse != null)
        {
            ItemStack itemstack = inventory.getCurrentItem();
            if(itemstack != itemInUse)
            {
                clearItemInUse();
            } else
            {
                if(itemInUseCount <= 25 && itemInUseCount % 4 == 0)
                {
                    func_35208_b(itemstack, 5);
                }
                if(--itemInUseCount == 0 && !worldObj.singleplayerWorld)
                {
                    func_35199_C();
                }
            }
        }
        if(xpCooldown > 0)
        {
            xpCooldown--;
        }
        if(isPlayerSleeping())
        {
            sleepTimer++;
            if(sleepTimer > 100)
            {
                sleepTimer = 100;
            }
            if(!worldObj.singleplayerWorld)
            {
                if(!isInBed())
                {
                    wakeUpPlayer(true, true, false);
                } else
                if(worldObj.isDaytime())
                {
                    wakeUpPlayer(false, true, true);
                }
            }
        } else
        if(sleepTimer > 0)
        {
            sleepTimer++;
            if(sleepTimer >= 110)
            {
                sleepTimer = 0;
            }
        }
        super.onUpdate();
        if(!worldObj.singleplayerWorld && currentCraftingInventory != null && !currentCraftingInventory.canInteractWith(this))
        {
            usePersonalCraftingInventory();
            currentCraftingInventory = personalCraftingInventory;
        }
        if(capabilities.isFlying)
        {
            for(int i = 0; i < 8; i++) { }
        }
        if(isBurning() && capabilities.disableDamage)
        {
            extinguish();
        }
        field_20047_ay = field_20050_aB;
        field_20046_az = field_20049_aC;
        field_20051_aA = field_20048_aD;
        double d = posX - field_20050_aB;
        double d1 = posY - field_20049_aC;
        double d2 = posZ - field_20048_aD;
        double d3 = 10D;
        if(d > d3)
        {
            field_20047_ay = field_20050_aB = posX;
        }
        if(d2 > d3)
        {
            field_20051_aA = field_20048_aD = posZ;
        }
        if(d1 > d3)
        {
            field_20046_az = field_20049_aC = posY;
        }
        if(d < -d3)
        {
            field_20047_ay = field_20050_aB = posX;
        }
        if(d2 < -d3)
        {
            field_20051_aA = field_20048_aD = posZ;
        }
        if(d1 < -d3)
        {
            field_20046_az = field_20049_aC = posY;
        }
        field_20050_aB += d * 0.25D;
        field_20048_aD += d2 * 0.25D;
        field_20049_aC += d1 * 0.25D;
        addStat(StatList.minutesPlayedStat, 1);
        if(ridingEntity == null)
        {
            startMinecartRidingCoordinate = null;
        }
        if(!worldObj.singleplayerWorld)
        {
            foodStats.update(this);
        }
    }

    protected void func_35208_b(ItemStack itemstack, int i)
    {
        if(itemstack.getItemUseAction() == EnumAction.drink)
        {
            worldObj.playSoundAtEntity(this, "random.drink", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
        if(itemstack.getItemUseAction() == EnumAction.eat)
        {
            for(int j = 0; j < i; j++)
            {
                Vec3D vec3d = Vec3D.createVector(((double)rand.nextFloat() - 0.5D) * 0.10000000000000001D, Math.random() * 0.10000000000000001D + 0.10000000000000001D, 0.0D);
                vec3d.rotateAroundX((-rotationPitch * 3.141593F) / 180F);
                vec3d.rotateAroundY((-rotationYaw * 3.141593F) / 180F);
                Vec3D vec3d1 = Vec3D.createVector(((double)rand.nextFloat() - 0.5D) * 0.29999999999999999D, (double)(-rand.nextFloat()) * 0.59999999999999998D - 0.29999999999999999D, 0.59999999999999998D);
                vec3d1.rotateAroundX((-rotationPitch * 3.141593F) / 180F);
                vec3d1.rotateAroundY((-rotationYaw * 3.141593F) / 180F);
                vec3d1 = vec3d1.addVector(posX, posY + (double)getEyeHeight(), posZ);
                worldObj.spawnParticle((new StringBuilder()).append("iconcrack_").append(itemstack.getItem().shiftedIndex).toString(), vec3d1.xCoord, vec3d1.yCoord, vec3d1.zCoord, vec3d.xCoord, vec3d.yCoord + 0.050000000000000003D, vec3d.zCoord);
            }

            worldObj.playSoundAtEntity(this, "random.eat", 0.5F + 0.5F * (float)rand.nextInt(2), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void func_35199_C()
    {
        if(itemInUse != null)
        {
            func_35208_b(itemInUse, 16);
            int i = itemInUse.stackSize;
            ItemStack itemstack = itemInUse.onFoodEaten(worldObj, this);
            if(itemstack != itemInUse || itemstack != null && itemstack.stackSize != i)
            {
                inventory.mainInventory[inventory.currentItem] = itemstack;
                if(itemstack.stackSize == 0)
                {
                    inventory.mainInventory[inventory.currentItem] = null;
                }
            }
            clearItemInUse();
        }
    }

    protected boolean isMovementBlocked()
    {
        return getEntityHealth() <= 0 || isPlayerSleeping();
    }

    protected void usePersonalCraftingInventory()
    {
        currentCraftingInventory = personalCraftingInventory;
    }

    public void updateRidden()
    {
        double d = posX;
        double d1 = posY;
        double d2 = posZ;
        super.updateRidden();
        prevCameraYaw = cameraYaw;
        cameraYaw = 0.0F;
        addMountedMovementStat(posX - d, posY - d1, posZ - d2);
    }

    private int func_35204_o()
    {
        if(isPotionActive(Potion.digSpeed))
        {
            return 6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1;
        }
        if(isPotionActive(Potion.digSlowdown))
        {
            return 6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
        } else
        {
            return 6;
        }
    }

    protected void updateEntityActionState()
    {
        int i = func_35204_o();
        if(isSwinging)
        {
            swingProgressInt++;
            if(swingProgressInt >= i)
            {
                swingProgressInt = 0;
                isSwinging = false;
            }
        } else
        {
            swingProgressInt = 0;
        }
        swingProgress = (float)swingProgressInt / (float)i;
    }

    public void onLivingUpdate()
    {
        if(flyToggleTimer > 0)
        {
            flyToggleTimer--;
        }
        if(worldObj.difficultySetting == 0 && getEntityHealth() < getMaxHealth() && (ticksExisted % 20) * 12 == 0)
        {
            heal(1);
        }
        inventory.decrementAnimations();
        prevCameraYaw = cameraYaw;
        super.onLivingUpdate();
        landMovementFactor = speedOnGround;
        jumpMovementFactor = speedInAir;
        if(isSprinting())
        {
            landMovementFactor += (double)speedOnGround * 0.29999999999999999D;
            jumpMovementFactor += (double)speedInAir * 0.29999999999999999D;
        }
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround || getEntityHealth() <= 0)
        {
            f = 0.0F;
        }
        if(onGround || getEntityHealth() <= 0)
        {
            f1 = 0.0F;
        }
        cameraYaw += (f - cameraYaw) * 0.4F;
        cameraPitch += (f1 - cameraPitch) * 0.8F;
        if(getEntityHealth() > 0)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    Entity entity = (Entity)list.get(i);
                    if(!entity.isDead)
                    {
                        collideWithPlayer(entity);
                    }
                }

            }
        }
    }

    private void collideWithPlayer(Entity entity)
    {
        entity.onCollideWithPlayer(this);
    }

    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);
        setSize(0.2F, 0.2F);
        setPosition(posX, posY, posZ);
        motionY = 0.10000000149011612D;
        if(username.equals("Notch"))
        {
            dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
        }
        inventory.dropAllItems();
        if(damagesource != null)
        {
            motionX = -MathHelper.cos(((attackedAtYaw + rotationYaw) * 3.141593F) / 180F) * 0.1F;
            motionZ = -MathHelper.sin(((attackedAtYaw + rotationYaw) * 3.141593F) / 180F) * 0.1F;
        } else
        {
            motionX = motionZ = 0.0D;
        }
        yOffset = 0.1F;
        addStat(StatList.deathsStat, 1);
    }

    public void addToPlayerScore(Entity entity, int i)
    {
        score += i;
        if(entity instanceof EntityPlayer)
        {
            addStat(StatList.playerKillsStat, 1);
        } else
        {
            addStat(StatList.mobKillsStat, 1);
        }
    }

    protected int func_40094_f(int i)
    {
        int j = EnchantmentHelper.getRespiration(inventory);
        if(j > 0 && rand.nextInt(j + 1) > 0)
        {
            return i;
        } else
        {
            return super.func_40094_f(i);
        }
    }

    public void dropCurrentItem()
    {
        dropPlayerItemWithRandomChoice(inventory.decrStackSize(inventory.currentItem, 1), false);
    }

    public void dropPlayerItem(ItemStack itemstack)
    {
        dropPlayerItemWithRandomChoice(itemstack, false);
    }

    public void dropPlayerItemWithRandomChoice(ItemStack itemstack, boolean flag)
    {
        if(itemstack == null)
        {
            return;
        }
        EntityItem entityitem = new EntityItem(worldObj, posX, (posY - 0.30000001192092896D) + (double)getEyeHeight(), posZ, itemstack);
        entityitem.delayBeforeCanPickup = 40;
        float f = 0.1F;
        if(flag)
        {
            float f2 = rand.nextFloat() * 0.5F;
            float f4 = rand.nextFloat() * 3.141593F * 2.0F;
            entityitem.motionX = -MathHelper.sin(f4) * f2;
            entityitem.motionZ = MathHelper.cos(f4) * f2;
            entityitem.motionY = 0.20000000298023224D;
        } else
        {
            float f1 = 0.3F;
            entityitem.motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f1 + 0.1F;
            f1 = 0.02F;
            float f3 = rand.nextFloat() * 3.141593F * 2.0F;
            f1 *= rand.nextFloat();
            entityitem.motionX += Math.cos(f3) * (double)f1;
            entityitem.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            entityitem.motionZ += Math.sin(f3) * (double)f1;
        }
        joinEntityItemWithWorld(entityitem);
        addStat(StatList.dropStat, 1);
    }

    protected void joinEntityItemWithWorld(EntityItem entityitem)
    {
        worldObj.spawnEntityInWorld(entityitem);
    }

    public float getCurrentPlayerStrVsBlock(Block block)
    {
        float f = inventory.getStrVsBlock(block);
        float f1 = f;
        int i = EnchantmentHelper.getEfficiencyModifier(inventory);
        if(i > 0 && inventory.canHarvestBlock(block))
        {
            f1 += i * i + 1;
        }
        if(isPotionActive(Potion.digSpeed))
        {
            f1 *= 1.0F + (float)(getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
        }
        if(isPotionActive(Potion.digSlowdown))
        {
            f1 *= 1.0F - (float)(getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
        }
        if(isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(inventory))
        {
            f1 /= 5F;
        }
        if(!onGround)
        {
            f1 /= 5F;
        }
        return f1;
    }

    public boolean canHarvestBlock(Block block)
    {
        return inventory.canHarvestBlock(block);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Inventory");
        inventory.readFromNBT(nbttaglist);
        dimension = nbttagcompound.getInteger("Dimension");
        sleeping = nbttagcompound.getBoolean("Sleeping");
        sleepTimer = nbttagcompound.getShort("SleepTimer");
        experience = nbttagcompound.getFloat("XpP");
        experienceLevel = nbttagcompound.getInteger("XpLevel");
        experienceTotal = nbttagcompound.getInteger("XpTotal");
        if(sleeping)
        {
            playerLocation = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
            wakeUpPlayer(true, true, false);
        }
        if(nbttagcompound.hasKey("SpawnX") && nbttagcompound.hasKey("SpawnY") && nbttagcompound.hasKey("SpawnZ"))
        {
            spawnChunk = new ChunkCoordinates(nbttagcompound.getInteger("SpawnX"), nbttagcompound.getInteger("SpawnY"), nbttagcompound.getInteger("SpawnZ"));
        }
        foodStats.readNBT(nbttagcompound);
        capabilities.readCapabilitiesFromNBT(nbttagcompound);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setTag("Inventory", inventory.writeToNBT(new NBTTagList()));
        nbttagcompound.setInteger("Dimension", dimension);
        nbttagcompound.setBoolean("Sleeping", sleeping);
        nbttagcompound.setShort("SleepTimer", (short)sleepTimer);
        nbttagcompound.setFloat("XpP", experience);
        nbttagcompound.setInteger("XpLevel", experienceLevel);
        nbttagcompound.setInteger("XpTotal", experienceTotal);
        if(spawnChunk != null)
        {
            nbttagcompound.setInteger("SpawnX", spawnChunk.posX);
            nbttagcompound.setInteger("SpawnY", spawnChunk.posY);
            nbttagcompound.setInteger("SpawnZ", spawnChunk.posZ);
        }
        foodStats.writeNBT(nbttagcompound);
        capabilities.writeCapabilitiesToNBT(nbttagcompound);
    }

    public void displayGUIChest(IInventory iinventory)
    {
    }

    public void displayGUIEnchantment(int i, int j, int k)
    {
    }

    public void displayWorkbenchGUI(int i, int j, int k)
    {
    }

    public void onItemPickup(Entity entity, int i)
    {
    }

    public float getEyeHeight()
    {
        return 0.12F;
    }

    protected void resetHeight()
    {
        yOffset = 1.62F;
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if(capabilities.disableDamage && !damagesource.canHarmInCreative())
        {
            return false;
        }
        entityAge = 0;
        if(getEntityHealth() <= 0)
        {
            return false;
        }
        if(isPlayerSleeping() && !worldObj.singleplayerWorld)
        {
            wakeUpPlayer(true, true, false);
        }
        Entity entity = damagesource.getEntity();
        if((entity instanceof EntityMob) || (entity instanceof EntityArrow))
        {
            if(worldObj.difficultySetting == 0)
            {
                i = 0;
            }
            if(worldObj.difficultySetting == 1)
            {
                i = i / 2 + 1;
            }
            if(worldObj.difficultySetting == 3)
            {
                i = (i * 3) / 2;
            }
        }
        if(i == 0)
        {
            return false;
        }
        Entity entity1 = entity;
        if((entity1 instanceof EntityArrow) && ((EntityArrow)entity1).shootingEntity != null)
        {
            entity1 = ((EntityArrow)entity1).shootingEntity;
        }
        if(entity1 instanceof EntityLiving)
        {
            alertWolves((EntityLiving)entity1, false);
        }
        addStat(StatList.damageTakenStat, i);
        return super.attackEntityFrom(damagesource, i);
    }

    protected int applyPotionDamageCalculations(DamageSource damagesource, int i)
    {
        int j = super.applyPotionDamageCalculations(damagesource, i);
        if(j <= 0)
        {
            return 0;
        }
        int k = EnchantmentHelper.getEnchantmentModifierDamage(inventory, damagesource);
        if(k > 20)
        {
            k = 20;
        }
        if(k > 0 && k <= 20)
        {
            int l = 25 - k;
            int i1 = j * l + carryoverDamage;
            j = i1 / 25;
            carryoverDamage = i1 % 25;
        }
        return j;
    }

    protected boolean isPVPEnabled()
    {
        return false;
    }

    protected void alertWolves(EntityLiving entityliving, boolean flag)
    {
        if((entityliving instanceof EntityCreeper) || (entityliving instanceof EntityGhast))
        {
            return;
        }
        if(entityliving instanceof EntityWolf)
        {
            EntityWolf entitywolf = (EntityWolf)entityliving;
            if(entitywolf.isTamed() && username.equals(entitywolf.getOwner()))
            {
                return;
            }
        }
        if((entityliving instanceof EntityPlayer) && !isPVPEnabled())
        {
            return;
        }
        List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityWolf.class, AxisAlignedBB.getBoundingBoxFromPool(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(16D, 4D, 16D));
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            Entity entity = (Entity)iterator.next();
            EntityWolf entitywolf1 = (EntityWolf)entity;
            if(entitywolf1.isTamed() && entitywolf1.getEntityToAttack() == null && username.equals(entitywolf1.getOwner()) && (!flag || !entitywolf1.isSitting()))
            {
                entitywolf1.setIsSitting(false);
                entitywolf1.setTarget(entityliving);
            }
        } while(true);
    }

    protected void func_40101_g(int i)
    {
        inventory.damageArmor(i);
    }

    protected int getTotalArmorValue()
    {
        return inventory.getTotalArmorValue();
    }

    protected void damageEntity(DamageSource damagesource, int i)
    {
        if(!damagesource.isUnblockable() && func_35180_G())
        {
            i = 1 + i >> 1;
        }
        i = applyArmorCalculations(damagesource, i);
        i = applyPotionDamageCalculations(damagesource, i);
        addExhaustion(damagesource.getHungerDamage());
        super.damageEntity(damagesource, i);
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
    }

    public void displayGUIDispenser(TileEntityDispenser tileentitydispenser)
    {
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
    }

    public void displayGUIBrewingStand(TileEntityBrewingStand tileentitybrewingstand)
    {
    }

    public void useCurrentItemOnEntity(Entity entity)
    {
        if(entity.interact(this))
        {
            return;
        }
        ItemStack itemstack = getCurrentEquippedItem();
        if(itemstack != null && (entity instanceof EntityLiving))
        {
            itemstack.useItemOnEntity((EntityLiving)entity);
            if(itemstack.stackSize <= 0)
            {
                itemstack.onItemDestroyedByUse(this);
                destroyCurrentEquippedItem();
            }
        }
    }

    public ItemStack getCurrentEquippedItem()
    {
        return inventory.getCurrentItem();
    }

    public void destroyCurrentEquippedItem()
    {
        inventory.setInventorySlotContents(inventory.currentItem, null);
    }

    public double getYOffset()
    {
        return (double)(yOffset - 0.5F);
    }

    public void swingItem()
    {
        if(!isSwinging || swingProgressInt >= func_35204_o() / 2 || swingProgressInt < 0)
        {
            swingProgressInt = -1;
            isSwinging = true;
        }
    }

    public void attackTargetEntityWithCurrentItem(Entity entity)
    {
        int i = inventory.getDamageVsEntity(entity);
        if(isPotionActive(Potion.damageBoost))
        {
            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if(isPotionActive(Potion.weakness))
        {
            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        int j = 0;
        int k = 0;
        if(entity instanceof EntityLiving)
        {
            k = EnchantmentHelper.getEnchantmentModifierLiving(inventory, (EntityLiving)entity);
            j += EnchantmentHelper.getKnockbackModifier(inventory, (EntityLiving)entity);
        }
        if(isSprinting())
        {
            j++;
        }
        if(i > 0 || k > 0)
        {
            boolean flag = fallDistance > 0.0F && !onGround && !isOnLadder() && !isInWater() && !isPotionActive(Potion.blindness) && ridingEntity == null && (entity instanceof EntityLiving);
            if(flag)
            {
                i += rand.nextInt(i / 2 + 2);
            }
            i += k;
            boolean flag1 = entity.attackEntityFrom(DamageSource.causePlayerDamage(this), i);
            if(flag1)
            {
                if(j > 0)
                {
                    entity.addVelocity(-MathHelper.sin((rotationYaw * 3.141593F) / 180F) * (float)j * 0.5F, 0.10000000000000001D, MathHelper.cos((rotationYaw * 3.141593F) / 180F) * (float)j * 0.5F);
                    motionX *= 0.59999999999999998D;
                    motionZ *= 0.59999999999999998D;
                    setSprinting(false);
                }
                if(flag)
                {
                    onCriticalHit(entity);
                }
                if(k > 0)
                {
                    func_40109_c(entity);
                }
                if(i >= 18)
                {
                    triggerAchievement(AchievementList.overkill);
                }
            }
            ItemStack itemstack = getCurrentEquippedItem();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.hitEntity((EntityLiving)entity, this);
                if(itemstack.stackSize <= 0)
                {
                    itemstack.onItemDestroyedByUse(this);
                    destroyCurrentEquippedItem();
                }
            }
            if(entity instanceof EntityLiving)
            {
                if(entity.isEntityAlive())
                {
                    alertWolves((EntityLiving)entity, true);
                }
                addStat(StatList.damageDealtStat, i);
                int l = EnchantmentHelper.getFireAspectModifier(inventory, (EntityLiving)entity);
                if(l > 0)
                {
                    entity.setFire(l * 4);
                }
            }
            addExhaustion(0.3F);
        }
    }

    public void onCriticalHit(Entity entity)
    {
    }

    public void func_40109_c(Entity entity)
    {
    }

    public void onItemStackChanged(ItemStack itemstack)
    {
    }

    public void setEntityDead()
    {
        super.setEntityDead();
        personalCraftingInventory.onCraftGuiClosed(this);
        if(currentCraftingInventory != null)
        {
            currentCraftingInventory.onCraftGuiClosed(this);
        }
    }

    public boolean isEntityInsideOpaqueBlock()
    {
        return !sleeping && super.isEntityInsideOpaqueBlock();
    }

    public EnumStatus sleepInBedAt(int i, int j, int k)
    {
        if(!worldObj.singleplayerWorld)
        {
            if(isPlayerSleeping() || !isEntityAlive())
            {
                return EnumStatus.OTHER_PROBLEM;
            }
            if(worldObj.worldProvider.canSleepInWorld)
            {
                return EnumStatus.NOT_POSSIBLE_HERE;
            }
            if(worldObj.isDaytime())
            {
                return EnumStatus.NOT_POSSIBLE_NOW;
            }
            if(Math.abs(posX - (double)i) > 3D || Math.abs(posY - (double)j) > 2D || Math.abs(posZ - (double)k) > 3D)
            {
                return EnumStatus.TOO_FAR_AWAY;
            }
            double d = 8D;
            double d1 = 5D;
            List list = worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityMob.class, AxisAlignedBB.getBoundingBoxFromPool((double)i - d, (double)j - d1, (double)k - d, (double)i + d, (double)j + d1, (double)k + d));
            if(!list.isEmpty())
            {
                return EnumStatus.NOT_SAFE;
            }
        }
        setSize(0.2F, 0.2F);
        yOffset = 0.2F;
        if(worldObj.blockExists(i, j, k))
        {
            int l = worldObj.getBlockMetadata(i, j, k);
            int i1 = BlockBed.getDirectionFromMetadata(l);
            float f = 0.5F;
            float f1 = 0.5F;
            switch(i1)
            {
            case 0: // '\0'
                f1 = 0.9F;
                break;

            case 2: // '\002'
                f1 = 0.1F;
                break;

            case 1: // '\001'
                f = 0.1F;
                break;

            case 3: // '\003'
                f = 0.9F;
                break;
            }
            func_22059_e(i1);
            setPosition((float)i + f, (float)j + 0.9375F, (float)k + f1);
        } else
        {
            setPosition((float)i + 0.5F, (float)j + 0.9375F, (float)k + 0.5F);
        }
        sleeping = true;
        sleepTimer = 0;
        playerLocation = new ChunkCoordinates(i, j, k);
        motionX = motionZ = motionY = 0.0D;
        if(!worldObj.singleplayerWorld)
        {
            worldObj.updateAllPlayersSleepingFlag();
        }
        return EnumStatus.OK;
    }

    private void func_22059_e(int i)
    {
        field_22066_z = 0.0F;
        field_22067_A = 0.0F;
        switch(i)
        {
        case 0: // '\0'
            field_22067_A = -1.8F;
            break;

        case 2: // '\002'
            field_22067_A = 1.8F;
            break;

        case 1: // '\001'
            field_22066_z = 1.8F;
            break;

        case 3: // '\003'
            field_22066_z = -1.8F;
            break;
        }
    }

    public void wakeUpPlayer(boolean flag, boolean flag1, boolean flag2)
    {
        setSize(0.6F, 1.8F);
        resetHeight();
        ChunkCoordinates chunkcoordinates = playerLocation;
        ChunkCoordinates chunkcoordinates1 = playerLocation;
        if(chunkcoordinates != null && worldObj.getBlockId(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ) == Block.bed.blockID)
        {
            BlockBed.setBedOccupied(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, false);
            ChunkCoordinates chunkcoordinates2 = BlockBed.getNearestEmptyChunkCoordinates(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, 0);
            if(chunkcoordinates2 == null)
            {
                chunkcoordinates2 = new ChunkCoordinates(chunkcoordinates.posX, chunkcoordinates.posY + 1, chunkcoordinates.posZ);
            }
            setPosition((float)chunkcoordinates2.posX + 0.5F, (float)chunkcoordinates2.posY + yOffset + 0.1F, (float)chunkcoordinates2.posZ + 0.5F);
        }
        sleeping = false;
        if(!worldObj.singleplayerWorld && flag1)
        {
            worldObj.updateAllPlayersSleepingFlag();
        }
        if(flag)
        {
            sleepTimer = 0;
        } else
        {
            sleepTimer = 100;
        }
        if(flag2)
        {
            setSpawnChunk(playerLocation);
        }
    }

    private boolean isInBed()
    {
        return worldObj.getBlockId(playerLocation.posX, playerLocation.posY, playerLocation.posZ) == Block.bed.blockID;
    }

    public static ChunkCoordinates verifyBedCoordinates(World world, ChunkCoordinates chunkcoordinates)
    {
        IChunkProvider ichunkprovider = world.getChunkProvider();
        ichunkprovider.loadChunk(chunkcoordinates.posX - 3 >> 4, chunkcoordinates.posZ - 3 >> 4);
        ichunkprovider.loadChunk(chunkcoordinates.posX + 3 >> 4, chunkcoordinates.posZ - 3 >> 4);
        ichunkprovider.loadChunk(chunkcoordinates.posX - 3 >> 4, chunkcoordinates.posZ + 3 >> 4);
        ichunkprovider.loadChunk(chunkcoordinates.posX + 3 >> 4, chunkcoordinates.posZ + 3 >> 4);
        if(world.getBlockId(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ) != Block.bed.blockID)
        {
            return null;
        } else
        {
            ChunkCoordinates chunkcoordinates1 = BlockBed.getNearestEmptyChunkCoordinates(world, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, 0);
            return chunkcoordinates1;
        }
    }

    public boolean isPlayerSleeping()
    {
        return sleeping;
    }

    public boolean isPlayerFullyAsleep()
    {
        return sleeping && sleepTimer >= 100;
    }

    public void addChatMessage(String s)
    {
    }

    public ChunkCoordinates getSpawnChunk()
    {
        return spawnChunk;
    }

    public void setSpawnChunk(ChunkCoordinates chunkcoordinates)
    {
        if(chunkcoordinates != null)
        {
            spawnChunk = new ChunkCoordinates(chunkcoordinates);
        } else
        {
            spawnChunk = null;
        }
    }

    public void triggerAchievement(StatBase statbase)
    {
        addStat(statbase, 1);
    }

    public void addStat(StatBase statbase, int i)
    {
    }

    protected void jump()
    {
        super.jump();
        addStat(StatList.jumpStat, 1);
        if(isSprinting())
        {
            addExhaustion(0.8F);
        } else
        {
            addExhaustion(0.2F);
        }
    }

    public void moveEntityWithHeading(float f, float f1)
    {
        double d = posX;
        double d1 = posY;
        double d2 = posZ;
        if(capabilities.isFlying)
        {
            double d3 = motionY;
            float f2 = jumpMovementFactor;
            jumpMovementFactor = 0.05F;
            super.moveEntityWithHeading(f, f1);
            motionY = d3 * 0.59999999999999998D;
            jumpMovementFactor = f2;
        } else
        {
            super.moveEntityWithHeading(f, f1);
        }
        addMovementStat(posX - d, posY - d1, posZ - d2);
    }

    public void addMovementStat(double d, double d1, double d2)
    {
        if(ridingEntity != null)
        {
            return;
        }
        if(isInsideOfMaterial(Material.water))
        {
            int i = Math.round(MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2) * 100F);
            if(i > 0)
            {
                addStat(StatList.distanceDoveStat, i);
                addExhaustion(0.015F * (float)i * 0.01F);
            }
        } else
        if(isInWater())
        {
            int j = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(j > 0)
            {
                addStat(StatList.distanceSwumStat, j);
                addExhaustion(0.015F * (float)j * 0.01F);
            }
        } else
        if(isOnLadder())
        {
            if(d1 > 0.0D)
            {
                addStat(StatList.distanceClimbedStat, (int)Math.round(d1 * 100D));
            }
        } else
        if(onGround)
        {
            int k = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(k > 0)
            {
                addStat(StatList.distanceWalkedStat, k);
                if(isSprinting())
                {
                    addExhaustion(0.09999999F * (float)k * 0.01F);
                } else
                {
                    addExhaustion(0.01F * (float)k * 0.01F);
                }
            }
        } else
        {
            int l = Math.round(MathHelper.sqrt_double(d * d + d2 * d2) * 100F);
            if(l > 25)
            {
                addStat(StatList.distanceFlownStat, l);
            }
        }
    }

    private void addMountedMovementStat(double d, double d1, double d2)
    {
        if(ridingEntity != null)
        {
            int i = Math.round(MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2) * 100F);
            if(i > 0)
            {
                if(ridingEntity instanceof EntityMinecart)
                {
                    addStat(StatList.distanceByMinecartStat, i);
                    if(startMinecartRidingCoordinate == null)
                    {
                        startMinecartRidingCoordinate = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
                    } else
                    if(startMinecartRidingCoordinate.getSqDistanceTo(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) >= 1000D)
                    {
                        addStat(AchievementList.onARail, 1);
                    }
                } else
                if(ridingEntity instanceof EntityBoat)
                {
                    addStat(StatList.distanceByBoatStat, i);
                } else
                if(ridingEntity instanceof EntityPig)
                {
                    addStat(StatList.distanceByPigStat, i);
                }
            }
        }
    }

    protected void fall(float f)
    {
        if(capabilities.allowFlying)
        {
            return;
        }
        if(f >= 2.0F)
        {
            addStat(StatList.distanceFallenStat, (int)Math.round((double)f * 100D));
        }
        super.fall(f);
    }

    public void onKillEntity(EntityLiving entityliving)
    {
        if(entityliving instanceof EntityMob)
        {
            triggerAchievement(AchievementList.killEnemy);
        }
    }

    public void setInPortal()
    {
        if(timeUntilPortal > 0)
        {
            timeUntilPortal = 10;
            return;
        } else
        {
            inPortal = true;
            return;
        }
    }

    public void addExperience(int i)
    {
        score += i;
        experience += (float)i / (float)func_35203_U();
        experienceTotal += i;
        while(experience >= 1.0F) 
        {
            experience--;
            increaseLevel();
        }
    }

    public void removeExperience(int i)
    {
        experienceLevel -= i;
        if(experienceLevel < 0)
        {
            experienceLevel = 0;
        }
    }

    public int func_35203_U()
    {
        return 7 + (experienceLevel * 7 >> 1);
    }

    private void increaseLevel()
    {
        experienceLevel++;
    }

    public void addExhaustion(float f)
    {
        if(capabilities.disableDamage)
        {
            return;
        }
        if(!worldObj.singleplayerWorld)
        {
            foodStats.addExhaustion(f);
        }
    }

    public FoodStats getFoodStats()
    {
        return foodStats;
    }

    public boolean canEat(boolean flag)
    {
        return (flag || foodStats.mustEat()) && !capabilities.disableDamage;
    }

    public boolean mustHeal()
    {
        return getEntityHealth() > 0 && getEntityHealth() < getMaxHealth();
    }

    public void setItemInUse(ItemStack itemstack, int i)
    {
        if(itemstack == itemInUse)
        {
            return;
        }
        itemInUse = itemstack;
        itemInUseCount = i;
        if(!worldObj.singleplayerWorld)
        {
            setEating(true);
        }
    }

    public boolean canPlayerEdit(int i, int j, int k)
    {
        return true;
    }

    protected int getExperiencePoints(EntityPlayer entityplayer)
    {
        int i = experienceLevel * 7;
        if(i > 100)
        {
            return 100;
        } else
        {
            return i;
        }
    }

    protected boolean func_35188_X()
    {
        return true;
    }

    public String func_35150_Y()
    {
        return username;
    }

    public void func_40107_e(int i)
    {
    }

    public void copyPlayer(EntityPlayer entityplayer)
    {
        inventory.copyInventory(entityplayer.inventory);
        health = entityplayer.health;
        foodStats = entityplayer.foodStats;
        experienceLevel = entityplayer.experienceLevel;
        experienceTotal = entityplayer.experienceTotal;
        experience = entityplayer.experience;
        score = entityplayer.score;
    }
}
