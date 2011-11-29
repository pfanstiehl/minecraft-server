// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, Item, EnumSkyBlock, 
//            World

public class BlockSnowBlock extends Block
{

    protected BlockSnowBlock(int i, int j)
    {
        super(i, j, Material.craftedSnow);
        setTickOnLoad(true);
    }

    public int idDropped(int i, Random random, int j)
    {
        return Item.snowball.shiftedIndex;
    }

    public int quantityDropped(Random random)
    {
        return 4;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.getSavedLightValue(EnumSkyBlock.Block, i, j, k) > 11)
        {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
            world.setBlockWithNotify(i, j, k, 0);
        }
    }
}
