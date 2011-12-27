// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, World

public abstract class StructureStart
{

    protected LinkedList components;
    protected StructureBoundingBox boundingBox;

    protected StructureStart()
    {
        components = new LinkedList();
    }

    public StructureBoundingBox getBoundingBox()
    {
        return boundingBox;
    }

    public LinkedList func_40208_c()
    {
        return components;
    }

    public void generateStructure(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        Iterator iterator = components.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            if(structurecomponent.getStructureBoundingBox().canFitInside(structureboundingbox) && !structurecomponent.addComponentParts(world, random, structureboundingbox))
            {
                iterator.remove();
            }
        } while(true);
    }

    protected void updateBoundingBox()
    {
        boundingBox = StructureBoundingBox.getNewBoundingBox();
        StructureComponent structurecomponent;
        for(Iterator iterator = components.iterator(); iterator.hasNext(); boundingBox.expandTo(structurecomponent.getStructureBoundingBox()))
        {
            structurecomponent = (StructureComponent)iterator.next();
        }

    }

    protected void markAvailableHeight(World world, Random random, int i)
    {
        int j = world.worldOceanHeight - i;
        int k = boundingBox.bbHeight() + 1;
        if(k < j)
        {
            k += random.nextInt(j - k);
        }
        int l = k - boundingBox.y2;
        boundingBox.offset(0, l, 0);
        StructureComponent structurecomponent;
        for(Iterator iterator = components.iterator(); iterator.hasNext(); structurecomponent.getStructureBoundingBox().offset(0, l, 0))
        {
            structurecomponent = (StructureComponent)iterator.next();
        }

    }

    protected void func_40209_a(World world, Random random, int i, int j)
    {
        int k = ((j - i) + 1) - boundingBox.bbHeight();
        int l = 1;
        if(k > 1)
        {
            l = i + random.nextInt(k);
        } else
        {
            l = i;
        }
        int i1 = l - boundingBox.y1;
        boundingBox.offset(0, i1, 0);
        StructureComponent structurecomponent;
        for(Iterator iterator = components.iterator(); iterator.hasNext(); structurecomponent.getStructureBoundingBox().offset(0, i1, 0))
        {
            structurecomponent = (StructureComponent)iterator.next();
        }

    }

    public boolean isSizeableStructure()
    {
        return true;
    }
}
