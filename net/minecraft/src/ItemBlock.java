// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, Block, World, ItemStack, 
//            EntityPlayer, Material, StepSound

public class ItemBlock extends Item
{

    private int blockID;

    public ItemBlock(int i)
    {
        super(i);
        blockID = i + 256;
        setIconIndex(Block.blocksList[i + 256].getBlockTextureFromSide(2));
    }

    public int getBlockID()
    {
        return blockID;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        if(i1 == Block.snow.blockID)
        {
            l = 0;
        } else
        if(i1 != Block.vine.blockID)
        {
            if(l == 0)
            {
                j--;
            }
            if(l == 1)
            {
                j++;
            }
            if(l == 2)
            {
                k--;
            }
            if(l == 3)
            {
                k++;
            }
            if(l == 4)
            {
                i--;
            }
            if(l == 5)
            {
                i++;
            }
        }
        if(itemstack.stackSize == 0)
        {
            return false;
        }
        if(!entityplayer.canPlayerEdit(i, j, k))
        {
            return false;
        }
        if(j == world.worldYMax - 1 && Block.blocksList[blockID].blockMaterial.isSolid())
        {
            return false;
        }
        if(world.canBlockBePlacedAt(blockID, i, j, k, false, l))
        {
            Block block = Block.blocksList[blockID];
            if(world.setBlockAndMetadataWithNotify(i, j, k, blockID, getMetadata(itemstack.getItemDamage())))
            {
                if(world.getBlockId(i, j, k) == blockID)
                {
                    Block.blocksList[blockID].onBlockPlaced(world, i, j, k, l);
                    Block.blocksList[blockID].onBlockPlacedBy(world, i, j, k, entityplayer);
                }
                world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.stepSoundDir(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                itemstack.stackSize--;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public String func_35407_a(ItemStack itemstack)
    {
        return Block.blocksList[blockID].getBlockName();
    }

    public String getItemName()
    {
        return Block.blocksList[blockID].getBlockName();
    }
}
