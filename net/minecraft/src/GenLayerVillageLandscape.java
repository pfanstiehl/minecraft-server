// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            GenLayer, BiomeGenBase, IntCache

public class GenLayerVillageLandscape extends GenLayer
{

    private BiomeGenBase allowedBiomes[];

    public GenLayerVillageLandscape(long l, GenLayer genlayer)
    {
        super(l);
        allowedBiomes = (new BiomeGenBase[] {
            BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga
        });
        parent = genlayer;
    }

    public int[] func_35018_a(int i, int j, int k, int l)
    {
        int ai[] = parent.func_35018_a(i, j, k, l);
        int ai1[] = IntCache.getIntCache(k * l);
        for(int i1 = 0; i1 < l; i1++)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                func_35017_a(j1 + i, i1 + j);
                int k1 = ai[j1 + i1 * k];
                if(k1 == 0)
                {
                    ai1[j1 + i1 * k] = 0;
                    continue;
                }
                if(k1 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    ai1[j1 + i1 * k] = k1;
                    continue;
                }
                if(k1 == 1)
                {
                    ai1[j1 + i1 * k] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                } else
                {
                    ai1[j1 + i1 * k] = BiomeGenBase.icePlains.biomeID;
                }
            }

        }

        return ai1;
    }
}
