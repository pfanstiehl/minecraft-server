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
        decorator.treesPerChunk = -100;
        decorator.flowersPerChunk = -100;
        decorator.grassPerChunk = -100;
        decorator.mushroomsPerChunk = 1;
        decorator.field_40318_J = 1;
        topBlock = (byte)Block.mycelium.blockID;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityMooshroom.class, 8, 4, 8));
    }
}
