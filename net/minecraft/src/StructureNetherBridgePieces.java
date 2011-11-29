// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureNetherBridgePieceWeight, ComponentNetherBridgeStraight, ComponentNetherBridgeCrossing3, ComponentNetherBridgeCrossing, 
//            ComponentNetherBridgeStairs, ComponentNetherBridgeThrone, ComponentNetherBridgeEntrance, ComponentNetherBridgeCorridor5, 
//            ComponentNetherBridgeCorridor2, ComponentNetherBridgeCorridor, ComponentNetherBridgeCorridor3, ComponentNetherBridgeCorridor4, 
//            ComponentNetherBridgeCrossing2, ComponentNetherBridgeNetherStalkRoom, ComponentNetherBridgePiece

public class StructureNetherBridgePieces
{

    private static final StructureNetherBridgePieceWeight field_40540_a[];
    private static final StructureNetherBridgePieceWeight field_40539_b[];

    public StructureNetherBridgePieces()
    {
    }

    private static ComponentNetherBridgePiece func_40537_b(StructureNetherBridgePieceWeight structurenetherbridgepieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Class class1 = structurenetherbridgepieceweight.field_40655_a;
        Object obj = null;
        if(class1 == (net.minecraft.src.ComponentNetherBridgeStraight.class))
        {
            obj = ComponentNetherBridgeStraight.func_40289_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCrossing3.class))
        {
            obj = ComponentNetherBridgeCrossing3.func_40292_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCrossing.class))
        {
            obj = ComponentNetherBridgeCrossing.func_40306_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeStairs.class))
        {
            obj = ComponentNetherBridgeStairs.func_40299_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeThrone.class))
        {
            obj = ComponentNetherBridgeThrone.func_40304_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeEntrance.class))
        {
            obj = ComponentNetherBridgeEntrance.func_40307_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCorridor5.class))
        {
            obj = ComponentNetherBridgeCorridor5.func_40300_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCorridor2.class))
        {
            obj = ComponentNetherBridgeCorridor2.func_40290_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCorridor.class))
        {
            obj = ComponentNetherBridgeCorridor.func_40297_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCorridor3.class))
        {
            obj = ComponentNetherBridgeCorridor3.func_40308_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCorridor4.class))
        {
            obj = ComponentNetherBridgeCorridor4.func_40298_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeCrossing2.class))
        {
            obj = ComponentNetherBridgeCrossing2.func_40303_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentNetherBridgeNetherStalkRoom.class))
        {
            obj = ComponentNetherBridgeNetherStalkRoom.func_40291_a(list, random, i, j, k, l, i1);
        }
        return ((ComponentNetherBridgePiece) (obj));
    }

    static ComponentNetherBridgePiece func_40538_a(StructureNetherBridgePieceWeight structurenetherbridgepieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return func_40537_b(structurenetherbridgepieceweight, list, random, i, j, k, l, i1);
    }

    static StructureNetherBridgePieceWeight[] func_40536_a()
    {
        return field_40540_a;
    }

    static StructureNetherBridgePieceWeight[] func_40535_b()
    {
        return field_40539_b;
    }

    static 
    {
        field_40540_a = (new StructureNetherBridgePieceWeight[] {
            new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeStraight.class, 30, 0, true), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCrossing3.class, 10, 4), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCrossing.class, 10, 4), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeStairs.class, 10, 3), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeThrone.class, 5, 2), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeEntrance.class, 5, 1)
        });
        field_40539_b = (new StructureNetherBridgePieceWeight[] {
            new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCorridor5.class, 25, 0, true), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCrossing2.class, 15, 5), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCorridor2.class, 5, 10), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCorridor.class, 5, 10), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCorridor3.class, 10, 3, true), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeCorridor4.class, 7, 2), new StructureNetherBridgePieceWeight(net.minecraft.src.ComponentNetherBridgeNetherStalkRoom.class, 5, 2)
        });
    }
}
