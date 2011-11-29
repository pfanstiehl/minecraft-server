// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Slot, Item, ItemStack, ContainerBrewingStand, 
//            IInventory

class SlotBrewingStandIngredient extends Slot
{

    final ContainerBrewingStand field_40267_a; /* synthetic field */

    public SlotBrewingStandIngredient(ContainerBrewingStand containerbrewingstand, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        field_40267_a = containerbrewingstand;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        if(itemstack != null)
        {
            return Item.itemsList[itemstack.itemID].func_40220_m();
        } else
        {
            return false;
        }
    }

    public int getSlotStackLimit()
    {
        return 64;
    }
}
