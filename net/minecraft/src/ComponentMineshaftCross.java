// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureMineshaftPieces, Block, 
//            World

public class ComponentMineshaftCross extends StructureComponent
{

    private final int field_35364_a;
    private final boolean field_35363_b;

    public ComponentMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        field_35364_a = j;
        boundingBox = structureboundingbox;
        field_35363_b = structureboundingbox.bbHeight() > 3;
    }

    public static StructureBoundingBox func_35362_a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);
        if(random.nextInt(4) == 0)
        {
            structureboundingbox.y2 += 4;
        }
        switch(l)
        {
        case 2: // '\002'
            structureboundingbox.x1 = i - 1;
            structureboundingbox.x2 = i + 3;
            structureboundingbox.z1 = k - 4;
            break;

        case 0: // '\0'
            structureboundingbox.x1 = i - 1;
            structureboundingbox.x2 = i + 3;
            structureboundingbox.z2 = k + 4;
            break;

        case 1: // '\001'
            structureboundingbox.x1 = i - 4;
            structureboundingbox.z1 = k - 1;
            structureboundingbox.z2 = k + 3;
            break;

        case 3: // '\003'
            structureboundingbox.x2 = i + 4;
            structureboundingbox.z1 = k - 1;
            structureboundingbox.z2 = k + 3;
            break;
        }
        if(StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return structureboundingbox;
        }
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        int i = func_35305_c();
        switch(field_35364_a)
        {
        case 2: // '\002'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1 - 1, 2, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1, boundingBox.z1 + 1, 1, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1, boundingBox.z1 + 1, 3, i);
            break;

        case 0: // '\0'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z2 + 1, 0, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1, boundingBox.z1 + 1, 1, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1, boundingBox.z1 + 1, 3, i);
            break;

        case 1: // '\001'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1 - 1, 2, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z2 + 1, 0, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1, boundingBox.z1 + 1, 1, i);
            break;

        case 3: // '\003'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1 - 1, 2, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z2 + 1, 0, i);
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1, boundingBox.z1 + 1, 3, i);
            break;
        }
        if(field_35363_b)
        {
            if(random.nextBoolean())
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1 + 3 + 1, boundingBox.z1 - 1, 2, i);
            }
            if(random.nextBoolean())
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1 + 3 + 1, boundingBox.z1 + 1, 1, i);
            }
            if(random.nextBoolean())
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1 + 3 + 1, boundingBox.z1 + 1, 3, i);
            }
            if(random.nextBoolean())
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + 1, boundingBox.y1 + 3 + 1, boundingBox.z2 + 1, 0, i);
            }
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        if(field_35363_b)
        {
            fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1, boundingBox.x2 - 1, (boundingBox.y1 + 3) - 1, boundingBox.z2, 0, 0, false);
            fillWithBlocks(world, structureboundingbox, boundingBox.x1, boundingBox.y1, boundingBox.z1 + 1, boundingBox.x2, (boundingBox.y1 + 3) - 1, boundingBox.z2 - 1, 0, 0, false);
            fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y2 - 2, boundingBox.z1, boundingBox.x2 - 1, boundingBox.y2, boundingBox.z2, 0, 0, false);
            fillWithBlocks(world, structureboundingbox, boundingBox.x1, boundingBox.y2 - 2, boundingBox.z1 + 1, boundingBox.x2, boundingBox.y2, boundingBox.z2 - 1, 0, 0, false);
            fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y1 + 3, boundingBox.z1 + 1, boundingBox.x2 - 1, boundingBox.y1 + 3, boundingBox.z2 - 1, 0, 0, false);
        } else
        {
            fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1, boundingBox.x2 - 1, boundingBox.y2, boundingBox.z2, 0, 0, false);
            fillWithBlocks(world, structureboundingbox, boundingBox.x1, boundingBox.y1, boundingBox.z1 + 1, boundingBox.x2, boundingBox.y2, boundingBox.z2 - 1, 0, 0, false);
        }
        fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z1 + 1, boundingBox.x1 + 1, boundingBox.y2, boundingBox.z1 + 1, Block.planks.blockID, 0, false);
        fillWithBlocks(world, structureboundingbox, boundingBox.x1 + 1, boundingBox.y1, boundingBox.z2 - 1, boundingBox.x1 + 1, boundingBox.y2, boundingBox.z2 - 1, Block.planks.blockID, 0, false);
        fillWithBlocks(world, structureboundingbox, boundingBox.x2 - 1, boundingBox.y1, boundingBox.z1 + 1, boundingBox.x2 - 1, boundingBox.y2, boundingBox.z1 + 1, Block.planks.blockID, 0, false);
        fillWithBlocks(world, structureboundingbox, boundingBox.x2 - 1, boundingBox.y1, boundingBox.z2 - 1, boundingBox.x2 - 1, boundingBox.y2, boundingBox.z2 - 1, Block.planks.blockID, 0, false);
        return true;
    }
}
