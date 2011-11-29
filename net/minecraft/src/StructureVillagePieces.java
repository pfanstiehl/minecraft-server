// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureVillagePieceWeight, ComponentVillageHouse4_Garden, MathHelper, ComponentVillageChurch, 
//            ComponentVillageHouse1, ComponentVillageWoodHut, ComponentVillageHall, ComponentVillageField, 
//            ComponentVillageField2, ComponentVillageHouse2, ComponentVillageHouse3, ComponentVillageStartPiece, 
//            ComponentVillageTorch, StructureBoundingBox, StructureComponent, MapGenVillage, 
//            WorldChunkManager, ComponentVillagePathGen, ComponentVillage

public class StructureVillagePieces
{

    public StructureVillagePieces()
    {
    }

    public static ArrayList getStructureVillageWeightedPieceList(Random random, int i)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageHouse4_Garden.class, 4, MathHelper.func_35476_a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageChurch.class, 20, MathHelper.func_35476_a(random, 0 + i, 1 + i)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageHouse1.class, 20, MathHelper.func_35476_a(random, 0 + i, 2 + i)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageWoodHut.class, 3, MathHelper.func_35476_a(random, 2 + i, 5 + i * 3)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageHall.class, 15, MathHelper.func_35476_a(random, 0 + i, 2 + i)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageField.class, 3, MathHelper.func_35476_a(random, 1 + i, 4 + i)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageField2.class, 3, MathHelper.func_35476_a(random, 2 + i, 4 + i * 2)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageHouse2.class, 15, MathHelper.func_35476_a(random, 0, 1 + i)));
        arraylist.add(new StructureVillagePieceWeight(net.minecraft.src.ComponentVillageHouse3.class, 8, MathHelper.func_35476_a(random, 0 + i, 3 + i * 2)));
        Iterator iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            if(((StructureVillagePieceWeight)iterator.next()).villagePiecesLimit == 0)
            {
                iterator.remove();
            }
        } while(true);
        return arraylist;
    }

    private static int getAvailablePieceWeight(ArrayList arraylist)
    {
        boolean flag = false;
        int i = 0;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            StructureVillagePieceWeight structurevillagepieceweight = (StructureVillagePieceWeight)iterator.next();
            if(structurevillagepieceweight.villagePiecesLimit > 0 && structurevillagepieceweight.villagePiecesSpawned < structurevillagepieceweight.villagePiecesLimit)
            {
                flag = true;
            }
            i += structurevillagepieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }

    private static ComponentVillage getVillageComponentFromWeightedPiece(StructureVillagePieceWeight structurevillagepieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Class class1 = structurevillagepieceweight.villageComponentClass;
        Object obj = null;
        if(class1 == (net.minecraft.src.ComponentVillageHouse4_Garden.class))
        {
            obj = ComponentVillageHouse4_Garden.func_35401_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageChurch.class))
        {
            obj = ComponentVillageChurch.func_35380_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageHouse1.class))
        {
            obj = ComponentVillageHouse1.func_35397_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageWoodHut.class))
        {
            obj = ComponentVillageWoodHut.func_35393_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageHall.class))
        {
            obj = ComponentVillageHall.func_35374_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageField.class))
        {
            obj = ComponentVillageField.func_35370_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageField2.class))
        {
            obj = ComponentVillageField2.func_35399_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageHouse2.class))
        {
            obj = ComponentVillageHouse2.func_35376_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentVillageHouse3.class))
        {
            obj = ComponentVillageHouse3.func_35372_a(list, random, i, j, k, l, i1);
        }
        return ((ComponentVillage) (obj));
    }

    private static ComponentVillage getNextVillageComponent(ComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7)
    {
        int var8 = getAvailablePieceWeight(var0.field_35388_d);
        if(var8 <= 0)
        {
            return null;
        }
        else
        {
            int var9 = 0;

            while(var9 < 5)
            {
                ++var9;
                int var10 = var2.nextInt(var8);
                Iterator var11 = var0.field_35388_d.iterator();

                while(var11.hasNext())
                {
                    StructureVillagePieceWeight var12 = (StructureVillagePieceWeight)var11.next();
                    var10 -= var12.villagePieceWeight;
                    if(var10 < 0)
                    {
                        if(!var12.canSpawnMoreVillagePiecesOfType(var7) || var12 == var0.field_35391_c && var0.field_35388_d.size() > 1)
                        {
                            break;
                        }

                        ComponentVillage var13 = getVillageComponentFromWeightedPiece(var12, var1, var2, var3, var4, var5, var6, var7);
                        if(var13 != null)
                        {
                            ++var12.villagePiecesSpawned;
                            var0.field_35391_c = var12;
                            if(!var12.canSpawnMoreVillagePieces())
                            {
                                var0.field_35388_d.remove(var12);
                            }

                            return var13;
                        }
                    }
                }
            }

            StructureBoundingBox var14 = ComponentVillageTorch.func_35382_a(var1, var2, var3, var4, var5, var6);
            if(var14 != null)
            {
                return new ComponentVillageTorch(var7, var2, var14, var6);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent getNextVillageStructureComponent(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 50)
        {
            return null;
        }
        if(Math.abs(i - componentvillagestartpiece.getStructureBoundingBox().x1) > 112 || Math.abs(k - componentvillagestartpiece.getStructureBoundingBox().z1) > 112)
        {
            return null;
        }
        ComponentVillage componentvillage = getNextVillageComponent(componentvillagestartpiece, list, random, i, j, k, l, i1 + 1);
        if(componentvillage != null)
        {
            int j1 = (((StructureComponent) (componentvillage)).boundingBox.x1 + ((StructureComponent) (componentvillage)).boundingBox.x2) / 2;
            int k1 = (((StructureComponent) (componentvillage)).boundingBox.z1 + ((StructureComponent) (componentvillage)).boundingBox.z2) / 2;
            int l1 = ((StructureComponent) (componentvillage)).boundingBox.x2 - ((StructureComponent) (componentvillage)).boundingBox.x1;
            int i2 = ((StructureComponent) (componentvillage)).boundingBox.z2 - ((StructureComponent) (componentvillage)).boundingBox.z1;
            int j2 = l1 <= i2 ? i2 : l1;
            if(componentvillagestartpiece.func_35386_a().func_35141_a(j1, k1, j2 / 2 + 4, MapGenVillage.field_35538_a))
            {
                list.add(componentvillage);
                componentvillagestartpiece.field_35389_e.add(componentvillage);
                return componentvillage;
            }
        }
        return null;
    }

    private static StructureComponent getNextComponentVillagePath(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 3 + componentvillagestartpiece.field_35390_b)
        {
            return null;
        }
        if(Math.abs(i - componentvillagestartpiece.getStructureBoundingBox().x1) > 112 || Math.abs(k - componentvillagestartpiece.getStructureBoundingBox().z1) > 112)
        {
            return null;
        }
        StructureBoundingBox structureboundingbox = ComponentVillagePathGen.func_35378_a(componentvillagestartpiece, list, random, i, j, k, l);
        if(structureboundingbox != null && structureboundingbox.y1 > 10)
        {
            ComponentVillagePathGen componentvillagepathgen = new ComponentVillagePathGen(i1, random, structureboundingbox, l);
            int j1 = (((StructureComponent) (componentvillagepathgen)).boundingBox.x1 + ((StructureComponent) (componentvillagepathgen)).boundingBox.x2) / 2;
            int k1 = (((StructureComponent) (componentvillagepathgen)).boundingBox.z1 + ((StructureComponent) (componentvillagepathgen)).boundingBox.z2) / 2;
            int l1 = ((StructureComponent) (componentvillagepathgen)).boundingBox.x2 - ((StructureComponent) (componentvillagepathgen)).boundingBox.x1;
            int i2 = ((StructureComponent) (componentvillagepathgen)).boundingBox.z2 - ((StructureComponent) (componentvillagepathgen)).boundingBox.z1;
            int j2 = l1 <= i2 ? i2 : l1;
            if(componentvillagestartpiece.func_35386_a().func_35141_a(j1, k1, j2 / 2 + 4, MapGenVillage.field_35538_a))
            {
                list.add(componentvillagepathgen);
                componentvillagestartpiece.field_35387_f.add(componentvillagepathgen);
                return componentvillagepathgen;
            }
        }
        return null;
    }

    static StructureComponent getNextStructureComponent(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return getNextVillageStructureComponent(componentvillagestartpiece, list, random, i, j, k, l, i1);
    }

    static StructureComponent getNextStructureComponentVillagePath(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return getNextComponentVillagePath(componentvillagestartpiece, list, random, i, j, k, l, i1);
    }
}
