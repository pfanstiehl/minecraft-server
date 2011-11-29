// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureStart, StructureVillagePieces, ComponentVillageStartPiece, World, 
//            StructureComponent, ComponentVillageRoadPiece

class StructureVillageStart extends StructureStart
{

    private boolean hasMoreThanTwoComponents;

    public StructureVillageStart(World world, Random random, int i, int j)
    {
        hasMoreThanTwoComponents = false;
        int k = 0;
        ArrayList arraylist = StructureVillagePieces.getStructureVillageWeightedPieceList(random, k);
        ComponentVillageStartPiece componentvillagestartpiece = new ComponentVillageStartPiece(world.getWorldChunkManager(), 0, random, (i << 4) + 2, (j << 4) + 2, arraylist, k);
        components.add(componentvillagestartpiece);
        componentvillagestartpiece.buildComponent(componentvillagestartpiece, components, random);
        ArrayList arraylist1 = componentvillagestartpiece.field_35387_f;
        for(ArrayList arraylist2 = componentvillagestartpiece.field_35389_e; !arraylist1.isEmpty() || !arraylist2.isEmpty();)
        {
            if(!arraylist1.isEmpty())
            {
                int l = random.nextInt(arraylist1.size());
                StructureComponent structurecomponent = (StructureComponent)arraylist1.remove(l);
                structurecomponent.buildComponent(componentvillagestartpiece, components, random);
            } else
            {
                int i1 = random.nextInt(arraylist2.size());
                StructureComponent structurecomponent1 = (StructureComponent)arraylist2.remove(i1);
                structurecomponent1.buildComponent(componentvillagestartpiece, components, random);
            }
        }

        updateBoundingBox();
        int j1 = 0;
        Iterator iterator = components.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            StructureComponent structurecomponent2 = (StructureComponent)iterator.next();
            if(!(structurecomponent2 instanceof ComponentVillageRoadPiece))
            {
                j1++;
            }
        } while(true);
        hasMoreThanTwoComponents = j1 > 2;
    }

    public boolean isSizeableStructure()
    {
        return hasMoreThanTwoComponents;
    }
}
