// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, BiomeDecorator, WorldGenerator

public class BiomeGenSwamp extends BiomeGenBase
{

    protected BiomeGenSwamp(int i)
    {
        super(i);
        decorator.treesPerChunk = 2;
        decorator.flowersPerChunk = -999;
        decorator.deadBushPerChunk = 1;
        decorator.mushroomsPerChunk = 8;
        decorator.reedsPerChunk = 10;
        decorator.clayPerChunk = 1;
        decorator.field_40321_y = 4;
        field_40461_A = 0xe0ff70;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return swampTreeGenerator;
    }
}
