// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, Block, BiomeDecorator

public class BiomeGenDesert extends BiomeGenBase
{

    public BiomeGenDesert(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        decorator.treesPerChunk = -999;
        decorator.deadBushPerChunk = 2;
        decorator.reedsPerChunk = 50;
        decorator.field_35289_x = 10;
    }
}
