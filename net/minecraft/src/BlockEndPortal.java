// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, TileEntityEndPortal, Entity, EntityPlayer, 
//            World, WorldProvider, Material, TileEntity, 
//            IBlockAccess, AxisAlignedBB

public class BlockEndPortal extends BlockContainer
{

    public static boolean field_41003_a = false;

    protected BlockEndPortal(int i, Material material)
    {
        super(i, 0, material);
        setLightValue(1.0F);
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityEndPortal();
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        float f = 0.0625F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isACube()
    {
        return false;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if(entity.ridingEntity == null && entity.riddenByEntity == null && (entity instanceof EntityPlayer) && !world.singleplayerWorld)
        {
            ((EntityPlayer)entity).func_40107_e(1);
        }
    }

    public int getRenderType()
    {
        return -1;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        if(field_41003_a)
        {
            return;
        }
        if(world.worldProvider.worldType != 0)
        {
            world.setBlockWithNotify(i, j, k, 0);
            return;
        } else
        {
            return;
        }
    }

}
