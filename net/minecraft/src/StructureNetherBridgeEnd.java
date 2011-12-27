// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, StructureBoundingBox, StructureComponent, Block, 
//            World

public class StructureNetherBridgeEnd extends ComponentNetherBridgePiece
{

    private int field_40302_a;

    public StructureNetherBridgeEnd(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
        field_40302_a = random.nextInt();
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
    }

    public static StructureNetherBridgeEnd func_40301_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, -1, -3, 0, 5, 10, 8, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new StructureNetherBridgeEnd(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        Random random1 = new Random(field_40302_a);
        for(int i = 0; i <= 4; i++)
        {
            for(int i1 = 3; i1 <= 4; i1++)
            {
                int l1 = random1.nextInt(8);
                fillWithBlocks(world, structureboundingbox, i, i1, 0, i, i1, l1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            }

        }

        int j = random1.nextInt(8);
        fillWithBlocks(world, structureboundingbox, 0, 5, 0, 0, 5, j, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        j = random1.nextInt(8);
        fillWithBlocks(world, structureboundingbox, 4, 5, 0, 4, 5, j, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        for(int k = 0; k <= 4; k++)
        {
            int j1 = random1.nextInt(5);
            fillWithBlocks(world, structureboundingbox, k, 2, 0, k, 2, j1, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        }

        for(int l = 0; l <= 4; l++)
        {
            for(int k1 = 0; k1 <= 1; k1++)
            {
                int i2 = random1.nextInt(3);
                fillWithBlocks(world, structureboundingbox, l, k1, 0, l, k1, i2, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
            }

        }

        return true;
    }
}
