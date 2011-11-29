// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureStrongholdPieceWeight, ComponentStrongholdStraight, ComponentStrongholdPrison, ComponentStrongholdLeftTurn, 
//            ComponentStrongholdRightTurn, ComponentStrongholdRoomCrossing, ComponentStrongholdStairsStraight, ComponentStrongholdStairs, 
//            ComponentStrongholdCrossing, ComponentStrongholdChestCorridor, ComponentStrongholdLibrary, ComponentStrongholdPortalRoom, 
//            ComponentStrongholdStairs2, ComponentStrongholdCorridor, StructureBoundingBox, StructureStrongholdPieceWeight2, 
//            StructureStrongholdPieceWeight3, StructureStrongholdStones, ComponentStronghold, StructureComponent

public class StructureStrongholdPieces
{

    private static final StructureStrongholdPieceWeight pieceWeightArray[];
    private static List structurePieceList;
    private static Class field_40542_d;
    static int totalWeight = 0;
    private static final StructureStrongholdStones field_35627_d = new StructureStrongholdStones(null);

    public StructureStrongholdPieces()
    {
    }

    public static void prepareStructurePieces()
    {
        structurePieceList = new ArrayList();
        StructureStrongholdPieceWeight astructurestrongholdpieceweight[] = pieceWeightArray;
        int i = astructurestrongholdpieceweight.length;
        for(int j = 0; j < i; j++)
        {
            StructureStrongholdPieceWeight structurestrongholdpieceweight = astructurestrongholdpieceweight[j];
            structurestrongholdpieceweight.instancesSpawned = 0;
            structurePieceList.add(structurestrongholdpieceweight);
        }

        field_40542_d = null;
    }

    private static boolean canAddStructurePieces()
    {
        boolean flag = false;
        totalWeight = 0;
        for(Iterator iterator = structurePieceList.iterator(); iterator.hasNext();)
        {
            StructureStrongholdPieceWeight structurestrongholdpieceweight = (StructureStrongholdPieceWeight)iterator.next();
            if(structurestrongholdpieceweight.instancesLimit > 0 && structurestrongholdpieceweight.instancesSpawned < structurestrongholdpieceweight.instancesLimit)
            {
                flag = true;
            }
            totalWeight += structurestrongholdpieceweight.pieceWeight;
        }

        return flag;
    }

    private static ComponentStronghold getStrongholdComponentFromWeightedPiece(Class class1, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Object obj = null;
        if(class1 == (net.minecraft.src.ComponentStrongholdStraight.class))
        {
            obj = ComponentStrongholdStraight.func_35338_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdPrison.class))
        {
            obj = ComponentStrongholdPrison.func_35332_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdLeftTurn.class))
        {
            obj = ComponentStrongholdLeftTurn.func_35330_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdRightTurn.class))
        {
            obj = ComponentStrongholdRightTurn.func_35330_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdRoomCrossing.class))
        {
            obj = ComponentStrongholdRoomCrossing.func_35346_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdStairsStraight.class))
        {
            obj = ComponentStrongholdStairsStraight.func_35344_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdStairs.class))
        {
            obj = ComponentStrongholdStairs.getStrongholdStairsComponent(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdCrossing.class))
        {
            obj = ComponentStrongholdCrossing.func_35350_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdChestCorridor.class))
        {
            obj = ComponentStrongholdChestCorridor.func_40311_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdLibrary.class))
        {
            obj = ComponentStrongholdLibrary.func_35334_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdPortalRoom.class))
        {
            obj = ComponentStrongholdPortalRoom.func_40315_a(list, random, i, j, k, l, i1);
        }
        return ((ComponentStronghold) (obj));
    }

    private static ComponentStronghold func_35623_b(ComponentStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7)
    {
        if(!canAddStructurePieces())
        {
            return null;
        }
        else
        {
            if(field_40542_d != null)
            {
                ComponentStronghold var8 = getStrongholdComponentFromWeightedPiece(field_40542_d, var1, var2, var3, var4, var5, var6, var7);
                field_40542_d = null;
                if(var8 != null)
                {
                    return var8;
                }
            }

            int var13 = 0;

            while(var13 < 5)
            {
                ++var13;
                int var9 = var2.nextInt(totalWeight);
                Iterator var10 = structurePieceList.iterator();

                while(var10.hasNext())
                {
                    StructureStrongholdPieceWeight var11 = (StructureStrongholdPieceWeight)var10.next();
                    var9 -= var11.pieceWeight;
                    if(var9 < 0)
                    {
                        if(!var11.canSpawnMoreStructuresOfType(var7) || var11 == var0.field_35329_a)
                        {
                            break;
                        }

                        ComponentStronghold var12 = getStrongholdComponentFromWeightedPiece(var11.pieceClass, var1, var2, var3, var4, var5, var6, var7);
                        if(var12 != null)
                        {
                            ++var11.instancesSpawned;
                            var0.field_35329_a = var11;
                            if(!var11.canSpawnMoreStructures())
                            {
                                structurePieceList.remove(var11);
                            }

                            return var12;
                        }
                    }
                }
            }

            StructureBoundingBox var14 = ComponentStrongholdCorridor.func_35342_a(var1, var2, var3, var4, var5, var6);
            if(var14 != null && var14.y1 > 1)
            {
                return new ComponentStrongholdCorridor(var7, var2, var14, var6);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_35621_c(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 50)
        {
            return null;
        }
        if(Math.abs(i - componentstrongholdstairs2.getStructureBoundingBox().x1) > 112 || Math.abs(k - componentstrongholdstairs2.getStructureBoundingBox().z1) > 112)
        {
            return null;
        }
        ComponentStronghold componentstronghold = func_35623_b(componentstrongholdstairs2, list, random, i, j, k, l, i1 + 1);
        if(componentstronghold != null)
        {
            list.add(componentstronghold);
            componentstrongholdstairs2.field_35328_b.add(componentstronghold);
        }
        return componentstronghold;
    }

    static StructureComponent func_35624_a(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return func_35621_c(componentstrongholdstairs2, list, random, i, j, k, l, i1);
    }

    static Class func_40541_a(Class class1)
    {
        return field_40542_d = class1;
    }

    static StructureStrongholdStones getStrongholdStones()
    {
        return field_35627_d;
    }

    static 
    {
        pieceWeightArray = (new StructureStrongholdPieceWeight[] {
            new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStraight.class, 40, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdPrison.class, 5, 5), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdLeftTurn.class, 20, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdRightTurn.class, 20, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdRoomCrossing.class, 10, 6), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStairsStraight.class, 5, 5), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStairs.class, 5, 5), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdCrossing.class, 5, 4), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdChestCorridor.class, 5, 4), new StructureStrongholdPieceWeight2(net.minecraft.src.ComponentStrongholdLibrary.class, 10, 2), 
            new StructureStrongholdPieceWeight3(net.minecraft.src.ComponentStrongholdPortalRoom.class, 20, 1)
        });
    }
}
