// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityPlayer, PlayerCapabilities, World, Block, 
//            EntityPlayerMP, Packet53BlockChange, NetServerHandler, ItemStack, 
//            InventoryPlayer, WorldServer

public class ItemInWorldManager
{

    public World thisWorld;
    public EntityPlayer thisPlayer;
    private int gameType;
    private float field_672_d;
    private int initialDamage;
    private int curBlockX;
    private int curBlockY;
    private int curBlockZ;
    private int curblockDamage;
    private boolean field_22050_k;
    private int field_22049_l;
    private int field_22048_m;
    private int field_22047_n;
    private int field_22046_o;

    public ItemInWorldManager(World world)
    {
        gameType = -1;
        field_672_d = 0.0F;
        thisWorld = world;
    }

    public void toggleGameType(int i)
    {
        gameType = i;
        if(i == 0)
        {
            thisPlayer.field_35214_K.allowFlying = false;
            thisPlayer.field_35214_K.isFlying = false;
            thisPlayer.field_35214_K.depleteBuckets = false;
            thisPlayer.field_35214_K.disableDamage = false;
        } else
        {
            thisPlayer.field_35214_K.allowFlying = true;
            thisPlayer.field_35214_K.depleteBuckets = true;
            thisPlayer.field_35214_K.disableDamage = true;
        }
    }

    public int getGameType()
    {
        return gameType;
    }

    public boolean isCreative()
    {
        return gameType == 1;
    }

    public void func_35695_b(int i)
    {
        if(gameType == -1)
        {
            gameType = i;
        }
        toggleGameType(gameType);
    }

    public void updateBlockRemoving()
    {
        curblockDamage++;
        if(field_22050_k)
        {
            int i = curblockDamage - field_22046_o;
            int j = thisWorld.getBlockId(field_22049_l, field_22048_m, field_22047_n);
            if(j != 0)
            {
                Block block = Block.blocksList[j];
                float f = block.blockStrength(thisPlayer) * (float)(i + 1);
                if(f >= 1.0F)
                {
                    field_22050_k = false;
                    blockHarvessted(field_22049_l, field_22048_m, field_22047_n);
                }
            } else
            {
                field_22050_k = false;
            }
        }
    }

    public void blockClicked(int i, int j, int k, int l)
    {
        thisWorld.onBlockHit(null, i, j, k, l);
        if(isCreative())
        {
            blockHarvessted(i, j, k);
            return;
        }
        initialDamage = curblockDamage;
        int i1 = thisWorld.getBlockId(i, j, k);
        if(i1 > 0)
        {
            Block.blocksList[i1].onBlockClicked(thisWorld, i, j, k, thisPlayer);
        }
        if(i1 > 0 && Block.blocksList[i1].blockStrength(thisPlayer) >= 1.0F)
        {
            blockHarvessted(i, j, k);
        } else
        {
            curBlockX = i;
            curBlockY = j;
            curBlockZ = k;
        }
    }

    public void blockRemoving(int i, int j, int k)
    {
        if(i == curBlockX && j == curBlockY && k == curBlockZ)
        {
            int l = curblockDamage - initialDamage;
            int i1 = thisWorld.getBlockId(i, j, k);
            if(i1 != 0)
            {
                Block block = Block.blocksList[i1];
                float f = block.blockStrength(thisPlayer) * (float)(l + 1);
                if(f >= 0.7F)
                {
                    blockHarvessted(i, j, k);
                } else
                if(!field_22050_k)
                {
                    field_22050_k = true;
                    field_22049_l = i;
                    field_22048_m = j;
                    field_22047_n = k;
                    field_22046_o = initialDamage;
                }
            }
        }
        field_672_d = 0.0F;
    }

    public boolean removeBlock(int i, int j, int k)
    {
        Block block = Block.blocksList[thisWorld.getBlockId(i, j, k)];
        int l = thisWorld.getBlockMetadata(i, j, k);
        boolean flag = thisWorld.setBlockWithNotify(i, j, k, 0);
        if(block != null && flag)
        {
            block.onBlockDestroyedByPlayer(thisWorld, i, j, k, l);
        }
        return flag;
    }

    public boolean blockHarvessted(int i, int j, int k)
    {
        int l = thisWorld.getBlockId(i, j, k);
        int i1 = thisWorld.getBlockMetadata(i, j, k);
        thisWorld.playAuxSFXAtEntity(thisPlayer, 2001, i, j, k, l + thisWorld.getBlockMetadata(i, j, k) * 256);
        boolean flag = removeBlock(i, j, k);
        if(isCreative())
        {
            ((EntityPlayerMP)thisPlayer).playerNetServerHandler.sendPacket(new Packet53BlockChange(i, j, k, thisWorld));
        } else
        {
            ItemStack itemstack = thisPlayer.getCurrentEquippedItem();
            boolean flag1 = thisPlayer.canHarvestBlock(Block.blocksList[l]);
            if(itemstack != null)
            {
                itemstack.onDestroyBlock(l, i, j, k, thisPlayer);
                if(itemstack.stackSize == 0)
                {
                    itemstack.onItemDestroyedByUse(thisPlayer);
                    thisPlayer.destroyCurrentEquippedItem();
                }
            }
            if(flag && flag1)
            {
                Block.blocksList[l].harvestBlock(thisWorld, thisPlayer, i, j, k, i1);
            }
        }
        return flag;
    }

    public boolean itemUsed(EntityPlayer entityplayer, World world, ItemStack itemstack)
    {
        int i = itemstack.stackSize;
        int j = itemstack.getItemDamage();
        ItemStack itemstack1 = itemstack.useItemRightClick(world, entityplayer);
        if(itemstack1 != itemstack || itemstack1 != null && itemstack1.stackSize != i || itemstack1 != null && itemstack1.func_35614_l() > 0)
        {
            entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = itemstack1;
            if(isCreative())
            {
                itemstack1.stackSize = i;
                itemstack1.setItemDamage(j);
            }
            if(itemstack1.stackSize == 0)
            {
                entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem] = null;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean activeBlockOrUseItem(EntityPlayer entityplayer, World world, ItemStack itemstack, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        if(i1 > 0 && Block.blocksList[i1].blockActivated(world, i, j, k, entityplayer))
        {
            return true;
        }
        if(itemstack == null)
        {
            return false;
        }
        if(isCreative())
        {
            int j1 = itemstack.getItemDamage();
            int k1 = itemstack.stackSize;
            boolean flag = itemstack.useItem(entityplayer, world, i, j, k, l);
            itemstack.setItemDamage(j1);
            itemstack.stackSize = k1;
            return flag;
        } else
        {
            return itemstack.useItem(entityplayer, world, i, j, k, l);
        }
    }

    public void setWorld(WorldServer worldserver)
    {
        thisWorld = worldserver;
    }
}
