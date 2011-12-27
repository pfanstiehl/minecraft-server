// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            Container, SlotEnchantmentTable, SlotEnchantment, Slot, 
//            ICrafting, IInventory, ItemStack, World, 
//            Block, EnchantmentHelper, EntityPlayer, EnchantmentData, 
//            InventoryPlayer

public class ContainerEnchantment extends Container
{

    public IInventory tableInventory;
    private World worldPointer;
    private int field_40339_i;
    private int field_40336_j;
    private int field_40337_k;
    private Random field_40335_l;
    public long nameSeed;
    public int enchantLevels[];

    public ContainerEnchantment(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        tableInventory = new SlotEnchantmentTable(this, "Enchant", 1);
        field_40335_l = new Random();
        enchantLevels = new int[3];
        worldPointer = world;
        field_40339_i = i;
        field_40336_j = j;
        field_40337_k = k;
        addSlot(new SlotEnchantment(this, tableInventory, 0, 25, 47));
        for(int l = 0; l < 3; l++)
        {
            for(int j1 = 0; j1 < 9; j1++)
            {
                addSlot(new Slot(inventoryplayer, j1 + l * 9 + 9, 8 + j1 * 18, 84 + l * 18));
            }

        }

        for(int i1 = 0; i1 < 9; i1++)
        {
            addSlot(new Slot(inventoryplayer, i1, 8 + i1 * 18, 142));
        }

    }

    public void onCraftGuiOpened(ICrafting icrafting)
    {
        super.onCraftGuiOpened(icrafting);
        icrafting.updateCraftingInventoryInfo(this, 0, enchantLevels[0]);
        icrafting.updateCraftingInventoryInfo(this, 1, enchantLevels[1]);
        icrafting.updateCraftingInventoryInfo(this, 2, enchantLevels[2]);
    }

    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            icrafting.updateCraftingInventoryInfo(this, 0, enchantLevels[0]);
            icrafting.updateCraftingInventoryInfo(this, 1, enchantLevels[1]);
            icrafting.updateCraftingInventoryInfo(this, 2, enchantLevels[2]);
        }

    }

    public void onCraftMatrixChanged(IInventory iinventory)
    {
        if(iinventory == tableInventory)
        {
            ItemStack itemstack = iinventory.getStackInSlot(0);
            if(itemstack == null || !itemstack.func_40606_q())
            {
                for(int i = 0; i < 3; i++)
                {
                    enchantLevels[i] = 0;
                }

            } else
            {
                nameSeed = field_40335_l.nextLong();
                if(!worldPointer.singleplayerWorld)
                {
                    int j = 0;
                    for(int k = -1; k <= 1; k++)
                    {
                        for(int i1 = -1; i1 <= 1; i1++)
                        {
                            if(k == 0 && i1 == 0 || !worldPointer.isAirBlock(field_40339_i + i1, field_40336_j, field_40337_k + k) || !worldPointer.isAirBlock(field_40339_i + i1, field_40336_j + 1, field_40337_k + k))
                            {
                                continue;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1 * 2, field_40336_j, field_40337_k + k * 2) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1 * 2, field_40336_j + 1, field_40337_k + k * 2) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                            if(i1 == 0 || k == 0)
                            {
                                continue;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1 * 2, field_40336_j, field_40337_k + k) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1 * 2, field_40336_j + 1, field_40337_k + k) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1, field_40336_j, field_40337_k + k * 2) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                            if(worldPointer.getBlockId(field_40339_i + i1, field_40336_j + 1, field_40337_k + k * 2) == Block.bookShelf.blockID)
                            {
                                j++;
                            }
                        }

                    }

                    for(int l = 0; l < 3; l++)
                    {
                        enchantLevels[l] = EnchantmentHelper.calcItemStackEnchantability(field_40335_l, l, j, itemstack);
                    }

                    updateCraftingResults();
                }
            }
        }
    }

    public boolean enchantItem(EntityPlayer entityplayer, int i)
    {
        ItemStack itemstack = tableInventory.getStackInSlot(0);
        if(enchantLevels[i] > 0 && itemstack != null && entityplayer.experienceLevel >= enchantLevels[i])
        {
            if(!worldPointer.singleplayerWorld)
            {
                List list = EnchantmentHelper.buildEnchantmentList(field_40335_l, itemstack, enchantLevels[i]);
                if(list != null)
                {
                    entityplayer.removeExperience(enchantLevels[i]);
                    EnchantmentData enchantmentdata;
                    for(Iterator iterator = list.iterator(); iterator.hasNext(); itemstack.addEnchantment(enchantmentdata.field_40494_a, enchantmentdata.field_40493_b))
                    {
                        enchantmentdata = (EnchantmentData)iterator.next();
                    }

                    onCraftMatrixChanged(tableInventory);
                }
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void onCraftGuiClosed(EntityPlayer entityplayer)
    {
        super.onCraftGuiClosed(entityplayer);
        if(worldPointer.singleplayerWorld)
        {
            return;
        }
        ItemStack itemstack = tableInventory.getStackInSlot(0);
        if(itemstack != null)
        {
            entityplayer.dropPlayerItem(itemstack);
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if(worldPointer.getBlockId(field_40339_i, field_40336_j, field_40337_k) != Block.enchantmentTable.blockID)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)field_40339_i + 0.5D, (double)field_40336_j + 0.5D, (double)field_40337_k + 0.5D) <= 64D;
    }

    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 0)
            {
                if(!mergeItemStack(itemstack1, 1, 37, true))
                {
                    return null;
                }
            } else
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
