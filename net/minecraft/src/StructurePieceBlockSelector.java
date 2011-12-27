// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

public abstract class StructurePieceBlockSelector
{

    protected int selectedBlockId;
    protected int selectedBlockMetaData;

    protected StructurePieceBlockSelector()
    {
    }

    public abstract void selectBlocks(Random random, int i, int j, int k, boolean flag);

    public int getSelectedBlockId()
    {
        return selectedBlockId;
    }

    public int getSelectedBlockMetaData()
    {
        return selectedBlockMetaData;
    }
}
