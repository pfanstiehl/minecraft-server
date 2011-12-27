// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Item, NBTTagCompound, StatList, 
//            EntityPlayer, EnchantmentHelper, EntityLiving, World, 
//            NBTTagList, Enchantment, Entity, EnumAction

public final class ItemStack
{

    public int stackSize;
    public int animationsToGo;
    public int itemID;
    public NBTTagCompound itemNBT;
    private int itemDamage;

    public ItemStack(Block block)
    {
        this(block, 1);
    }

    public ItemStack(Block block, int i)
    {
        this(block.blockID, i, 0);
    }

    public ItemStack(Block block, int i, int j)
    {
        this(block.blockID, i, j);
    }

    public ItemStack(Item item)
    {
        this(item.shiftedIndex, 1, 0);
    }

    public ItemStack(Item item, int i)
    {
        this(item.shiftedIndex, i, 0);
    }

    public ItemStack(Item item, int i, int j)
    {
        this(item.shiftedIndex, i, j);
    }

    public ItemStack(int i, int j, int k)
    {
        stackSize = 0;
        itemID = i;
        stackSize = j;
        itemDamage = k;
    }

    public static ItemStack loadItemStackFromNBT(NBTTagCompound nbttagcompound)
    {
        ItemStack itemstack = new ItemStack();
        itemstack.readFromNBT(nbttagcompound);
        return itemstack.getItem() == null ? null : itemstack;
    }

    private ItemStack()
    {
        stackSize = 0;
    }

    public ItemStack splitStack(int i)
    {
        ItemStack itemstack = new ItemStack(itemID, i, itemDamage);
        if(itemNBT != null)
        {
            itemstack.itemNBT = (NBTTagCompound)itemNBT.cloneTag();
        }
        stackSize -= i;
        return itemstack;
    }

    public Item getItem()
    {
        return Item.itemsList[itemID];
    }

    public boolean useItem(EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        boolean flag = getItem().onItemUse(this, entityplayer, world, i, j, k, l);
        if(flag)
        {
            entityplayer.addStat(StatList.objectUseStats[itemID], 1);
        }
        return flag;
    }

    public float getStrVsBlock(Block block)
    {
        return getItem().getStrVsBlock(this, block);
    }

    public ItemStack useItemRightClick(World world, EntityPlayer entityplayer)
    {
        return getItem().onItemRightClick(this, world, entityplayer);
    }

    public ItemStack onFoodEaten(World world, EntityPlayer entityplayer)
    {
        return getItem().onFoodEaten(this, world, entityplayer);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("id", (short)itemID);
        nbttagcompound.setByte("Count", (byte)stackSize);
        nbttagcompound.setShort("Damage", (short)itemDamage);
        if(itemNBT != null)
        {
            nbttagcompound.setTag("tag", itemNBT);
        }
        return nbttagcompound;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        itemID = nbttagcompound.getShort("id");
        stackSize = nbttagcompound.getByte("Count");
        itemDamage = nbttagcompound.getShort("Damage");
        if(nbttagcompound.hasKey("tag"))
        {
            itemNBT = nbttagcompound.getCompoundTag("tag");
        }
    }

    public int getMaxStackSize()
    {
        return getItem().getItemStackLimit();
    }

    public boolean isStackable()
    {
        return getMaxStackSize() > 1 && (!isItemStackDamageable() || !isItemDamaged());
    }

    public boolean isItemStackDamageable()
    {
        return Item.itemsList[itemID].getMaxDamage() > 0;
    }

    public boolean getHasSubtypes()
    {
        return Item.itemsList[itemID].getHasSubtypes();
    }

    public boolean isItemDamaged()
    {
        return isItemStackDamageable() && itemDamage > 0;
    }

    public int getItemDamageForDisplay()
    {
        return itemDamage;
    }

    public int getItemDamage()
    {
        return itemDamage;
    }

    public void setItemDamage(int i)
    {
        itemDamage = i;
    }

    public int getMaxDamage()
    {
        return Item.itemsList[itemID].getMaxDamage();
    }

    public void damageItem(int i, EntityLiving entityliving)
    {
        if(!isItemStackDamageable())
        {
            return;
        }
        if(i > 0 && (entityliving instanceof EntityPlayer))
        {
            int j = EnchantmentHelper.getUnbreakingModifier(((EntityPlayer)entityliving).inventory);
            if(j > 0 && entityliving.worldObj.rand.nextInt(j + 1) > 0)
            {
                return;
            }
        }
        itemDamage += i;
        if(itemDamage > getMaxDamage())
        {
            entityliving.func_41030_c(this);
            if(entityliving instanceof EntityPlayer)
            {
                ((EntityPlayer)entityliving).addStat(StatList.objectBreakStats[itemID], 1);
            }
            stackSize--;
            if(stackSize < 0)
            {
                stackSize = 0;
            }
            itemDamage = 0;
        }
    }

    public void hitEntity(EntityLiving entityliving, EntityPlayer entityplayer)
    {
        boolean flag = Item.itemsList[itemID].hitEntity(this, entityliving, entityplayer);
        if(flag)
        {
            entityplayer.addStat(StatList.objectUseStats[itemID], 1);
        }
    }

