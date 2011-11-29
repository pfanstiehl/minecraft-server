// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, World, EntityPlayer, 
//            EnumAction

public class ItemBucketMilk extends Item
{

    public ItemBucketMilk(int i)
    {
        super(i);
        setMaxStackSize(1);
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        if(!world.singleplayerWorld)
        {
            entityplayer.func_40089_ar();
        }
        if(itemstack.stackSize <= 0)
        {
            return new ItemStack(Item.bucketEmpty);
        } else
        {
            return itemstack;
        }
    }

    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 32;
    }

    public EnumAction func_35406_b(ItemStack itemstack)
    {
        return EnumAction.drink;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.func_35201_a(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }
}
