// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentStronghold, ComponentStrongholdStairs2, StructureBoundingBox, StructureComponent, 
//            StructureStrongholdPieces, Block, EnumDoor, World

public class ComponentStrongholdPrison extends ComponentStronghold
{

    protected final EnumDoor field_35333_a;

    public ComponentStrongholdPrison(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        field_35333_a = func_35322_a(random);
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        func_35324_a((ComponentStrongholdStairs2)structurecomponent, list, random, 1, 1);
    }

    public static ComponentStrongholdPrison func_35332_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -1, 0, 9, 5, 11, l);
        if(!canStrongholdGoDeeper(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentStrongholdPrison(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        } else
        {
            fillWithRandomizedBlocks(world, structureboundingbox, 0, 0, 0, 8, 4, 10, true, random, StructureStrongholdPieces.getStrongholdStones());
            placeDoor(world, random, structureboundingbox, field_35333_a, 1, 1, 0);
            fillWithBlocks(world, structureboundingbox, 1, 1, 10, 3, 3, 10, 0, 0, false);
            fillWithRandomizedBlocks(world, structureboundingbox, 4, 1, 1, 4, 3, 1, false, random, StructureStrongholdPieces.getStrongholdStones());
            fillWithRandomizedBlocks(world, structureboundingbox, 4, 1, 3, 4, 3, 3, false, random, StructureStrongholdPieces.getStrongholdStones());
            fillWithRandomizedBlocks(world, structureboundingbox, 4, 1, 7, 4, 3, 7, false, random, StructureStrongholdPieces.getStrongholdStones());
            fillWithRandomizedBlocks(world, structureboundingbox, 4, 1, 9, 4, 3, 9, false, random, StructureStrongholdPieces.getStrongholdStones());
            fillWithBlocks(world, structureboundingbox, 4, 1, 4, 4, 3, 6, Block.fenceIron.blockID, Block.fenceIron.blockID, false);
            fillWithBlocks(world, structureboundingbox, 5, 1, 5, 7, 3, 5, Block.fenceIron.blockID, Block.fenceIron.blockID, false);
            placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, 4, 3, 2, structureboundingbox);
            placeBlockAtCurrentPosition(world, Block.fenceIron.blockID, 0, 4, 3, 8, structureboundingbox);
            placeBlockAtCurrentPosition(world, Block.doorSteel.blockID, func_35301_c(Block.doorSteel.blockID, 3), 4, 1, 2, structureboundingbox);
            placeBlockAtCurrentPosition(world, Block.doorSteel.blockID, func_35301_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 2, structureboundingbox);
            placeBlockAtCurrentPosition(world, Block.doorSteel.blockID, func_35301_c(Block.doorSteel.blockID, 3), 4, 1, 8, structureboundingbox);
            placeBlockAtCurrentPosition(world, Block.doorSteel.blockID, func_35301_c(Block.doorSteel.blockID, 3) + 8, 4, 2, 8, structureboundingbox);
            return true;
        }
    }
}
