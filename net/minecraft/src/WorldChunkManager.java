// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            BiomeCache, BiomeGenBase, World, GenLayer, 
//            ChunkCoordIntPair, IntCache, ChunkPosition

public class WorldChunkManager
{

    private GenLayer field_34907_a;
    private GenLayer field_34906_b;
    private GenLayer temperatureLayer;
    private GenLayer rainfallLayer;
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;
    public float field_40580_a[];

    protected WorldChunkManager()
    {
        biomeCache = new BiomeCache(this);
        biomesToSpawnIn = new ArrayList();
        biomesToSpawnIn.add(BiomeGenBase.forest);
        biomesToSpawnIn.add(BiomeGenBase.swampland);
        biomesToSpawnIn.add(BiomeGenBase.taiga);
    }

    public WorldChunkManager(World world)
    {
        this();
        GenLayer agenlayer[] = GenLayer.func_35019_a(world.getRandomSeed());
        field_34907_a = agenlayer[0];
        field_34906_b = agenlayer[1];
        temperatureLayer = agenlayer[2];
        rainfallLayer = agenlayer[3];
    }

    public List getBiomesToSpawnIn()
    {
        return biomesToSpawnIn;
    }

    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkcoordintpair)
    {
        return getBiomeGenAt(chunkcoordintpair.chunkXPos << 4, chunkcoordintpair.chunkZPos << 4);
    }

    public BiomeGenBase getBiomeGenAt(int i, int j)
    {
        return biomeCache.func_35683_a(i, j);
    }

    public float[] getRainfall(float af[], int i, int j, int k, int l)
    {
        IntCache.func_35550_a();
        if(af == null || af.length < k * l)
        {
            af = new float[k * l];
        }
        int ai[] = rainfallLayer.func_35018_a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            float f = (float)ai[i1] / 65536F;
            if(f > 1.0F)
            {
                f = 1.0F;
            }
            af[i1] = f;
        }

        return af;
    }

    public float func_40579_a(int i, int j, int k)
    {
        return func_40577_a(biomeCache.func_40625_c(i, k), j);
    }

    public float func_40577_a(float f, int i)
    {
        return f;
    }

    public float[] func_40578_a(int i, int j, int k, int l)
    {
        field_40580_a = getTemperatures(field_40580_a, i, j, k, l);
        return field_40580_a;
    }

    public float[] getTemperatures(float af[], int i, int j, int k, int l)
    {
        IntCache.func_35550_a();
        if(af == null || af.length < k * l)
        {
            af = new float[k * l];
        }
        int ai[] = temperatureLayer.func_35018_a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            float f = (float)ai[i1] / 65536F;
            if(f > 1.0F)
            {
                f = 1.0F;
            }
            af[i1] = f;
        }

        return af;
    }

    public BiomeGenBase[] func_35142_b(BiomeGenBase abiomegenbase[], int i, int j, int k, int l)
    {
        IntCache.func_35550_a();
        if(abiomegenbase == null || abiomegenbase.length < k * l)
        {
            abiomegenbase = new BiomeGenBase[k * l];
        }
        int ai[] = field_34907_a.func_35018_a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            abiomegenbase[i1] = BiomeGenBase.biomeList[ai[i1]];
        }

        return abiomegenbase;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase abiomegenbase[], int i, int j, int k, int l)
    {
        return func_35140_a(abiomegenbase, i, j, k, l, true);
    }

    public BiomeGenBase[] func_35140_a(BiomeGenBase abiomegenbase[], int i, int j, int k, int l, boolean flag)
    {
        IntCache.func_35550_a();
        if(abiomegenbase == null || abiomegenbase.length < k * l)
        {
            abiomegenbase = new BiomeGenBase[k * l];
        }
        if(flag && k == 16 && l == 16 && (i & 0xf) == 0 && (j & 0xf) == 0)
        {
            BiomeGenBase abiomegenbase1[] = biomeCache.getCachedBiomes(i, j);
            System.arraycopy(abiomegenbase1, 0, abiomegenbase, 0, k * l);
            return abiomegenbase;
        }
        int ai[] = field_34906_b.func_35018_a(i, j, k, l);
        for(int i1 = 0; i1 < k * l; i1++)
        {
            abiomegenbase[i1] = BiomeGenBase.biomeList[ai[i1]];
        }

        return abiomegenbase;
    }

    public boolean areBiomesViable(int i, int j, int k, List list)
    {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = (j1 - l) + 1;
        int i2 = (k1 - i1) + 1;
        int ai[] = field_34907_a.func_35018_a(l, i1, l1, i2);
        for(int j2 = 0; j2 < l1 * i2; j2++)
        {
            BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[j2]];
            if(!list.contains(biomegenbase))
            {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition func_35139_a(int i, int j, int k, List list, Random random)
    {
        int l = i - k >> 2;
        int i1 = j - k >> 2;
        int j1 = i + k >> 2;
        int k1 = j + k >> 2;
        int l1 = (j1 - l) + 1;
        int i2 = (k1 - i1) + 1;
        int ai[] = field_34907_a.func_35018_a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;
        for(int k2 = 0; k2 < ai.length; k2++)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[k2]];
            if(list.contains(biomegenbase) && (chunkposition == null || random.nextInt(j2 + 1) == 0))
            {
                chunkposition = new ChunkPosition(l2, 0, i3);
                j2++;
            }
        }

        return chunkposition;
    }

    public void func_35138_b()
    {
        biomeCache.cleanupCache();
    }
}
