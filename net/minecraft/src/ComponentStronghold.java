// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, EnumDoorHelper, EnumDoor, Block, 
//            StructureBoundingBox, StructureStrongholdPieces, World, ComponentStrongholdStairs2

abstract class ComponentStronghold extends StructureComponent
{

    protected ComponentStronghold(int i)
    {
        super(i);
    }

    protected void placeDoor(World world, Random random, StructureBoundingBox structureboundingbox, EnumDoor enumdoor, int i, int j, int k)
    {
        switch(EnumDoorHelper.doorEnum[enumdoor.ordinal()])
        {
        case 1: // '\001'
        default:
            fillWithBlocks(world, structureboundingbox, i, j, k, (i + 3) - 1, (j + 3) - 1, k, 0, 0, false);
            break;

        case 2: // '\002'
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 1, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j, k, structureboundingbox);
            func_35309_a(world, Block.doorWood.blockID, 0, i + 1, j, k, structureboundingbox);
            func_35309_a(world, Block.doorWood.blockID, 8, i + 1, j + 1, k, structureboundingbox);
            break;

        case 3: // '\003'
            func_35309_a(world, 0, 0, i + 1, j, k, structureboundingbox);
            func_35309_a(world, 0, 0, i + 1, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i, j, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i + 1, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i + 2, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i + 2, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.fenceIron.blockID, 0, i + 2, j, k, structureboundingbox);
            break;

        case 4: // '\004'
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 1, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j + 2, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.stoneBrick.blockID, 0, i + 2, j, k, structureboundingbox);
            func_35309_a(world, Block.doorSteel.blockID, 0, i + 1, j, k, structureboundingbox);
            func_35309_a(world, Block.doorSteel.blockID, 8, i + 1, j + 1, k, structureboundingbox);
            func_35309_a(world, Block.button.blockID, func_35301_c(Block.button.blockID, 4), i + 2, j + 1, k + 1, structureboundingbox);
            func_35309_a(world, Block.button.blockID, func_35301_c(Block.button.blockID, 3), i + 2, j + 1, k - 1, structureboundingbox);
            break;
        }
    }

    protected EnumDoor func_35322_a(Random random)
    {
        int i = random.nextInt(5);
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
        default:
            return EnumDoor.OPENING;

        case 2: // '\002'
            return EnumDoor.WOOD_DOOR;

        case 3: // '\003'
            return EnumDoor.GRATES;

        case 4: // '\004'
            return EnumDoor.IRON_DOOR;
        }
    }

    protected StructureComponent func_35324_a(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + i, boundingBox.y1 + j, boundingBox.z1 - 1, coordBaseMode, func_35305_c());

        case 0: // '\0'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + i, boundingBox.y1 + j, boundingBox.z2 + 1, coordBaseMode, func_35305_c());

        case 1: // '\001'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 - 1, boundingBox.y1 + j, boundingBox.z1 + i, coordBaseMode, func_35305_c());

        case 3: // '\003'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x2 + 1, boundingBox.y1 + j, boundingBox.z1 + i, coordBaseMode, func_35305_c());
        }
        return null;
    }

    protected StructureComponent func_35321_b(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c());

        case 0: // '\0'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c());

        case 1: // '\001'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c());

        case 3: // '\003'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c());
        }
        return null;
    }

    protected StructureComponent func_35320_c(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c());

        case 0: // '\0'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c());

        case 1: // '\001'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c());

        case 3: // '\003'
            return StructureStrongholdPieces.func_35624_a(componentstrongholdstairs2, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c());
        }
        return null;
    }

    protected static boolean func_35319_a(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.y1 > 10;
    }
}
