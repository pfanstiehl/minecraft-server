// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            LongHashMap, BiomeCacheBlock, WorldChunkManager, BiomeGenBase

public class BiomeCache
{

    private final WorldChunkManager worldChunkManager;
    private long field_35685_b;
    private LongHashMap field_35686_c;
    private List field_35684_d;

    public BiomeCache(WorldChunkManager worldchunkmanager)
    {
        field_35685_b = 0L;
        field_35686_c = new LongHashMap();
        field_35684_d = new ArrayList();
        worldChunkManager = worldchunkmanager;
    }

    public BiomeCacheBlock func_35680_c(int i, int j)
    {
        i >>= 4;
        j >>= 4;
        long l = (long)i & 0xffffffffL | ((long)j & 0xffffffffL) << 32;
        BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)field_35686_c.getValueByKey(l);
        if(biomecacheblock == null)
        {
            biomecacheblock = new BiomeCacheBlock(this, i, j);
            field_35686_c.add(l, biomecacheblock);
            field_35684_d.add(biomecacheblock);
        }
        biomecacheblock.field_35701_f = System.currentTimeMillis();
        return biomecacheblock;
    }

    public BiomeGenBase func_35683_a(int i, int j)
    {
        return func_35680_c(i, j).func_35700_a(i, j);
    }

    public float func_40625_c(int i, int j)
    {
        return func_35680_c(i, j).func_40626_b(i, j);
    }

    public void func_35681_a()
    {
        long l = System.currentTimeMillis();
        long l1 = l - field_35685_b;
        if(l1 > 7500L || l1 < 0L)
        {
            field_35685_b = l;
            for(int i = 0; i < field_35684_d.size(); i++)
            {
                BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)field_35684_d.get(i);
                long l2 = l - biomecacheblock.field_35701_f;
                if(l2 > 30000L || l2 < 0L)
                {
                    field_35684_d.remove(i--);
                    long l3 = (long)biomecacheblock.field_35703_d & 0xffffffffL | ((long)biomecacheblock.field_35704_e & 0xffffffffL) << 32;
                    field_35686_c.remove(l3);
                }
            }

        }
    }

    public BiomeGenBase[] func_35682_b(int i, int j)
    {
        return func_35680_c(i, j).field_35706_c;
    }

    static WorldChunkManager getChunkManager(BiomeCache biomecache)
    {
        return biomecache.worldChunkManager;
    }
}
