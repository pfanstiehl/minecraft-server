// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


class StructureStrongholdPieceWeight
{

    public Class pieceClass;
    public final int pieceWeight;
    public int instancesSpawned;
    public int instancesLimit;

    public StructureStrongholdPieceWeight(Class class1, int i, int j)
    {
        pieceClass = class1;
        pieceWeight = i;
        instancesLimit = j;
    }

    public boolean canSpawnMoreStructuresOfType(int i)
    {
        return instancesLimit == 0 || instancesSpawned < instancesLimit;
    }

    public boolean canSpawnMoreStructures()
    {
        return instancesLimit == 0 || instancesSpawned < instancesLimit;
    }
}
