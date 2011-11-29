// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, ItemStack

public class ItemCoal extends Item
{

    public ItemCoal(int i)
    {
        super(i);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    public String func_35407_a(ItemStack itemstack)
    {
        if(itemstack.getItemDamage() == 1)
        {
            return "item.charcoal";
        } else
        {
            return "item.coal";
        }
    }
}
