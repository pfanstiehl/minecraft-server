// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureMineshaftPieces, World

public class ComponentMineshaftStairs extends StructureComponent
{

    public ComponentMineshaftStairs(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public static StructureBoundingBox func_35365_a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j - 5, k, i, j + 2, k);
        switch(l)
        {
        case 2: // '\002'
            structureboundingbox.x2 = i + 2;
            structureboundingbox.z1 = k - 8;
            break;

        case 0: // '\0'
            structureboundingbox.x2 = i + 2;
            structureboundingbox.z2 = k + 8;
            break;

        case 1: // '\001'
            structureboundingbox.x1 = i - 8;
            structureboundingbox.z2 = k + 2;
            break;

        case 3: // '\003'
            structureboundingbox.x2 = i + 8;
            structureboundingbox.z2 = k + 2;
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
        switch(coordBaseMode)
        {
        case 2: // '\002'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, boundingBox.y1, boundingBox.z1 - 1, 2, i);
            break;

        case 0: // '\0'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, boundingBox.y1, boundingBox.z2 + 1, 0, i);
            break;

        case 1: // '\001'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1, boundingBox.z1, 1, i);
            break;

        case 3: // '\003'
            StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1, boundingBox.z1, 3, i);
            break;
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        fillWithBlocks(world, structureboundingbox, 0, 5, 0, 2, 7, 1, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 0, 0, 7, 2, 2, 8, 0, 0, false);
        for(int i = 0; i < 5; i++)
        {
            fillWithBlocks(world, structureboundingbox, 0, 5 - i - (i >= 4 ? 0 : 1), 2 + i, 2, 7 - i, 2 + i, 0, 0, false);
        }

        return true;
    }
}
