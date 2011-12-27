// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentStronghold, ComponentStrongholdStairs2, StructureBoundingBox, StructureComponent, 
//            StructureStrongholdPieces, EnumDoor, World

public class ComponentStrongholdLeftTurn extends ComponentStronghold
{

    protected final EnumDoor field_35331_a;

    public ComponentStrongholdLeftTurn(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        field_35331_a = func_35322_a(random);
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        if(coordBaseMode == 2 || coordBaseMode == 3)
        {
            func_35321_b((ComponentStrongholdStairs2)structurecomponent, list, random, 1, 1);
        } else
        {
            func_35320_c((ComponentStrongholdStairs2)structurecomponent, list, random, 1, 1);
        }
    }

    public static ComponentStrongholdLeftTurn func_35330_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -1, 0, 5, 5, 5, l);
        if(!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentStrongholdLeftTurn(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        fillWithRandomizedBlocks(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, StructureStrongholdPieces.getStrongholdStones());
        placeDoor(world, random, structureboundingbox, field_35331_a, 1, 1, 0);
        if(coordBaseMode == 2 || coordBaseMode == 3)
        {
            fillWithBlocks(world, structureboundingbox, 0, 1, 1, 0, 3, 3, 0, 0, false);
        } else
        {
            fillWithBlocks(world, structureboundingbox, 4, 1, 1, 4, 3, 3, 0, 0, false);
        }
        return true;
    }
}
