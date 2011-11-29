// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityGhast, EntityPigZombie, 
//            EntityMagmaCube

public class BiomeGenHell extends BiomeGenBase
{

    public BiomeGenHell(int i)
    {
        super(i);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityGhast.class, 50, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityPigZombie.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityMagmaCube.class, 1, 4, 4));
    }
}
