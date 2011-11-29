// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Material

public class BlockBreakable extends Block
{

    private boolean localFlag;

    protected BlockBreakable(int i, int j, Material material, boolean flag)
    {
        super(i, j, material);
        localFlag = flag;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}
