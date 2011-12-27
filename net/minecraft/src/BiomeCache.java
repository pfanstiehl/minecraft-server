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
    private long lastCleanupTime;
    private LongHashMap cacheMap;
    private List cache;

    public BiomeCache(WorldChunkManager worldchunkmanager)
    {
        lastCleanupTime = 0L;
        cacheMap = new LongHashMap();
        cache = new ArrayList();
        worldChunkManager = worldchunkmanager;
    }

    public BiomeCacheBlock getBiomeCacheBlock(int i, int j)
    {
        i >>= 4;
        j >>= 4;
        long l = (long)i & 0xffffffffL | ((long)j & 0xffffffffL) << 32;
        BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)cacheMap.getValueByKey(l);
        if(biomecacheblock == null)
        {
            biomecacheblock = new BiomeCacheBlock(this, i, j);
            cacheMap.add(l, biomecacheblock);
            cache.add(biomecacheblock);
        }
        biomecacheblock.lastAccessTime = System.currentTimeMillis();
        return biomecacheblock;
    }

    public BiomeGenBase func_35683_a(int i, int j)
    {
        return getBiomeCacheBlock(i, j).func_35700_a(i, j);
    }

    public float func_40625_c(int i, int j)
    {
        return getBiomeCacheBlock(i, j).func_40626_b(i, j);
    }

    public void cleanupCache()
    {
        long l = System.currentTimeMillis();
        long l1 = l - lastCleanupTime;
        if(l1 > 7500L || l1 < 0L)
        {
            lastCleanupTime = l;
            for(int i = 0; i < cache.size(); i++)
            {
                BiomeCacheBlock biomecacheblock = (BiomeCacheBlock)cache.get(i);
                long l2 = l - biomecacheblock.lastAccessTime;
                if(l2 > 30000L || l2 < 0L)
                {
                    cache.remove(i--);
                    long l3 = (long)biomecacheblock.xPosition & 0xffffffffL | ((long)biomecacheblock.zPosition & 0xffffffffL) << 32;
                    cacheMap.remove(l3);
                }
            }

        }
    }

    public BiomeGenBase[] getCachedBiomes(int i, int j)
    {
        return getBiomeCacheBlock(i, j).biomes;
    }

    static WorldChunkManager getChunkManager(BiomeCache biomecache)
    {
        return biomecache.worldChunkManager;
    }
}
