// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, ComponentNetherBridgeStartPiece, StructureBoundingBox, StructureComponent, 
//            Block, World

public class ComponentNetherBridgeEntrance extends ComponentNetherBridgePiece
{

    public ComponentNetherBridgeEntrance(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        func_40287_a((ComponentNetherBridgeStartPiece)structurecomponent, list, random, 5, 3, true);
    }

    public static ComponentNetherBridgeEntrance func_40307_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -5, -3, 0, 13, 14, 13, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentNetherBridgeEntrance(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        fillWithBlocks(world, structureboundingbox, 0, 3, 0, 12, 4, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 5, 0, 12, 13, 12, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 0, 5, 0, 1, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 11, 5, 0, 12, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 2, 5, 11, 4, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 8, 5, 11, 10, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 5, 9, 11, 7, 12, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 2, 5, 0, 4, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 8, 5, 0, 10, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 5, 9, 0, 7, 12, 1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 2, 11, 2, 10, 12, 10, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 5, 8, 0, 7, 8, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
        for(int i = 1; i <= 11; i += 2)
        {
            fillWithBlocks(world, structureboundingbox, i, 10, 0, i, 11, 0, Block.netherFence.blockID, Block.netherFence.blockID, false);
            fillWithBlocks(world, structureboundingbox, i, 10, 12, i, 11, 12, Block.netherFence.blockID, Block.netherFence.blockID, false);
            fillWithBlocks(world, structureboundingbox, 0, 10, i, 0, 11, i, Block.netherFence.blockID, Block.netherFence.blockID, false);
            fillWithBlocks(world, structureboundingbox, 12, 10, i, 12, 11, i, Block.netherFence.blockID, Block.netherFence.blockID, false);
            func_35309_a(world, Block.netherBrick.blockID, 0, i, 13, 0, structureboundingbox);
            func_35309_a(world, Block.netherBrick.blockID, 0, i, 13, 12, structureboundingbox);
            func_35309_a(world, Block.netherBrick.blockID, 0, 0, 13, i, structureboundingbox);
            func_35309_a(world, Block.netherBrick.blockID, 0, 12, 13, i, structureboundingbox);
            func_35309_a(world, Block.netherFence.blockID, 0, i + 1, 13, 0, structureboundingbox);
            func_35309_a(world, Block.netherFence.blockID, 0, i + 1, 13, 12, structureboundingbox);
            func_35309_a(world, Block.netherFence.blockID, 0, 0, 13, i + 1, structureboundingbox);
            func_35309_a(world, Block.netherFence.blockID, 0, 12, 13, i + 1, structureboundingbox);
        }

        func_35309_a(world, Block.netherFence.blockID, 0, 0, 13, 0, structureboundingbox);
        func_35309_a(world, Block.netherFence.blockID, 0, 0, 13, 12, structureboundingbox);
        func_35309_a(world, Block.netherFence.blockID, 0, 0, 13, 0, structureboundingbox);
        func_35309_a(world, Block.netherFence.blockID, 0, 12, 13, 0, structureboundingbox);
        for(int j = 3; j <= 9; j += 2)
        {
            fillWithBlocks(world, structureboundingbox, 1, 7, j, 1, 8, j, Block.netherFence.blockID, Block.netherFence.blockID, false);
            fillWithBlocks(world, structureboundingbox, 11, 7, j, 11, 8, j, Block.netherFence.blockID, Block.netherFence.blockID, false);
        }

        fillWithBlocks(world, structureboundingbox, 4, 2, 0, 8, 2, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 2, 4, 12, 2, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 0, 0, 8, 1, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 0, 9, 8, 1, 12, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 0, 4, 3, 1, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 9, 0, 4, 12, 1, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        for(int k = 4; k <= 8; k++)
        {
            for(int j1 = 0; j1 <= 2; j1++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, k, -1, j1, structureboundingbox);
                func_35303_b(world, Block.netherBrick.blockID, 0, k, -1, 12 - j1, structureboundingbox);
            }

        }

        for(int l = 0; l <= 2; l++)
        {
            for(int k1 = 4; k1 <= 8; k1++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, l, -1, k1, structureboundingbox);
                func_35303_b(world, Block.netherBrick.blockID, 0, 12 - l, -1, k1, structureboundingbox);
            }

        }

        fillWithBlocks(world, structureboundingbox, 5, 5, 5, 7, 5, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 6, 1, 6, 6, 4, 6, 0, 0, false);
        func_35309_a(world, Block.netherBrick.blockID, 0, 6, 0, 6, structureboundingbox);
        func_35309_a(world, Block.lavaMoving.blockID, 0, 6, 5, 6, structureboundingbox);
        int i1 = func_35306_a(6, 6);
        int l1 = func_35300_a(5);
        int i2 = func_35296_b(6, 6);
        if(structureboundingbox.isInBbVolume(i1, l1, i2))
        {
            world.scheduledUpdatesAreImmediate = true;
            Block.blocksList[Block.lavaMoving.blockID].updateTick(world, i1, l1, i2, random);
            world.scheduledUpdatesAreImmediate = false;
        }
        return true;
    }
}
