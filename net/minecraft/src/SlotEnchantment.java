// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Slot, ContainerEnchantment, IInventory, ItemStack

class SlotEnchantment extends Slot
{

    final ContainerEnchantment field_40268_a; /* synthetic field */

    SlotEnchantment(ContainerEnchantment containerenchantment, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        field_40268_a = containerenchantment;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return true;
    }
}
