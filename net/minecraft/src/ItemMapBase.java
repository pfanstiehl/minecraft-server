// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, ItemStack, World, EntityPlayer, 
//            Packet

public class ItemMapBase extends Item
{

    protected ItemMapBase(int i)
    {
        super(i);
    }

    public boolean func_28019_b()
    {
        return true;
    }

    public Packet getUpdatePacket(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        return null;
    }
}
