// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentVillage, StructureBoundingBox, StructureComponent, Block, 
//            World

public class ComponentVillageHouse4_Garden extends ComponentVillage
{

    private int field_35403_a;
    private final boolean field_35402_b;

    public ComponentVillageHouse4_Garden(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        field_35403_a = -1;
        coordBaseMode = j;
        boundingBox = structureboundingbox;
        field_35402_b = random.nextBoolean();
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
    }

    public static ComponentVillageHouse4_Garden func_35401_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, 0, 0, 0, 5, 6, 5, l);
        if(StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentVillageHouse4_Garden(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(field_35403_a < 0)
        {
            field_35403_a = getFoundationLevel(world, structureboundingbox);
            if(field_35403_a < 0)
            {
                return true;
            }
            boundingBox.offset(0, ((field_35403_a - boundingBox.y2) + 6) - 1, 0);
        }
        fillWithBlocks(world, structureboundingbox, 0, 0, 0, 4, 0, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Block.wood.blockID, Block.wood.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 4, 1, 3, 4, 3, Block.planks.blockID, Block.planks.blockID, false);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 1, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 2, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 3, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 1, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 2, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 3, 0, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 1, 4, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 2, 4, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 0, 3, 4, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 1, 4, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 2, 4, structureboundingbox);
        func_35309_a(world, Block.cobblestone.blockID, 0, 4, 3, 4, structureboundingbox);
        fillWithBlocks(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Block.planks.blockID, Block.planks.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 1, 4, 3, 3, 4, Block.planks.blockID, Block.planks.blockID, false);
        func_35309_a(world, Block.thinGlass.blockID, 0, 0, 2, 2, structureboundingbox);
        func_35309_a(world, Block.thinGlass.blockID, 0, 2, 2, 4, structureboundingbox);
        func_35309_a(world, Block.thinGlass.blockID, 0, 4, 2, 2, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 1, 1, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 1, 2, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 1, 3, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 2, 3, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 3, 3, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 3, 2, 0, structureboundingbox);
        func_35309_a(world, Block.planks.blockID, 0, 3, 1, 0, structureboundingbox);
        if(func_35297_a(world, 2, 0, -1, structureboundingbox) == 0 && func_35297_a(world, 2, -1, -1, structureboundingbox) != 0)
        {
            func_35309_a(world, Block.stairCompactCobblestone.blockID, func_35301_c(Block.stairCompactCobblestone.blockID, 3), 2, 0, -1, structureboundingbox);
        }
        fillWithBlocks(world, structureboundingbox, 1, 1, 1, 3, 3, 3, 0, 0, false);
        if(field_35402_b)
        {
            func_35309_a(world, Block.fence.blockID, 0, 0, 5, 0, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 1, 5, 0, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 2, 5, 0, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 3, 5, 0, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 4, 5, 0, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 0, 5, 4, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 1, 5, 4, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 2, 5, 4, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 3, 5, 4, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 4, 5, 4, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 4, 5, 1, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 4, 5, 2, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 4, 5, 3, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 0, 5, 1, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 0, 5, 2, structureboundingbox);
            func_35309_a(world, Block.fence.blockID, 0, 0, 5, 3, structureboundingbox);
        }
        if(field_35402_b)
        {
            int i = func_35301_c(Block.ladder.blockID, 3);
            func_35309_a(world, Block.ladder.blockID, i, 3, 1, 3, structureboundingbox);
            func_35309_a(world, Block.ladder.blockID, i, 3, 2, 3, structureboundingbox);
            func_35309_a(world, Block.ladder.blockID, i, 3, 3, 3, structureboundingbox);
            func_35309_a(world, Block.ladder.blockID, i, 3, 4, 3, structureboundingbox);
        }
        func_35309_a(world, Block.torchWood.blockID, 0, 2, 3, 1, structureboundingbox);
        for(int j = 0; j < 5; j++)
        {
            for(int k = 0; k < 5; k++)
            {
                func_35314_b(world, k, 6, j, structureboundingbox);
                func_35303_b(world, Block.cobblestone.blockID, 0, k, -1, j, structureboundingbox);
            }

        }

        func_40309_a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
