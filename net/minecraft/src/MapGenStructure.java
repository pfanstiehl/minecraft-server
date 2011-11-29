// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            MapGenBase, ChunkCoordIntPair, StructureStart, StructureBoundingBox, 
//            StructureComponent, World, ChunkPosition, IChunkProvider

public abstract class MapGenStructure extends MapGenBase
{

    protected HashMap field_35534_e;

    public MapGenStructure()
    {
        field_35534_e = new HashMap();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        super.generate(ichunkprovider, world, i, j, abyte0);
    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
        if(field_35534_e.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j))))
        {
            return;
        }
        rand.nextInt();
        if(func_35531_a(i, j))
        {
            StructureStart structurestart = func_35533_b(i, j);
            field_35534_e.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)), structurestart);
        }
    }

    public boolean func_35532_a(World world, Random random, int i, int j)
    {
        int k = (i << 4) + 8;
        int l = (j << 4) + 8;
        boolean flag = false;
        Iterator iterator = field_35534_e.values().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            StructureStart structurestart = (StructureStart)iterator.next();
            if(structurestart.isSizeableStructure() && structurestart.getBoundingBox().isInBbArea(k, l, k + 15, l + 15))
            {
                structurestart.generateStructure(world, random, new StructureBoundingBox(k, l, k + 15, l + 15));
                flag = true;
            }
        } while(true);
        return flag;
    }

    public boolean func_40204_a(int i, int j, int k)
    {
        Iterator iterator = field_35534_e.values().iterator();
label0:
        do
        {
            if(iterator.hasNext())
            {
                StructureStart structurestart = (StructureStart)iterator.next();
                if(!structurestart.isSizeableStructure() || !structurestart.getBoundingBox().isInBbArea(i, k, i, k))
                {
                    continue;
                }
                Iterator iterator1 = structurestart.func_40208_c().iterator();
                StructureComponent structurecomponent;
                do
                {
                    if(!iterator1.hasNext())
                    {
                        continue label0;
                    }
                    structurecomponent = (StructureComponent)iterator1.next();
                } while(!structurecomponent.getStructureBoundingBox().isInBbVolume(i, j, k));
                break;
            } else
            {
                return false;
            }
        } while(true);
        return true;
    }

    public ChunkPosition func_40202_a(World world, int i, int j, int k)
    {
        worldObj = world;
        rand.setSeed(world.getRandomSeed());
        long l = rand.nextLong();
        long l1 = rand.nextLong();
        long l2 = (long)(i >> 4) * l;
        long l3 = (long)(k >> 4) * l1;
        rand.setSeed(l2 ^ l3 ^ world.getRandomSeed());
        recursiveGenerate(world, i >> 4, k >> 4, 0, 0, null);
        double d = 1.7976931348623157E+308D;
        ChunkPosition chunkposition = null;
        Object obj = field_35534_e.values().iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
            {
                break;
            }
            StructureStart structurestart = (StructureStart)((Iterator) (obj)).next();
            if(structurestart.isSizeableStructure())
            {
                StructureComponent structurecomponent = (StructureComponent)structurestart.func_40208_c().get(0);
                ChunkPosition chunkposition2 = structurecomponent.func_40281_b_();
                int i1 = chunkposition2.x - i;
                int k1 = chunkposition2.y - j;
                int j2 = chunkposition2.z - k;
                double d1 = i1 + i1 * k1 * k1 + j2 * j2;
                if(d1 < d)
                {
                    d = d1;
                    chunkposition = chunkposition2;
                }
            }
        } while(true);
        if(chunkposition != null)
        {
            return chunkposition;
        }
        obj = func_40203_a();
        if(obj != null)
        {
            ChunkPosition chunkposition1 = null;
            Iterator iterator = ((List) (obj)).iterator();
            do
            {
                if(!iterator.hasNext())
                {
                    break;
                }
                ChunkPosition chunkposition3 = (ChunkPosition)iterator.next();
                int j1 = chunkposition3.x - i;
                int i2 = chunkposition3.y - j;
                int k2 = chunkposition3.z - k;
                double d2 = j1 + j1 * i2 * i2 + k2 * k2;
                if(d2 < d)
                {
                    d = d2;
                    chunkposition1 = chunkposition3;
                }
            } while(true);
            return chunkposition1;
        } else
        {
            return null;
        }
    }

    protected List func_40203_a()
    {
        return null;
    }

    protected abstract boolean func_35531_a(int i, int j);

    protected abstract StructureStart func_35533_b(int i, int j);
}
