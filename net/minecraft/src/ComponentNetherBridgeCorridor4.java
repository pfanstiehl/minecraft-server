// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, ComponentNetherBridgeStartPiece, StructureBoundingBox, StructureComponent, 
//            Block, World

public class ComponentNetherBridgeCorridor4 extends ComponentNetherBridgePiece
{

    public ComponentNetherBridgeCorridor4(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        byte byte0 = 1;
        if(coordBaseMode == 1 || coordBaseMode == 2)
        {
            byte0 = 5;
        }
        func_40285_b((ComponentNetherBridgeStartPiece)structurecomponent, list, random, 0, byte0, random.nextInt(8) > 0);
        func_40288_c((ComponentNetherBridgeStartPiece)structurecomponent, list, random, 0, byte0, random.nextInt(8) > 0);
    }

    public static ComponentNetherBridgeCorridor4 func_40298_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -3, 0, 0, 9, 7, 9, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentNetherBridgeCorridor4(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        fillWithBlocks(world, structureboundingbox, 0, 0, 0, 8, 1, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 0, 8, 5, 8, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 0, 6, 0, 8, 6, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 0, 2, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 6, 2, 0, 8, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 3, 0, 1, 4, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 7, 3, 0, 7, 4, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 4, 8, 2, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 1, 4, 2, 2, 4, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 6, 1, 4, 7, 2, 4, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 0, 3, 8, 8, 3, 8, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 3, 6, 0, 3, 7, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 8, 3, 6, 8, 3, 7, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 3, 4, 0, 5, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 8, 3, 4, 8, 5, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 3, 5, 2, 5, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 6, 3, 5, 7, 5, 5, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 4, 5, 1, 5, 5, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 7, 4, 5, 7, 5, 5, Block.netherFence.blockID, Block.netherFence.blockID, false);
        for(int i = 0; i <= 5; i++)
        {
            for(int j = 0; j <= 8; j++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, j, -1, i, structureboundingbox);
            }

        }

        return true;
    }
}
