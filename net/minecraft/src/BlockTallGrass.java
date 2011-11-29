// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockFlower, Item, World, EntityPlayer, 
//            ItemStack, ItemShears, StatList, Block

public class BlockTallGrass extends BlockFlower
{

    protected BlockTallGrass(int i, int j)
    {
        super(i, j);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(j == 1)
        {
            return blockIndexInTexture;
        }
        if(j == 2)
        {
            return blockIndexInTexture + 16 + 1;
        }
        if(j == 0)
        {
            return blockIndexInTexture + 16;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public int idDropped(int i, Random random, int j)
    {
        if(random.nextInt(8) == 0)
        {
            return Item.seeds.shiftedIndex;
        } else
        {
            return -1;
        }
    }

    public int func_40162_a(int i, Random random)
    {
        return 1 + random.nextInt(i * 2 + 1);
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
        if(!world.singleplayerWorld && entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().itemID == Item.shears.shiftedIndex)
        {
            entityplayer.addStat(StatList.mineBlockStatArray[blockID], 1);
            dropBlockAsItem_do(world, i, j, k, new ItemStack(Block.tallGrass, 1, l));
        } else
        {
            super.harvestBlock(world, entityplayer, i, j, k, l);
        }
    }
}
