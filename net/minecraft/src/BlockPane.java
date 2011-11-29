// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, World, IBlockAccess, Material, 
//            AxisAlignedBB

public class BlockPane extends Block
{

    private int field_35064_a;
    private final boolean field_40178_b;

    protected BlockPane(int i, int j, int k, Material material, boolean flag)
    {
        super(i, j, material);
        field_35064_a = k;
        field_40178_b = flag;
    }

    public int idDropped(int i, Random random, int j)
    {
        if(!field_40178_b)
        {
            return 0;
        } else
        {
            return super.idDropped(i, random, j);
        }
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isACube()
    {
        return false;
    }

    public int getRenderType()
    {
        return 18;
    }

    public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        boolean flag = func_35063_c(world.getBlockId(i, j, k - 1));
        boolean flag1 = func_35063_c(world.getBlockId(i, j, k + 1));
        boolean flag2 = func_35063_c(world.getBlockId(i - 1, j, k));
        boolean flag3 = func_35063_c(world.getBlockId(i + 1, j, k));
        if(flag2 && flag3 || !flag2 && !flag3 && !flag && !flag1)
        {
            setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(flag2 && !flag3)
        {
            setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(!flag2 && flag3)
        {
            setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
        if(flag && flag1 || !flag2 && !flag3 && !flag && !flag1)
        {
            setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(flag && !flag1)
        {
            setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        } else
        if(!flag && flag1)
        {
            setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
    }

    public void func_40163_f()
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = func_35063_c(iblockaccess.getBlockId(i, j, k - 1));
        boolean flag1 = func_35063_c(iblockaccess.getBlockId(i, j, k + 1));
        boolean flag2 = func_35063_c(iblockaccess.getBlockId(i - 1, j, k));
        boolean flag3 = func_35063_c(iblockaccess.getBlockId(i + 1, j, k));
        if(flag2 && flag3 || !flag2 && !flag3 && !flag && !flag1)
        {
            f = 0.0F;
            f1 = 1.0F;
        } else
        if(flag2 && !flag3)
        {
            f = 0.0F;
        } else
        if(!flag2 && flag3)
        {
            f1 = 1.0F;
        }
        if(flag && flag1 || !flag2 && !flag3 && !flag && !flag1)
        {
            f2 = 0.0F;
            f3 = 1.0F;
        } else
        if(flag && !flag1)
        {
            f2 = 0.0F;
        } else
        if(!flag && flag1)
        {
            f3 = 1.0F;
        }
        setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public final boolean func_35063_c(int i)
    {
        return Block.opaqueCubeLookup[i] || i == blockID || i == Block.glass.blockID;
    }
}
