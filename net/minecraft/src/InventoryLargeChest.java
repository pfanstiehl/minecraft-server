// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            IInventory, ItemStack, EntityPlayer

public class InventoryLargeChest
    implements IInventory
{

    private String name;
    private IInventory upperChest;
    private IInventory lowerChest;

    public InventoryLargeChest(String s, IInventory iinventory, IInventory iinventory1)
    {
        name = s;
        if(iinventory == null)
        {
            iinventory = iinventory1;
        }
        if(iinventory1 == null)
        {
            iinventory1 = iinventory;
        }
        upperChest = iinventory;
        lowerChest = iinventory1;
    }

    public int getSizeInventory()
    {
        return upperChest.getSizeInventory() + lowerChest.getSizeInventory();
    }

    public String getInvName()
    {
        return name;
    }

    public ItemStack getStackInSlot(int i)
    {
        if(i >= upperChest.getSizeInventory())
        {
            return lowerChest.getStackInSlot(i - upperChest.getSizeInventory());
        } else
        {
            return upperChest.getStackInSlot(i);
        }
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(i >= upperChest.getSizeInventory())
        {
            return lowerChest.decrStackSize(i - upperChest.getSizeInventory(), j);
        } else
        {
            return upperChest.decrStackSize(i, j);
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        if(i >= upperChest.getSizeInventory())
        {
            lowerChest.setInventorySlotContents(i - upperChest.getSizeInventory(), itemstack);
        } else
        {
            upperChest.setInventorySlotContents(i, itemstack);
        }
    }

    public int getInventoryStackLimit()
    {
        return upperChest.getInventoryStackLimit();
    }

    public void onInventoryChanged()
    {
        upperChest.onInventoryChanged();
        lowerChest.onInventoryChanged();
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return upperChest.isUseableByPlayer(entityplayer) && lowerChest.isUseableByPlayer(entityplayer);
    }

    public void openChest()
    {
        upperChest.openChest();
        lowerChest.openChest();
    }

    public void closeChest()
    {
        upperChest.closeChest();
        lowerChest.closeChest();
    }
}
