// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, ComponentNetherBridgeStartPiece, StructureBoundingBox, StructureComponent, 
//            Block, World

public class ComponentNetherBridgeStraight extends ComponentNetherBridgePiece
{

    public ComponentNetherBridgeStraight(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        func_40287_a((ComponentNetherBridgeStartPiece)structurecomponent, list, random, 1, 3, false);
    }

    public static ComponentNetherBridgeStraight func_40289_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -1, -3, 0, 5, 10, 19, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentNetherBridgeStraight(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        fillWithBlocks(world, structureboundingbox, 0, 3, 0, 4, 4, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 5, 0, 3, 7, 18, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 0, 5, 0, 0, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 5, 0, 4, 5, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 0, 4, 2, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 13, 4, 2, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 0, 0, 4, 1, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 0, 15, 4, 1, 18, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        for(int i = 0; i <= 4; i++)
        {
            for(int j = 0; j <= 2; j++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, i, -1, j, structureboundingbox);
                func_35303_b(world, Block.netherBrick.blockID, 0, i, -1, 18 - j, structureboundingbox);
            }

        }

        fillWithBlocks(world, structureboundingbox, 0, 1, 1, 0, 4, 1, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 3, 4, 0, 4, 4, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 3, 14, 0, 4, 14, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 1, 17, 0, 4, 17, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 1, 1, 4, 4, 1, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 3, 4, 4, 4, 4, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 3, 14, 4, 4, 14, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 1, 17, 4, 4, 17, Block.netherFence.blockID, Block.netherFence.blockID, false);
        return true;
    }
}
