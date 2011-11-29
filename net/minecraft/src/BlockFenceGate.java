// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Block, Material, World, AxisAlignedBB, 
//            EntityLiving, MathHelper, EntityPlayer

public class BlockFenceGate extends Block
{

    public BlockFenceGate(int i, int j)
    {
        super(i, j, Material.wood);
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        if(!world.getBlockMaterial(i, j - 1, k).isSolid())
        {
            return false;
        } else
        {
            return super.canPlaceBlockAt(world, i, j, k);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        if(func_35070_c(l))
        {
            return null;
        } else
        {
            return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)j + 1.5F, k + 1);
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
        return 21;
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = (MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
        world.setBlockMetadataWithNotify(i, j, k, l);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        int l = world.getBlockMetadata(i, j, k);
        if(func_35070_c(l))
        {
            world.setBlockMetadataWithNotify(i, j, k, l & -5);
        } else
        {
            int i1 = (MathHelper.floor_double((double)((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
            int j1 = func_35071_d(l);
            if(j1 == (i1 + 2) % 4)
            {
                l = i1;
            }
            world.setBlockMetadataWithNotify(i, j, k, l | 4);
        }
        world.playAuxSFXAtEntity(entityplayer, 1003, i, j, k, 0);
        return true;
    }

    public static boolean func_35070_c(int i)
    {
        return (i & 4) != 0;
    }

    public static int func_35071_d(int i)
    {
        return i & 3;
    }
}