    public void onDestroyBlock(int i, int j, int k, int l, EntityPlayer entityplayer)
    {
        boolean flag = Item.itemsList[itemID].onBlockDestroyed(this, i, j, k, l, entityplayer);
        if(flag)
        {
            entityplayer.addStat(StatList.objectUseStats[itemID], 1);
        }
    }

    public int getDamageVsEntity(Entity entity)
    {
        return Item.itemsList[itemID].getDamageVsEntity(entity);
    }

    public boolean canHarvestBlock(Block block)
    {
        return Item.itemsList[itemID].canHarvestBlock(block);
    }

    public void onItemDestroyedByUse(EntityPlayer entityplayer)
    {
    }

    public void useItemOnEntity(EntityLiving entityliving)
    {
        Item.itemsList[itemID].saddleEntity(this, entityliving);
    }

    public ItemStack copy()
    {
        ItemStack itemstack = new ItemStack(itemID, stackSize, itemDamage);
        if(itemNBT != null)
        {
            itemstack.itemNBT = (NBTTagCompound)itemNBT.cloneTag();
            if(!itemstack.itemNBT.equals(itemNBT))
            {
                return itemstack;
            }
        }
        return itemstack;
    }

    public static boolean areItemStacksEqual(ItemStack itemstack, ItemStack itemstack1)
    {
        if(itemstack == null && itemstack1 == null)
        {
            return true;
        }
        if(itemstack == null || itemstack1 == null)
        {
            return false;
        } else
        {
            return itemstack.isItemStackEqual(itemstack1);
        }
    }

    private boolean isItemStackEqual(ItemStack itemstack)
    {
        if(stackSize != itemstack.stackSize)
        {
            return false;
        }
        if(itemID != itemstack.itemID)
        {
            return false;
        }
        if(itemDamage != itemstack.itemDamage)
        {
            return false;
        }
        if(itemNBT == null && itemstack.itemNBT != null)
        {
            return false;
        }
        return itemNBT == null || itemNBT.equals(itemstack.itemNBT);
    }

    public boolean isItemEqual(ItemStack itemstack)
    {
        return itemID == itemstack.itemID && itemDamage == itemstack.itemDamage;
    }

    public String func_35616_k()
    {
        return Item.itemsList[itemID].func_35407_a(this);
    }

    public static ItemStack copyItemStack(ItemStack itemstack)
    {
        return itemstack != null ? itemstack.copy() : null;
    }

    public String toString()
    {
        return (new StringBuilder()).append(stackSize).append("x").append(Item.itemsList[itemID].getItemName()).append("@").append(itemDamage).toString();
    }

    public void updateAnimation(World world, Entity entity, int i, boolean flag)
    {
        if(animationsToGo > 0)
        {
            animationsToGo--;
        }
        Item.itemsList[itemID].onUpdate(this, world, entity, i, flag);
    }

    public void onCrafting(World world, EntityPlayer entityplayer)
    {
        entityplayer.addStat(StatList.objectCraftStats[itemID], stackSize);
        Item.itemsList[itemID].onCreated(this, world, entityplayer);
    }

    public boolean isStackEqual(ItemStack itemstack)
    {
        return itemID == itemstack.itemID && stackSize == itemstack.stackSize && itemDamage == itemstack.itemDamage;
    }

    public int getMaxItemUseDuration()
    {
        return getItem().getMaxItemUseDuration(this);
    }

    public EnumAction getItemUseAction()
    {
        return getItem().getAction(this);
    }

    public void onPlayerStoppedUsing(World world, EntityPlayer entityplayer, int i)
    {
        getItem().onPlayerStoppedUsing(this, world, entityplayer, i);
    }

    public boolean hasTagCompound()
    {
        return itemNBT != null;
    }

    public NBTTagCompound getTagCompound()
    {
        return itemNBT;
    }

    public NBTTagList getEnchantmentTagList()
    {
        if(itemNBT == null)
        {
            return null;
        } else
        {
            return (NBTTagList)itemNBT.getTag("ench");
        }
    }

    public void setNBTData(NBTTagCompound nbttagcompound)
    {
        if(Item.itemsList[itemID].getItemStackLimit() != 1)
        {
            throw new IllegalArgumentException("Cannot add tag data to a stackable item");
        } else
        {
            itemNBT = nbttagcompound;
            return;
        }
    }

    public boolean func_40606_q()
    {
        if(!getItem().func_40222_e(this))
        {
            return false;
        }
        return !func_40610_r();
    }

    public void addEnchantment(Enchantment enchantment, int i)
    {
        if(itemNBT == null)
        {
            setNBTData(new NBTTagCompound());
        }
        if(!itemNBT.hasKey("ench"))
        {
            itemNBT.setTag("ench", new NBTTagList("ench"));
        }
        NBTTagList nbttaglist = (NBTTagList)itemNBT.getTag("ench");
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short)enchantment.effectId);
        nbttagcompound.setShort("lvl", (byte)i);
        nbttaglist.setTag(nbttagcompound);
    }

    public boolean func_40610_r()
    {
        return itemNBT != null && itemNBT.hasKey("ench");
    }
}
