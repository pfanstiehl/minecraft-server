// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureNetherBridgePieceWeight, ComponentNetherBridgeStartPiece, StructureNetherBridgePieces, 
//            StructureNetherBridgeEnd, StructureBoundingBox

abstract class ComponentNetherBridgePiece extends StructureComponent
{

    protected ComponentNetherBridgePiece(int i)
    {
        super(i);
    }

    private int func_40282_a(List list)
    {
        boolean flag = false;
        int i = 0;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            StructureNetherBridgePieceWeight structurenetherbridgepieceweight = (StructureNetherBridgePieceWeight)iterator.next();
            if(structurenetherbridgepieceweight.field_40651_d > 0 && structurenetherbridgepieceweight.field_40654_c < structurenetherbridgepieceweight.field_40651_d)
            {
                flag = true;
            }
            i += structurenetherbridgepieceweight.field_40653_b;
        }

        return flag ? i : -1;
    }

    private ComponentNetherBridgePiece func_40284_a(ComponentNetherBridgeStartPiece var1, List var2, List var3, Random var4, int var5, int var6, int var7, int var8, int var9)
    {
        int var10 = this.func_40282_a(var2);
        boolean var11 = var10 > 0 && var9 <= 30;
        int var12 = 0;

        while(var12 < 5 && var11)
        {
            ++var12;
            int var13 = var4.nextInt(var10);
            Iterator var14 = var2.iterator();

            while(var14.hasNext())
            {
                StructureNetherBridgePieceWeight var15 = (StructureNetherBridgePieceWeight)var14.next();
                var13 -= var15.field_40653_b;
                if(var13 < 0)
                {
                    if(!var15.func_40649_a(var9) || var15 == var1.field_40296_a && !var15.field_40652_e)
                    {
                        break;
                    }

                    ComponentNetherBridgePiece var16 = StructureNetherBridgePieces.func_40538_a(var15, var3, var4, var5, var6, var7, var8, var9);
                    if(var16 != null)
                    {
                        ++var15.field_40654_c;
                        var1.field_40296_a = var15;
                        if(!var15.func_40650_a())
                        {
                            var2.remove(var15);
                        }

                        return var16;
                    }
                }
            }
        }

        StructureNetherBridgeEnd var17 = StructureNetherBridgeEnd.func_40301_a(var3, var4, var5, var6, var7, var8, var9);
        return var17;
    }

    private StructureComponent func_40283_a(ComponentNetherBridgeStartPiece componentnetherbridgestartpiece, List list, Random random, int i, int j, int k, int l, 
            int i1, boolean flag)
    {
        if(Math.abs(i - componentnetherbridgestartpiece.getStructureBoundingBox().x1) > 112 || Math.abs(k - componentnetherbridgestartpiece.getStructureBoundingBox().z1) > 112)
        {
            StructureNetherBridgeEnd structurenetherbridgeend = StructureNetherBridgeEnd.func_40301_a(list, random, i, j, k, l, i1);
            return structurenetherbridgeend;
        }
        List list1 = componentnetherbridgestartpiece.field_40294_b;
        if(flag)
        {
            list1 = componentnetherbridgestartpiece.field_40295_c;
        }
        ComponentNetherBridgePiece componentnetherbridgepiece = func_40284_a(componentnetherbridgestartpiece, list1, list, random, i, j, k, l, i1 + 1);
        if(componentnetherbridgepiece != null)
        {
            list.add(componentnetherbridgepiece);
            componentnetherbridgestartpiece.field_40293_d.add(componentnetherbridgepiece);
        }
        return componentnetherbridgepiece;
    }

    protected StructureComponent func_40287_a(ComponentNetherBridgeStartPiece componentnetherbridgestartpiece, List list, Random random, int i, int j, boolean flag)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + i, boundingBox.y1 + j, boundingBox.z1 - 1, coordBaseMode, func_35305_c(), flag);

        case 0: // '\0'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + i, boundingBox.y1 + j, boundingBox.z2 + 1, coordBaseMode, func_35305_c(), flag);

        case 1: // '\001'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 - 1, boundingBox.y1 + j, boundingBox.z1 + i, coordBaseMode, func_35305_c(), flag);

        case 3: // '\003'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x2 + 1, boundingBox.y1 + j, boundingBox.z1 + i, coordBaseMode, func_35305_c(), flag);
        }
        return null;
    }

    protected StructureComponent func_40285_b(ComponentNetherBridgeStartPiece componentnetherbridgestartpiece, List list, Random random, int i, int j, boolean flag)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c(), flag);

        case 0: // '\0'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 - 1, boundingBox.y1 + i, boundingBox.z1 + j, 1, func_35305_c(), flag);

        case 1: // '\001'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c(), flag);

        case 3: // '\003'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z1 - 1, 2, func_35305_c(), flag);
        }
        return null;
    }

    protected StructureComponent func_40288_c(ComponentNetherBridgeStartPiece componentnetherbridgestartpiece, List list, Random random, int i, int j, boolean flag)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c(), flag);

        case 0: // '\0'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x2 + 1, boundingBox.y1 + i, boundingBox.z1 + j, 3, func_35305_c(), flag);

        case 1: // '\001'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c(), flag);

        case 3: // '\003'
            return func_40283_a(componentnetherbridgestartpiece, list, random, boundingBox.x1 + j, boundingBox.y1 + i, boundingBox.z2 + 1, 0, func_35305_c(), flag);
        }
        return null;
    }

    protected static boolean func_40286_a(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.y1 > 10;
    }
}
