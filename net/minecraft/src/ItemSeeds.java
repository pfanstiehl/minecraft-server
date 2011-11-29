// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPlayer, World, ItemStack

public class ItemSeeds extends Item
{

    private int cropID;
    private int field_40253_bQ;

    public ItemSeeds(int i, int j, int k)
    {
        super(i);
        cropID = j;
        field_40253_bQ = k;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l != 1)
        {
            return false;
        }
        if(!entityplayer.func_35200_c(i, j, k) || !entityplayer.func_35200_c(i, j + 1, k))
        {
            return false;
        }
        int i1 = world.getBlockId(i, j, k);
        if(i1 == field_40253_bQ && world.isAirBlock(i, j + 1, k))
        {
            world.setBlockWithNotify(i, j + 1, k, cropID);
            itemstack.stackSize--;
            return true;
        } else
        {
            return false;
        }
    }
}
