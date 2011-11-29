// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, EntityPlayer, FoodStats, 
//            World, PotionEffect, EnumAction

public class ItemFood extends Item
{

    public final int field_35427_a = 32;
    private final int healAmount;
    private final float saturationFactor;
    private final boolean isWolfsFavoriteMeat;
    private boolean field_35428_bw;
    private int field_35430_bx;
    private int field_35429_by;
    private int field_35425_bz;
    private float field_35426_bA;

    public ItemFood(int i, int j, float f, boolean flag)
    {
        super(i);
        healAmount = j;
        isWolfsFavoriteMeat = flag;
        saturationFactor = f;
    }

    public ItemFood(int i, int j, boolean flag)
    {
        this(i, j, 0.6F, flag);
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        entityplayer.func_35207_V().eatFood(this);
        world.playSoundAtEntity(entityplayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        if(!world.singleplayerWorld && field_35430_bx > 0 && world.rand.nextFloat() < field_35426_bA)
        {
            entityplayer.func_35182_d(new PotionEffect(field_35430_bx, field_35429_by * 20, field_35425_bz));
        }
        return itemstack;
    }

    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 32;
    }

    public EnumAction func_35406_b(ItemStack itemstack)
    {
        return EnumAction.eat;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(entityplayer.func_35197_c(field_35428_bw))
        {
            entityplayer.func_35201_a(itemstack, getMaxItemUseDuration(itemstack));
        }
        return itemstack;
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    public float getSaturationFactor()
    {
        return saturationFactor;
    }

    public boolean getIsWolfsFavoriteMeat()
    {
        return isWolfsFavoriteMeat;
    }

    public ItemFood setPotionEffect(int i, int j, int k, float f)
    {
        field_35430_bx = i;
        field_35429_by = j;
        field_35425_bz = k;
        field_35426_bA = f;
        return this;
    }

    public ItemFood func_35423_n()
    {
        field_35428_bw = true;
        return this;
    }

    public Item setItemName(String s)
    {
        return super.setItemName(s);
    }
}
