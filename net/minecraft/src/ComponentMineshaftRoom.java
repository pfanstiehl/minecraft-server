// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureMineshaftPieces, Block, 
//            World

public class ComponentMineshaftRoom extends StructureComponent
{

    private LinkedList field_35356_a;

    public ComponentMineshaftRoom(int i, Random random, int j, int k)
    {
        super(i);
        field_35356_a = new LinkedList();
        boundingBox = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        int i = func_35305_c();
        int j1 = boundingBox.bbHeight() - 3 - 1;
        if(j1 <= 0)
        {
            j1 = 1;
        }
        for(int j = 0; j < boundingBox.bbWidth(); j += 4)
        {
            j += random.nextInt(boundingBox.bbWidth());
            if(j + 3 > boundingBox.bbWidth())
            {
                break;
            }
            StructureComponent structurecomponent1 = StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + j, boundingBox.y1 + random.nextInt(j1) + 1, boundingBox.z1 - 1, 2, i);
            if(structurecomponent1 != null)
            {
                StructureBoundingBox structureboundingbox = structurecomponent1.getStructureBoundingBox();
                field_35356_a.add(new StructureBoundingBox(structureboundingbox.x1, structureboundingbox.y1, boundingBox.z1, structureboundingbox.x2, structureboundingbox.y2, boundingBox.z1 + 1));
            }
        }

        for(int k = 0; k < boundingBox.bbWidth(); k += 4)
        {
            k += random.nextInt(boundingBox.bbWidth());
            if(k + 3 > boundingBox.bbWidth())
            {
                break;
            }
            StructureComponent structurecomponent2 = StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 + k, boundingBox.y1 + random.nextInt(j1) + 1, boundingBox.z2 + 1, 0, i);
            if(structurecomponent2 != null)
            {
                StructureBoundingBox structureboundingbox1 = structurecomponent2.getStructureBoundingBox();
                field_35356_a.add(new StructureBoundingBox(structureboundingbox1.x1, structureboundingbox1.y1, boundingBox.z2 - 1, structureboundingbox1.x2, structureboundingbox1.y2, boundingBox.z2));
            }
        }

        for(int l = 0; l < boundingBox.bbDepth(); l += 4)
        {
            l += random.nextInt(boundingBox.bbDepth());
            if(l + 3 > boundingBox.bbDepth())
            {
                break;
            }
            StructureComponent structurecomponent3 = StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1 + random.nextInt(j1) + 1, boundingBox.z1 + l, 1, i);
            if(structurecomponent3 != null)
            {
                StructureBoundingBox structureboundingbox2 = structurecomponent3.getStructureBoundingBox();
                field_35356_a.add(new StructureBoundingBox(boundingBox.x1, structureboundingbox2.y1, structureboundingbox2.z1, boundingBox.x1 + 1, structureboundingbox2.y2, structureboundingbox2.z2));
            }
        }

        for(int i1 = 0; i1 < boundingBox.bbDepth(); i1 += 4)
        {
            i1 += random.nextInt(boundingBox.bbDepth());
            if(i1 + 3 > boundingBox.bbDepth())
            {
                break;
            }
            StructureComponent structurecomponent4 = StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1 + random.nextInt(j1) + 1, boundingBox.z1 + i1, 3, i);
            if(structurecomponent4 != null)
            {
                StructureBoundingBox structureboundingbox3 = structurecomponent4.getStructureBoundingBox();
                field_35356_a.add(new StructureBoundingBox(boundingBox.x2 - 1, structureboundingbox3.y1, structureboundingbox3.z1, boundingBox.x2, structureboundingbox3.y2, structureboundingbox3.z2));
            }
        }

    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        fillWithBlocks(world, structureboundingbox, boundingBox.x1, boundingBox.y1, boundingBox.z1, boundingBox.x2, boundingBox.y1, boundingBox.z2, Block.dirt.blockID, 0, true);
        fillWithBlocks(world, structureboundingbox, boundingBox.x1, boundingBox.y1 + 1, boundingBox.z1, boundingBox.x2, Math.min(boundingBox.y1 + 3, boundingBox.y2), boundingBox.z2, 0, 0, false);
        StructureBoundingBox structureboundingbox1;
        for(Iterator iterator = field_35356_a.iterator(); iterator.hasNext(); fillWithBlocks(world, structureboundingbox, structureboundingbox1.x1, structureboundingbox1.y2 - 2, structureboundingbox1.z1, structureboundingbox1.x2, structureboundingbox1.y2, structureboundingbox1.z2, 0, 0, false))
        {
            structureboundingbox1 = (StructureBoundingBox)iterator.next();
        }

        func_35304_a(world, structureboundingbox, boundingBox.x1, boundingBox.y1 + 4, boundingBox.z1, boundingBox.x2, boundingBox.y2, boundingBox.z2, 0, false);
        return true;
    }
}
