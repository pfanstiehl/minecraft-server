// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, ComponentNetherBridgeStartPiece, StructureBoundingBox, StructureComponent, 
//            Block, World

public class ComponentNetherBridgeCorridor3 extends ComponentNetherBridgePiece
{

    public ComponentNetherBridgeCorridor3(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        func_40287_a((ComponentNetherBridgeStartPiece)structurecomponent, list, random, 1, 0, true);
    }

    public static ComponentNetherBridgeCorridor3 func_40308_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -1, -7, 0, 5, 14, 10, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentNetherBridgeCorridor3(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        int i = func_35301_c(Block.stairsNetherBrick.blockID, 2);
        for(int j = 0; j <= 9; j++)
        {
            int k = Math.max(1, 7 - j);
            int l = Math.min(Math.max(k + 5, 14 - j), 13);
            int i1 = j;
            fillWithBlocks(world, structureboundingbox, 0, 0, i1, 4, k, i1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            fillWithBlocks(world, structureboundingbox, 1, k + 1, i1, 3, l - 1, i1, 0, 0, false);
            if(j <= 6)
            {
                func_35309_a(world, Block.stairsNetherBrick.blockID, i, 1, k + 1, i1, structureboundingbox);
                func_35309_a(world, Block.stairsNetherBrick.blockID, i, 2, k + 1, i1, structureboundingbox);
                func_35309_a(world, Block.stairsNetherBrick.blockID, i, 3, k + 1, i1, structureboundingbox);
            }
            fillWithBlocks(world, structureboundingbox, 0, l, i1, 4, l, i1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            fillWithBlocks(world, structureboundingbox, 0, k + 1, i1, 0, l - 1, i1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            fillWithBlocks(world, structureboundingbox, 4, k + 1, i1, 4, l - 1, i1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            if((j & 1) == 0)
            {
                fillWithBlocks(world, structureboundingbox, 0, k + 2, i1, 0, k + 3, i1, Block.netherFence.blockID, Block.netherFence.blockID, false);
                fillWithBlocks(world, structureboundingbox, 4, k + 2, i1, 4, k + 3, i1, Block.netherFence.blockID, Block.netherFence.blockID, false);
            }
            for(int j1 = 0; j1 <= 4; j1++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, j1, -1, i1, structureboundingbox);
            }

        }

        return true;
    }
}
