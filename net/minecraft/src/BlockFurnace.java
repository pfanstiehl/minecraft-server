// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, Block, World, 
//            TileEntityFurnace, EntityPlayer, TileEntity, EntityLiving, 
//            MathHelper, IInventory, ItemStack, EntityItem

public class BlockFurnace extends BlockContainer
{

    private Random furnaceRand;
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;

    protected BlockFurnace(int i, boolean flag)
    {
        super(i, Material.rock);
        furnaceRand = new Random();
        isActive = flag;
        blockIndexInTexture = 45;
    }

    public int idDropped(int i, Random random, int j)
    {
        return Block.stoneOvenIdle.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        if(world.singleplayerWorld)
        {
            return;
        }
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }
        if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }
        if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }
        if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture + 17;
        }
        if(i == 0)
        {
            return blockIndexInTexture + 17;
        }
        if(i == 3)
        {
            return blockIndexInTexture - 1;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.singleplayerWorld)
        {
            return true;
        }
        TileEntityFurnace tileentityfurnace = (TileEntityFurnace)world.getBlockTileEntity(i, j, k);
        if(tileentityfurnace != null)
        {
            entityplayer.displayGUIFurnace(tileentityfurnace);
        }
        return true;
    }

    public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepFurnaceInventory = true;
        if(flag)
        {
            world.setBlockWithNotify(i, j, k, Block.stoneOvenActive.blockID);
        } else
        {
            world.setBlockWithNotify(i, j, k, Block.stoneOvenIdle.blockID);
        }
        keepFurnaceInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l);
        if(tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityFurnace();
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if(!keepFurnaceInventory)
        {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace)world.getBlockTileEntity(i, j, k);
            if(tileentityfurnace != null)
            {
label0:
                for(int l = 0; l < tileentityfurnace.getSizeInventory(); l++)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(l);
                    if(itemstack == null)
                    {
                        continue;
                    }
                    float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;
                    do
                    {
                        if(itemstack.stackSize <= 0)
                        {
                            continue label0;
                        }
                        int i1 = furnaceRand.nextInt(21) + 10;
                        if(i1 > itemstack.stackSize)
                        {
                            i1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (float)furnaceRand.nextGaussian() * f3;
                        entityitem.motionY = (float)furnaceRand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)furnaceRand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    } while(true);
                }

            }
        }
        super.onBlockRemoval(world, i, j, k);
    }

}
