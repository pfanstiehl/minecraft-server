// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public class StructureVillagePieceWeight
{

    public Class villageComponentClass;
    public final int villagePieceWeight;
    public int villagePiecesSpawned;
    public int villagePiecesLimit;

    public StructureVillagePieceWeight(Class class1, int i, int j)
    {
        villageComponentClass = class1;
        villagePieceWeight = i;
        villagePiecesLimit = j;
    }

    public boolean canSpawnMoreVillagePiecesOfType(int i)
    {
        return villagePiecesLimit == 0 || villagePiecesSpawned < villagePiecesLimit;
    }

    public boolean canSpawnMoreVillagePieces()
    {
        return villagePiecesLimit == 0 || villagePiecesSpawned < villagePiecesLimit;
    }
}
