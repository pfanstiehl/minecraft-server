// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentStronghold, StructureBoundingBox, StructureComponent, Block, 
//            World

public class ComponentStrongholdCorridor extends ComponentStronghold
{

    private final int field_35343_a;

    public ComponentStrongholdCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
        field_35343_a = j != 2 && j != 0 ? structureboundingbox.bbWidth() : structureboundingbox.bbDepth();
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
    }

    public static StructureBoundingBox func_35342_a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -1, -1, 0, 5, 5, 4, l);
        StructureComponent structurecomponent = StructureComponent.canFitInside(list, structureboundingbox);
        if(structurecomponent == null)
        {
            return null;
        }
        if(structurecomponent.getStructureBoundingBox().y1 == structureboundingbox.y1)
        {
            for(int i1 = 3; i1 >= 1; i1--)
            {
                StructureBoundingBox structureboundingbox1 = StructureBoundingBox.func_35663_a(i, j, k, -1, -1, 0, 5, 5, i1 - 1, l);
                if(!structurecomponent.getStructureBoundingBox().canFitInside(structureboundingbox1))
                {
                    return StructureBoundingBox.func_35663_a(i, j, k, -1, -1, 0, 5, 5, i1, l);
                }
            }

        }
        return null;
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        for(int i = 0; i < field_35343_a; i++)
        {
            func_35309_a(world, Block.stoneBrick.blockID, 0, 0, 0, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 0, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 2, 0, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 3, 0, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 4, 0, i, structureboundingbox);
            for(int j = 1; j <= 3; j++)
            {
                func_35309_a(world, Block.stoneBrick.blockID, 0, 0, j, i, structureboundingbox);
                func_35309_a(world, 0, 0, 1, j, i, structureboundingbox);
                func_35309_a(world, 0, 0, 2, j, i, structureboundingbox);
                func_35309_a(world, 0, 0, 3, j, i, structureboundingbox);
                func_35309_a(world, Block.stoneBrick.blockID, 0, 4, j, i, structureboundingbox);
            }

            func_35309_a(world, Block.stoneBrick.blockID, 0, 0, 4, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 4, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 2, 4, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 3, 4, i, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 4, 4, i, structureboundingbox);
        }

        return true;
    }
}
