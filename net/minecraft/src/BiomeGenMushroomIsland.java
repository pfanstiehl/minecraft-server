// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, BiomeDecorator, Block, BlockMycelium, 
//            SpawnListEntry, EntityMooshroom

public class BiomeGenMushroomIsland extends BiomeGenBase
{

    public BiomeGenMushroomIsland(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = -100;
        biomeDecorator.flowersPerChunk = -100;
        biomeDecorator.grassPerChunk = -100;
        biomeDecorator.mushroomsPerChunk = 1;
        biomeDecorator.bigMushroomsPerChunk = 1;
        topBlock = (byte)Block.mycelium.blockID;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityMooshroom.class, 8, 4, 8));
    }
}
