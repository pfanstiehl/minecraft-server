// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            MapGenStructure, StructureMineshaftStart, StructureStart

public class MapGenMineshaft extends MapGenStructure
{

    public MapGenMineshaft()
    {
    }

    protected boolean canSpawnStructureAtCoords(int i, int j)
    {
        return rand.nextInt(100) == 0 && rand.nextInt(80) < Math.max(Math.abs(i), Math.abs(j));
    }

    protected StructureStart getStructureStart(int i, int j)
    {
        return new StructureMineshaftStart(worldObj, rand, i, j);
    }
}
