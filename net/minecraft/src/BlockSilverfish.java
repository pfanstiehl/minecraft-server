// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, EntitySilverfish, 
//            ItemStack, EntityPlayer

public class BlockSilverfish extends Block
{

    public BlockSilverfish(int i)
    {
        super(i, 1, Material.clay);
        setHardness(0.0F);
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
        super.harvestBlock(world, entityplayer, i, j, k, l);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(j == 1)
        {
            return Block.cobblestone.blockIndexInTexture;
        }
        if(j == 2)
        {
            return Block.stoneBrick.blockIndexInTexture;
        } else
        {
            return Block.stone.blockIndexInTexture;
        }
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(!world.singleplayerWorld)
        {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);
            entitysilverfish.setLocationAndAngles((double)i + 0.5D, j, (double)k + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(entitysilverfish);
            entitysilverfish.spawnExplosionParticle();
        }
        super.onBlockDestroyedByPlayer(world, i, j, k, l);
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    public static boolean getPosingIdByMetadata(int i)
    {
        return i == Block.stone.blockID || i == Block.cobblestone.blockID || i == Block.stoneBrick.blockID;
    }

    public static int func_35061_d(int i)
    {
        if(i == Block.cobblestone.blockID)
        {
            return 1;
        }
        return i != Block.stoneBrick.blockID ? 0 : 2;
    }

    protected ItemStack func_41001_e(int i)
    {
        Block block = Block.stone;
        if(i == 1)
        {
            block = Block.cobblestone;
        }
        if(i == 2)
        {
            block = Block.stoneBrick;
        }
        return new ItemStack(block);
    }
}
