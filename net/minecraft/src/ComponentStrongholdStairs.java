// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentStronghold, EnumDoor, StructureBoundingBox, ComponentStrongholdCrossing, 
//            StructureStrongholdPieces, ComponentStrongholdStairs2, StructureComponent, Block, 
//            World

public class ComponentStrongholdStairs extends ComponentStronghold
{

    private final boolean field_35327_a;
    private final EnumDoor field_35326_b;

    public ComponentStrongholdStairs(int i, Random random, int j, int k)
    {
        super(i);
        field_35327_a = true;
        coordBaseMode = random.nextInt(4);
        field_35326_b = EnumDoor.OPENING;
        switch(coordBaseMode)
        {
        case 0: // '\0'
        case 2: // '\002'
            boundingBox = new StructureBoundingBox(j, 64, k, (j + 5) - 1, 74, (k + 5) - 1);
            break;

        default:
            boundingBox = new StructureBoundingBox(j, 64, k, (j + 5) - 1, 74, (k + 5) - 1);
            break;
        }
    }

    public ComponentStrongholdStairs(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        field_35327_a = false;
        coordBaseMode = j;
        field_35326_b = func_35322_a(random);
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        if(field_35327_a)
        {
            StructureStrongholdPieces.func_40541_a(net.minecraft.src.ComponentStrongholdCrossing.class);
        }
        func_35324_a((ComponentStrongholdStairs2)structurecomponent, list, random, 1, 1);
    }

    public static ComponentStrongholdStairs getStrongholdStairsComponent(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -1, -7, 0, 5, 11, 5, l);
        if(!func_35319_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentStrongholdStairs(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        } else
        {
            if(!field_35327_a);
            func_35307_a(world, structureboundingbox, 0, 0, 0, 4, 10, 4, true, random, StructureStrongholdPieces.getStrongholdStones());
            placeDoor(world, random, structureboundingbox, field_35326_b, 1, 7, 0);
            placeDoor(world, random, structureboundingbox, EnumDoor.OPENING, 1, 1, 4);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 2, 6, 1, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 5, 1, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 1, 6, 1, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 5, 2, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 4, 3, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 1, 5, 3, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 2, 4, 3, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 3, 3, 3, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 3, 4, 3, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 3, 3, 2, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 3, 2, 1, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 3, 3, 1, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 2, 2, 1, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 1, 1, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 1, 2, 1, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, 1, 1, 2, structureboundingbox);
            func_35309_a(world, Block.stairSingle.blockID, 0, 1, 1, 3, structureboundingbox);
            return true;
        }
    }
}
