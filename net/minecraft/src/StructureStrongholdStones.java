// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructurePieceBlockSelector, Block, StructureStrongholdPieceWeight2

class StructureStrongholdStones extends StructurePieceBlockSelector
{

    private StructureStrongholdStones()
    {
    }

    public void selectBlocks(Random random, int i, int j, int k, boolean flag)
    {
        if(!flag)
        {
            selectedBlockId = 0;
            selectedBlockMetaData = 0;
        } else
        {
            selectedBlockId = Block.stoneBrick.blockID;
            float f = random.nextFloat();
            if(f < 0.2F)
            {
                selectedBlockMetaData = 2;
            } else
            if(f < 0.5F)
            {
                selectedBlockMetaData = 1;
            } else
            if(f < 0.55F)
            {
                selectedBlockId = Block.silverfish.blockID;
                selectedBlockMetaData = 2;
            } else
            {
                selectedBlockMetaData = 0;
            }
        }
    }

    StructureStrongholdStones(StructureStrongholdPieceWeight2 structurestrongholdpieceweight2)
    {
        this();
    }
}
