// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, SlotBrewingStandPotion, InventoryPlayer, SlotBrewingStandIngredient, 
//            Slot, TileEntityBrewingStand, ICrafting, ItemStack, 
//            EntityPlayer

public class ContainerBrewingStand extends Container
{

    private TileEntityBrewingStand field_40341_a;
    private int field_40340_b;

    public ContainerBrewingStand(InventoryPlayer inventoryplayer, TileEntityBrewingStand tileentitybrewingstand)
    {
        field_40340_b = 0;
        field_40341_a = tileentitybrewingstand;
        addSlot(new SlotBrewingStandPotion(this, inventoryplayer.player, tileentitybrewingstand, 0, 56, 46));
        addSlot(new SlotBrewingStandPotion(this, inventoryplayer.player, tileentitybrewingstand, 1, 79, 53));
        addSlot(new SlotBrewingStandPotion(this, inventoryplayer.player, tileentitybrewingstand, 2, 102, 46));
        addSlot(new SlotBrewingStandIngredient(this, tileentitybrewingstand, 3, 79, 17));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    public void onCraftGuiOpened(ICrafting icrafting)
    {
        super.onCraftGuiOpened(icrafting);
        icrafting.updateCraftingInventoryInfo(this, 0, field_40341_a.func_40077_h());
    }

    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            if(field_40340_b != field_40341_a.func_40077_h())
            {
                icrafting.updateCraftingInventoryInfo(this, 0, field_40341_a.func_40077_h());
            }
        }

        field_40340_b = field_40341_a.func_40077_h();
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return field_40341_a.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i >= 0 && i <= 2 || i == 3)
            {
                if(!mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }
            } else
            if(i >= 4 && i < 31)
            {
                if(!mergeItemStack(itemstack1, 31, 40, false))
                {
                    return null;
                }
            } else
            if(i >= 31 && i < 40)
            {
                if(!mergeItemStack(itemstack1, 4, 31, false))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 4, 40, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }
}
