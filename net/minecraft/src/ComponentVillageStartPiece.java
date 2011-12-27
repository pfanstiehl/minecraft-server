// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentVillageWell, WorldChunkManager, StructureVillagePieceWeight

public class ComponentVillageStartPiece extends ComponentVillageWell
{

    public WorldChunkManager worldChunkMngr;
    public int field_35390_b;
    public StructureVillagePieceWeight structVillagePieceWeight;
    public ArrayList structureVillageWeightedPieceList;
    public ArrayList field_35389_e;
    public ArrayList field_35387_f;

    public ComponentVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, ArrayList arraylist, int l)
    {
        super(0, random, j, k);
        field_35389_e = new ArrayList();
        field_35387_f = new ArrayList();
        worldChunkMngr = worldchunkmanager;
        structureVillageWeightedPieceList = arraylist;
        field_35390_b = l;
    }

    public WorldChunkManager getWorldChunkMngr()
    {
        return worldChunkMngr;
    }
}
