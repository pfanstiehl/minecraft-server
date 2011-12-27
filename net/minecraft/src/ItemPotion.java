// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, PotionHelper, World, 
//            PotionEffect, EntityPlayer, InventoryPlayer, EnumAction, 
//            EntityPotion

public class ItemPotion extends Item
{

    private HashMap effectCache;

    public ItemPotion(int i)
    {
        super(i);
        effectCache = new HashMap();
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    public List getPotionEffectsForItemStack(ItemStack itemstack)
    {
        return getPotionEffectsForDamage(itemstack.getItemDamage());
    }

    public List getPotionEffectsForDamage(int i)
    {
        List list = (List)effectCache.get(Integer.valueOf(i));
        if(list == null)
        {
            list = PotionHelper.getPotionEffects(i, false);
            effectCache.put(Integer.valueOf(i), list);
        }
        return list;
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        if(!world.singleplayerWorld)
        {
            List list = getPotionEffectsForItemStack(itemstack);
            if(list != null)
            {
                PotionEffect potioneffect;
                for(Iterator iterator = list.iterator(); iterator.hasNext(); entityplayer.addPotionEffect(new PotionEffect(potioneffect)))
                {
                    potioneffect = (PotionEffect)iterator.next();
                }

            }
        }
        if(itemstack.stackSize <= 0)
        {
            return new ItemStack(Item.glassBottle);
        } else
        {
            entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
            return itemstack;
        }
    }

    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 32;
    }

    public EnumAction getAction(ItemStack itemstack)
    {
        return EnumAction.drink;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(isSplash(itemstack.getItemDamage()))
        {
            itemstack.stackSize--;
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.singleplayerWorld)
            {
                world.spawnEntityInWorld(new EntityPotion(world, entityplayer, itemstack.getItemDamage()));
            }
            return itemstack;
        } else
        {
            entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
            return itemstack;
        }
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        return false;
    }

    public static boolean isSplash(int i)
    {
        return (i & 0x4000) != 0;
    }
}
