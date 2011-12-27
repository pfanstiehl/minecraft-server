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
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.deadBushPerChunk = 2;
        biomeDecorator.reedsPerChunk = 50;
        biomeDecorator.cactiPerChunk = 10;
    }
}
