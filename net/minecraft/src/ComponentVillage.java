// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureVillagePieces, World, 
//            EntityVillager, ComponentVillageStartPiece

abstract class ComponentVillage extends StructureComponent
{

    private int field_39005_a;

    protected ComponentVillage(int i)
    {
        super(i);
    }

    protected StructureComponent func_35368_a(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c());

        case 0: // '\0'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c());

        case 1: // '\001'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c());

        case 3: // '\003'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c());
        }
        return null;
    }

    protected StructureComponent func_35369_b(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c());

        case 0: // '\0'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c());

        case 1: // '\001'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c());

        case 3: // '\003'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c());
        }
        return null;
    }

    protected int getFoundationLevel(World world, StructureBoundingBox structureboundingbox)
    {
        int i = 0;
        int j = 0;
        for(int k = boundingBox.z1; k <= boundingBox.z2; k++)
        {
            for(int l = boundingBox.x1; l <= boundingBox.x2; l++)
            {
                if(structureboundingbox.isInBbVolume(l, 64, k))
                {
                    i += Math.max(world.findTopSolidBlock(l, k), world.worldOceanHeight);
                    j++;
                }
            }

        }

        if(j == 0)
        {
            return -1;
        } else
        {
            return i / j;
        }
    }

    protected static boolean func_35366_a(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.y1 > 10;
    }

    protected void func_40309_a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l)
    {
        if(field_39005_a >= l)
        {
            return;
        }
        int i1 = field_39005_a;
        do
        {
            if(i1 >= l)
            {
                break;
            }
            int j1 = func_35306_a(i + i1, k);
            int k1 = func_35300_a(j);
            int l1 = func_35296_b(i + i1, k);
            if(!structureboundingbox.isInBbVolume(j1, k1, l1))
            {
                break;
            }
            field_39005_a++;
            EntityVillager entityvillager = new EntityVillager(world, func_40310_a(i1));
            entityvillager.setLocationAndAngles((double)j1 + 0.5D, k1, (double)l1 + 0.5D, 0.0F, 0.0F);
            world.entityJoinedWorld(entityvillager);
            i1++;
        } while(true);
    }

    protected int func_40310_a(int i)
    {
        return 0;
    }
}
