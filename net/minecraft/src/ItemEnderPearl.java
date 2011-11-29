// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Item, EntityPlayer, PlayerCapabilities, ItemStack, 
//            World, EntityEnderPearl

public class ItemEnderPearl extends Item
{

    public ItemEnderPearl(int i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(entityplayer.field_35214_K.depleteBuckets)
        {
            return itemstack;
        }
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!world.singleplayerWorld)
        {
            world.entityJoinedWorld(new EntityEnderPearl(world, entityplayer));
        }
        return itemstack;
    }
}
