// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, BiomeCache, WorldChunkManager

public class BiomeCacheBlock
{

    public float temperatureValues[];
    public float rainfallValues[];
    public BiomeGenBase biomes[];
    public int xPosition;
    public int zPosition;
    public long lastAccessTime;
    final BiomeCache field_35702_g; /* synthetic field */

    public BiomeCacheBlock(BiomeCache biomecache, int i, int j)
    {
        field_35702_g = biomecache;
//        super();
        temperatureValues = new float[256];
        rainfallValues = new float[256];
        biomes = new BiomeGenBase[256];
        xPosition = i;
        zPosition = j;
        BiomeCache.getChunkManager(biomecache).getTemperatures(temperatureValues, i << 4, j << 4, 16, 16);
        BiomeCache.getChunkManager(biomecache).getRainfall(rainfallValues, i << 4, j << 4, 16, 16);
        BiomeCache.getChunkManager(biomecache).func_35140_a(biomes, i << 4, j << 4, 16, 16, false);
    }

    public BiomeGenBase func_35700_a(int i, int j)
    {
        return biomes[i & 0xf | (j & 0xf) << 4];
    }

    public float func_40626_b(int i, int j)
    {
        return temperatureValues[i & 0xf | (j & 0xf) << 4];
    }
}
