// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Material, Block, 
//            BlockGrass

public class WorldGenSand extends WorldGenerator
{

    private int field_35291_a;
    private int field_35290_b;

    public WorldGenSand(int i, int j)
    {
        field_35291_a = j;
        field_35290_b = i;
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.getBlockMaterial(i, j, k) != Material.water)
        {
            return false;
        }
        int l = random.nextInt(field_35290_b - 2) + 2;
        byte byte0 = 2;
        for(int i1 = i - l; i1 <= i + l; i1++)
        {
            for(int j1 = k - l; j1 <= k + l; j1++)
            {
                int k1 = i1 - i;
                int l1 = j1 - k;
                if(k1 * k1 + l1 * l1 > l * l)
                {
                    continue;
                }
                for(int i2 = j - byte0; i2 <= j + byte0; i2++)
                {
                    int j2 = world.getBlockId(i1, i2, j1);
                    if(j2 == Block.dirt.blockID || j2 == Block.grass.blockID)
                    {
                        world.setBlock(i1, i2, j1, field_35291_a);
                    }
                }

            }

        }

        return true;
    }
}
