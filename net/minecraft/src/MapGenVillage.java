// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            MapGenStructure, World, WorldChunkManager, StructureVillageStart, 
//            BiomeGenBase, StructureStart

public class MapGenVillage extends MapGenStructure
{

    public static List field_35538_a;

    public MapGenVillage()
    {
    }

    protected boolean func_35531_a(int i, int j)
    {
        byte byte0 = 32;
        byte byte1 = 8;
        int k = i;
        int l = j;
        if(i < 0)
        {
            i -= byte0 - 1;
        }
        if(j < 0)
        {
            j -= byte0 - 1;
        }
        int i1 = i / byte0;
        int j1 = j / byte0;
        Random random = worldObj.func_35238_t(i1, j1, 0x9e7f70);
        i1 *= byte0;
        j1 *= byte0;
        i1 += random.nextInt(byte0 - byte1);
        j1 += random.nextInt(byte0 - byte1);
        i = k;
        j = l;
        if(i == i1 && j == j1)
        {
            boolean flag = worldObj.getWorldChunkManager().func_35141_a(i * 16 + 8, j * 16 + 8, 0, field_35538_a);
            if(flag)
            {
                return true;
            }
        }
        return false;
    }

    protected StructureStart func_35533_b(int i, int j)
    {
        return new StructureVillageStart(worldObj, rand, i, j);
    }

    static 
    {
        field_35538_a = Arrays.asList(new BiomeGenBase[] {
            BiomeGenBase.plains, BiomeGenBase.desert
        });
    }
}
