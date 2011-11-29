// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemBlock, ItemDye, ItemStack, BlockCloth

public class ItemCloth extends ItemBlock
{

    public ItemCloth(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int i)
    {
        return i;
    }

    public String func_35407_a(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getItemName()).append(".").append(ItemDye.dyeColorNames[BlockCloth.getBlockFromDye(itemstack.getItemDamage())]).toString();
    }
}
