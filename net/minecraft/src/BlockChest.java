// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, World, EntityLiving, 
//            MathHelper, Block, TileEntityChest, IInventory, 
//            ItemStack, EntityItem, NBTTagCompound, InventoryLargeChest, 
//            EntityPlayer, TileEntity

public class BlockChest extends BlockContainer
{

    private Random random;

    protected BlockChest(int i)
    {
        super(i, Material.wood);
        random = new Random();
        blockIndexInTexture = 26;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 22;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        unifyAdjacentChests(world, i, j, k);
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        if(l == blockID)
        {
            unifyAdjacentChests(world, i, j, k - 1);
        }
        if(i1 == blockID)
        {
            unifyAdjacentChests(world, i, j, k + 1);
        }
        if(j1 == blockID)
        {
            unifyAdjacentChests(world, i - 1, j, k);
        }
        if(k1 == blockID)
        {
            unifyAdjacentChests(world, i + 1, j, k);
        }
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 0;
        int l1 = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l1 == 0)
        {
            byte0 = 2;
        }
        if(l1 == 1)
        {
            byte0 = 5;
        }
        if(l1 == 2)
        {
            byte0 = 3;
        }
        if(l1 == 3)
        {
            byte0 = 4;
        }
        if(l != blockID && i1 != blockID && j1 != blockID && k1 != blockID)
        {
            world.setBlockMetadataWithNotify(i, j, k, byte0);
        } else
        {
            if((l == blockID || i1 == blockID) && (byte0 == 4 || byte0 == 5))
            {
                if(l == blockID)
                {
                    world.setBlockMetadataWithNotify(i, j, k - 1, byte0);
                } else
                {
                    world.setBlockMetadataWithNotify(i, j, k + 1, byte0);
                }
                world.setBlockMetadataWithNotify(i, j, k, byte0);
            }
            if((j1 == blockID || k1 == blockID) && (byte0 == 2 || byte0 == 3))
            {
                if(j1 == blockID)
                {
                    world.setBlockMetadataWithNotify(i - 1, j, k, byte0);
                } else
                {
                    world.setBlockMetadataWithNotify(i + 1, j, k, byte0);
                }
                world.setBlockMetadataWithNotify(i, j, k, byte0);
            }
        }
    }

    public void unifyAdjacentChests(World world, int i, int j, int k)
    {
        if(world.singleplayerWorld)
        {
            return;
        }
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 4;
        if(l == blockID || i1 == blockID)
        {
            int l1 = world.getBlockId(i - 1, j, l != blockID ? k + 1 : k - 1);
            int j2 = world.getBlockId(i + 1, j, l != blockID ? k + 1 : k - 1);
            byte0 = 5;
            int l2 = -1;
            if(l == blockID)
            {
                l2 = world.getBlockMetadata(i, j, k - 1);
            } else
            {
                l2 = world.getBlockMetadata(i, j, k + 1);
            }
            if(l2 == 4)
            {
                byte0 = 4;
            }
            if((Block.opaqueCubeLookup[j1] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j2])
            {
                byte0 = 5;
            }
            if((Block.opaqueCubeLookup[k1] || Block.opaqueCubeLookup[j2]) && !Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[l1])
            {
                byte0 = 4;
            }
        } else
        if(j1 == blockID || k1 == blockID)
        {
            int i2 = world.getBlockId(j1 != blockID ? i + 1 : i - 1, j, k - 1);
            int k2 = world.getBlockId(j1 != blockID ? i + 1 : i - 1, j, k + 1);
            byte0 = 3;
            int i3 = -1;
            if(j1 == blockID)
            {
                i3 = world.getBlockMetadata(i - 1, j, k);
            } else
            {
                i3 = world.getBlockMetadata(i + 1, j, k);
            }
            if(i3 == 2)
            {
                byte0 = 2;
            }
            if((Block.opaqueCubeLookup[l] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[k2])
            {
                byte0 = 3;
            }
            if((Block.opaqueCubeLookup[i1] || Block.opaqueCubeLookup[k2]) && !Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i2])
            {
                byte0 = 2;
            }
        } else
        {
            byte0 = 3;
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
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture - 1;
        }
        if(i == 0)
        {
            return blockIndexInTexture - 1;
        }
        if(i == 3)
        {
            return blockIndexInTexture + 1;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        int l = 0;
        if(world.getBlockId(i - 1, j, k) == blockID)
        {
            l++;
        }
        if(world.getBlockId(i + 1, j, k) == blockID)
        {
            l++;
        }
        if(world.getBlockId(i, j, k - 1) == blockID)
        {
            l++;
        }
        if(world.getBlockId(i, j, k + 1) == blockID)
        {
            l++;
        }
        if(l > 1)
        {
            return false;
        }
        if(isThereANeighborChest(world, i - 1, j, k))
        {
            return false;
        }
        if(isThereANeighborChest(world, i + 1, j, k))
        {
            return false;
        }
        if(isThereANeighborChest(world, i, j, k - 1))
        {
            return false;
        }
        return !isThereANeighborChest(world, i, j, k + 1);
    }

    private boolean isThereANeighborChest(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return false;
        }
        if(world.getBlockId(i - 1, j, k) == blockID)
        {
            return true;
        }
        if(world.getBlockId(i + 1, j, k) == blockID)
        {
            return true;
        }
        if(world.getBlockId(i, j, k - 1) == blockID)
        {
            return true;
        }
        return world.getBlockId(i, j, k + 1) == blockID;
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        super.onNeighborBlockChange(world, i, j, k, l);
        TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(i, j, k);
        if(tileentitychest != null)
        {
            tileentitychest.func_35164_g();
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(i, j, k);
        if(tileentitychest != null)
        {
            for(int l = 0; l < tileentitychest.getSizeInventory(); l++)
            {
                ItemStack itemstack = tileentitychest.getStackInSlot(l);
                if(itemstack == null)
                {
                    continue;
                }
                float f = random.nextFloat() * 0.8F + 0.1F;
                float f1 = random.nextFloat() * 0.8F + 0.1F;
                float f2 = random.nextFloat() * 0.8F + 0.1F;
                while(itemstack.stackSize > 0) 
                {
                    int i1 = random.nextInt(21) + 10;
                    if(i1 > itemstack.stackSize)
                    {
                        i1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float)random.nextGaussian() * f3;
                    entityitem.motionY = (float)random.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float)random.nextGaussian() * f3;
                    if(itemstack.hasTagCompound())
                    {
                        entityitem.item.setNBTData((NBTTagCompound)itemstack.getTagCompound().cloneTag());
                    }
                    world.spawnEntityInWorld(entityitem);
                }
            }

        }
        super.onBlockRemoval(world, i, j, k);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        Object obj = (TileEntityChest)world.getBlockTileEntity(i, j, k);
        if(obj == null)
        {
            return true;
        }
        if(world.isBlockNormalCube(i, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i - 1, j, k) == blockID && world.isBlockNormalCube(i - 1, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i + 1, j, k) == blockID && world.isBlockNormalCube(i + 1, j + 1, k))
        {
            return true;
        }
        if(world.getBlockId(i, j, k - 1) == blockID && world.isBlockNormalCube(i, j + 1, k - 1))
        {
            return true;
        }
        if(world.getBlockId(i, j, k + 1) == blockID && world.isBlockNormalCube(i, j + 1, k + 1))
        {
            return true;
        }
        if(world.getBlockId(i - 1, j, k) == blockID)
        {
            obj = new InventoryLargeChest("Large chest", (TileEntityChest)world.getBlockTileEntity(i - 1, j, k), ((IInventory) (obj)));
        }
        if(world.getBlockId(i + 1, j, k) == blockID)
        {
            obj = new InventoryLargeChest("Large chest", ((IInventory) (obj)), (TileEntityChest)world.getBlockTileEntity(i + 1, j, k));
        }
        if(world.getBlockId(i, j, k - 1) == blockID)
        {
            obj = new InventoryLargeChest("Large chest", (TileEntityChest)world.getBlockTileEntity(i, j, k - 1), ((IInventory) (obj)));
        }
        if(world.getBlockId(i, j, k + 1) == blockID)
        {
            obj = new InventoryLargeChest("Large chest", ((IInventory) (obj)), (TileEntityChest)world.getBlockTileEntity(i, j, k + 1));
        }
        if(world.singleplayerWorld)
        {
            return true;
        } else
        {
            entityplayer.displayGUIChest(((IInventory) (obj)));
            return true;
        }
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityChest();
    }
}
