// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockFlower, Block, World, IBlockAccess, 
//            Item, EntityItem, ItemStack

public class BlockStem extends BlockFlower
{

    private Block fruitType;

    protected BlockStem(int i, Block block)
    {
        super(i, 111);
        fruitType = block;
        setTickOnLoad(true);
        float f = 0.125F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    protected boolean canThisPlantGrowOnThisBlockID(int i)
    {
        return i == Block.tilledField.blockID;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        super.updateTick(world, i, j, k, random);
        if(world.getBlockLightValue(i, j + 1, k) >= 9)
        {
            float f = func_35067_h(world, i, j, k);
            if(random.nextInt((int)(25F / f) + 1) == 0)
            {
                int l = world.getBlockMetadata(i, j, k);
                if(l < 7)
                {
                    l++;
                    world.setBlockMetadataWithNotify(i, j, k, l);
                } else
                {
                    if(world.getBlockId(i - 1, j, k) == fruitType.blockID)
                    {
                        return;
                    }
                    if(world.getBlockId(i + 1, j, k) == fruitType.blockID)
                    {
                        return;
                    }
                    if(world.getBlockId(i, j, k - 1) == fruitType.blockID)
                    {
                        return;
                    }
                    if(world.getBlockId(i, j, k + 1) == fruitType.blockID)
                    {
                        return;
                    }
                    int i1 = random.nextInt(4);
                    int j1 = i;
                    int k1 = k;
                    if(i1 == 0)
                    {
                        j1--;
                    }
                    if(i1 == 1)
                    {
                        j1++;
                    }
                    if(i1 == 2)
                    {
                        k1--;
                    }
                    if(i1 == 3)
                    {
                        k1++;
                    }
                    if(world.getBlockId(j1, j, k1) == 0 && world.getBlockId(j1, j - 1, k1) == Block.tilledField.blockID)
                    {
                        world.setBlockWithNotify(j1, j, k1, fruitType.blockID);
                    }
                }
            }
        }
    }

    public void func_35066_f_(World world, int i, int j, int k)
    {
        world.setBlockMetadataWithNotify(i, j, k, 7);
    }

    private float func_35067_h(World world, int i, int j, int k)
    {
        float f = 1.0F;
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        int l1 = world.getBlockId(i - 1, j, k - 1);
        int i2 = world.getBlockId(i + 1, j, k - 1);
        int j2 = world.getBlockId(i + 1, j, k + 1);
        int k2 = world.getBlockId(i - 1, j, k + 1);
        boolean flag = j1 == blockID || k1 == blockID;
        boolean flag1 = l == blockID || i1 == blockID;
        boolean flag2 = l1 == blockID || i2 == blockID || j2 == blockID || k2 == blockID;
        for(int l2 = i - 1; l2 <= i + 1; l2++)
        {
            for(int i3 = k - 1; i3 <= k + 1; i3++)
            {
                int j3 = world.getBlockId(l2, j - 1, i3);
                float f1 = 0.0F;
                if(j3 == Block.tilledField.blockID)
                {
                    f1 = 1.0F;
                    if(world.getBlockMetadata(l2, j - 1, i3) > 0)
                    {
                        f1 = 3F;
                    }
                }
                if(l2 != i || i3 != k)
                {
                    f1 /= 4F;
                }
                f += f1;
            }

        }

        if(flag2 || flag && flag1)
        {
            f /= 2.0F;
        }
        return f;
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return blockIndexInTexture;
    }

    public void func_40163_f()
    {
        float f = 0.125F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        maxY = (float)(iblockaccess.getBlockMetadata(i, j, k) * 2 + 2) / 16F;
        float f = 0.125F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)maxY, 0.5F + f);
    }

    public int getRenderType()
    {
        return 19;
    }

    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f, int i1)
    {
        super.dropBlockAsItemWithChance(world, i, j, k, l, f, i1);
        if(world.singleplayerWorld)
        {
            return;
        }
        Item item = null;
        if(fruitType == Block.pumpkin)
        {
            item = Item.pumpkinSeeds;
        }
        if(fruitType == Block.melon)
        {
            item = Item.melonSeeds;
        }
        for(int j1 = 0; j1 < 3; j1++)
        {
            if(world.rand.nextInt(15) <= l)
            {
                float f1 = 0.7F;
                float f2 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                float f3 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                float f4 = world.rand.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                EntityItem entityitem = new EntityItem(world, (float)i + f2, (float)j + f3, (float)k + f4, new ItemStack(item));
                entityitem.delayBeforeCanPickup = 10;
                world.entityJoinedWorld(entityitem);
            }
        }

    }

    public int idDropped(int i, Random random, int j)
    {
        if(i != 7);
        return -1;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }
}
