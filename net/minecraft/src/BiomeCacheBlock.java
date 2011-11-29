// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, BiomeCache, WorldChunkManager

public class BiomeCacheBlock
{

    public float field_35707_a[];
    public float field_35705_b[];
    public BiomeGenBase field_35706_c[];
    public int field_35703_d;
    public int field_35704_e;
    public long field_35701_f;
    final BiomeCache field_35702_g; /* synthetic field */

    public BiomeCacheBlock(BiomeCache biomecache, int i, int j)
    {
        field_35702_g = biomecache;
//        super();
        field_35707_a = new float[256];
        field_35705_b = new float[256];
        field_35706_c = new BiomeGenBase[256];
        field_35703_d = i;
        field_35704_e = j;
        BiomeCache.getChunkManager(biomecache).getTemperatures(field_35707_a, i << 4, j << 4, 16, 16);
        BiomeCache.getChunkManager(biomecache).func_4065_a(field_35705_b, i << 4, j << 4, 16, 16);
        BiomeCache.getChunkManager(biomecache).func_35140_a(field_35706_c, i << 4, j << 4, 16, 16, false);
    }

    public BiomeGenBase func_35700_a(int i, int j)
    {
        return field_35706_c[i & 0xf | (j & 0xf) << 4];
    }

    public float func_40626_b(int i, int j)
    {
        return field_35707_a[i & 0xf | (j & 0xf) << 4];
    }
}
