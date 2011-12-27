// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.LinkedList;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureStart, ComponentMineshaftRoom, World

public class StructureMineshaftStart extends StructureStart
{

    public StructureMineshaftStart(World world, Random random, int i, int j)
    {
        ComponentMineshaftRoom componentmineshaftroom = new ComponentMineshaftRoom(0, random, (i << 4) + 2, (j << 4) + 2);
        components.add(componentmineshaftroom);
        componentmineshaftroom.buildComponent(componentmineshaftroom, components, random);
        updateBoundingBox();
        markAvailableHeight(world, random, 10);
    }
}
