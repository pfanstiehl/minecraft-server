// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentStronghold, ComponentStrongholdStairs2, StructureBoundingBox, StructureComponent, 
//            StructureStrongholdPieces, EnumDoor, Block, StructurePieceTreasure, 
//            Item, World

public class ComponentStrongholdChestCorridor extends ComponentStronghold
{

    private static final StructurePieceTreasure field_40314_a[];
    private final EnumDoor field_40312_b;
    private boolean field_40313_c;

    public ComponentStrongholdChestCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        field_40312_b = func_35322_a(random);
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        func_35324_a((ComponentStrongholdStairs2)structurecomponent, list, random, 1, 1);
    }

    public static ComponentStrongholdChestCorridor func_40311_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -1, -1, 0, 5, 5, 7, l);
        if(!func_35319_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentStrongholdChestCorridor(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        func_35307_a(world, structureboundingbox, 0, 0, 0, 4, 4, 6, true, random, StructureStrongholdPieces.getStrongholdStones());
        placeDoor(world, random, structureboundingbox, field_40312_b, 1, 1, 0);
        placeDoor(world, random, structureboundingbox, EnumDoor.OPENING, 1, 1, 6);
        fillWithBlocks(world, structureboundingbox, 3, 1, 2, 3, 1, 4, Block.stoneBrick.blockID, Block.stoneBrick.blockID, false);
        func_35309_a(world, Block.stairSingle.blockID, 5, 3, 1, 1, structureboundingbox);
        func_35309_a(world, Block.stairSingle.blockID, 5, 3, 1, 5, structureboundingbox);
        func_35309_a(world, Block.stairSingle.blockID, 5, 3, 2, 2, structureboundingbox);
        func_35309_a(world, Block.stairSingle.blockID, 5, 3, 2, 4, structureboundingbox);
        for(int i = 2; i <= 4; i++)
        {
            func_35309_a(world, Block.stairSingle.blockID, 5, 2, 1, i, structureboundingbox);
        }

        if(!field_40313_c)
        {
            int j = func_35300_a(2);
            int k = func_35306_a(3, 3);
            int l = func_35296_b(3, 3);
            if(structureboundingbox.isInBbVolume(k, j, l))
            {
                field_40313_c = true;
                func_35299_a(world, structureboundingbox, random, 3, 2, 3, field_40314_a, 2 + random.nextInt(2));
            }
        }
        return true;
    }

    static 
    {
        field_40314_a = (new StructurePieceTreasure[] {
            new StructurePieceTreasure(Item.enderPearl.shiftedIndex, 0, 1, 1, 10), new StructurePieceTreasure(Item.diamond.shiftedIndex, 0, 1, 3, 3), new StructurePieceTreasure(Item.ingotIron.shiftedIndex, 0, 1, 5, 10), new StructurePieceTreasure(Item.ingotGold.shiftedIndex, 0, 1, 3, 5), new StructurePieceTreasure(Item.redstone.shiftedIndex, 0, 4, 9, 5), new StructurePieceTreasure(Item.bread.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.appleRed.shiftedIndex, 0, 1, 3, 15), new StructurePieceTreasure(Item.pickaxeSteel.shiftedIndex, 0, 1, 1, 5), new StructurePieceTreasure(Item.swordSteel.shiftedIndex, 0, 1, 1, 5), new StructurePieceTreasure(Item.plateSteel.shiftedIndex, 0, 1, 1, 5), 
            new StructurePieceTreasure(Item.helmetSteel.shiftedIndex, 0, 1, 1, 5), new StructurePieceTreasure(Item.legsSteel.shiftedIndex, 0, 1, 1, 5), new StructurePieceTreasure(Item.bootsSteel.shiftedIndex, 0, 1, 1, 5), new StructurePieceTreasure(Item.appleGold.shiftedIndex, 0, 1, 1, 1)
        });
    }
}
