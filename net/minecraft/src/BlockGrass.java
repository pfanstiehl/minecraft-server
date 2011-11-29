// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World

public class BlockGrass extends Block
{

    protected BlockGrass(int i)
    {
        super(i, Material.grass);
        blockIndexInTexture = 3;
        setTickOnLoad(true);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(i == 1)
        {
            return 0;
        }
        return i != 0 ? 3 : 2;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.singleplayerWorld)
        {
            return;
        }
        if(world.getBlockLightValue(i, j + 1, k) < 4 && Block.lightOpacity[world.getBlockId(i, j + 1, k)] > 2)
        {
            world.setBlockWithNotify(i, j, k, Block.dirt.blockID);
        } else
        if(world.getBlockLightValue(i, j + 1, k) >= 9)
        {
            for(int l = 0; l < 4; l++)
            {
                int i1 = (i + random.nextInt(3)) - 1;
                int j1 = (j + random.nextInt(5)) - 3;
                int k1 = (k + random.nextInt(3)) - 1;
                int l1 = world.getBlockId(i1, j1 + 1, k1);
                if(world.getBlockId(i1, j1, k1) == Block.dirt.blockID && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && Block.lightOpacity[l1] <= 2)
                {
                    world.setBlockWithNotify(i1, j1, k1, Block.grass.blockID);
                }
            }

        }
    }

    public int idDropped(int i, Random random, int j)
    {
        return Block.dirt.idDropped(0, random, j);
    }
}
