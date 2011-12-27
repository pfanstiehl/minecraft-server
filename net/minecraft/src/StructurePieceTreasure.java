// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WeightedRandomChoice

public class StructurePieceTreasure extends WeightedRandomChoice
{

    public int itemID;
    public int itemMetadata;
    public int minItemStack;
    public int maxItemStack;

    public StructurePieceTreasure(int i, int j, int k, int l, int i1)
    {
        super(i1);
        itemID = i;
        itemMetadata = j;
        minItemStack = k;
        maxItemStack = l;
    }
}
