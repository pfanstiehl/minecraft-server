// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            InventoryBasic, ContainerEnchantment

class SlotEnchantmentTable extends InventoryBasic
{

    final ContainerEnchantment field_40088_a; /* synthetic field */

    SlotEnchantmentTable(ContainerEnchantment containerenchantment, String s, int i)
    {
        super(s, i);
        field_40088_a = containerenchantment;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        field_40088_a.onCraftMatrixChanged(this);
    }
}
