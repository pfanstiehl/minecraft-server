// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            TileEntity, Block, BlockPistonMoving, World, 
//            Entity, Facing, NBTTagCompound

public class TileEntityPiston extends TileEntity
{

    private int storedBlockID;
    private int storedMetadata;
    private int storedOrientation;
    private boolean extending;
    private boolean field_31018_j;
    private float progress;
    private float lastProgress;
    private static List field_31013_m = new ArrayList();

    public TileEntityPiston()
    {
    }

    public TileEntityPiston(int i, int j, int k, boolean flag, boolean flag1)
    {
        storedBlockID = i;
        storedMetadata = j;
        storedOrientation = k;
        extending = flag;
        field_31018_j = flag1;
    }

    public int getStoredBlockID()
    {
        return storedBlockID;
    }

    public int getBlockMetadata()
    {
        return storedMetadata;
    }

    public boolean func_31010_c()
    {
        return extending;
    }

    public int func_31008_d()
    {
        return storedOrientation;
    }

    public float func_31007_a(float f)
    {
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        return lastProgress + (progress - lastProgress) * f;
    }

    private void func_31009_a(float f, float f1)
    {
        if(!extending)
        {
            f--;
        } else
        {
            f = 1.0F - f;
        }
        AxisAlignedBB axisalignedbb = Block.pistonMoving.getAxisAlignedBB(worldObj, xCoord, yCoord, zCoord, storedBlockID, f, storedOrientation);
        if(axisalignedbb != null)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb);
            if(!list.isEmpty())
            {
                field_31013_m.addAll(list);
                Entity entity;
                for(Iterator iterator = field_31013_m.iterator(); iterator.hasNext(); entity.moveEntity(f1 * (float)Facing.offsetsXForSide[storedOrientation], f1 * (float)Facing.offsetsYForSide[storedOrientation], f1 * (float)Facing.offsetsZForSide[storedOrientation]))
                {
                    entity = (Entity)iterator.next();
                }

                field_31013_m.clear();
            }
        }
    }

    public void clearPistonTileEntity()
    {
        if(lastProgress < 1.0F && worldObj != null)
        {
            lastProgress = progress = 1.0F;
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
            invalidate();
            if(worldObj.getBlockId(xCoord, yCoord, zCoord) == Block.pistonMoving.blockID)
            {
                worldObj.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, storedBlockID, storedMetadata);
            }
        }
    }

    public void updateEntity()
    {
        lastProgress = progress;
        if(lastProgress >= 1.0F)
        {
            func_31009_a(1.0F, 0.25F);
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
            invalidate();
            if(worldObj.getBlockId(xCoord, yCoord, zCoord) == Block.pistonMoving.blockID)
            {
                worldObj.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, storedBlockID, storedMetadata);
            }
            return;
        }
        progress += 0.5F;
        if(progress >= 1.0F)
        {
            progress = 1.0F;
        }
        if(extending)
        {
            func_31009_a(progress, (progress - lastProgress) + 0.0625F);
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        storedBlockID = nbttagcompound.getInteger("blockId");
        storedMetadata = nbttagcompound.getInteger("blockData");
        storedOrientation = nbttagcompound.getInteger("facing");
        lastProgress = progress = nbttagcompound.getFloat("progress");
        extending = nbttagcompound.getBoolean("extending");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("blockId", storedBlockID);
        nbttagcompound.setInteger("blockData", storedMetadata);
        nbttagcompound.setInteger("facing", storedOrientation);
        nbttagcompound.setFloat("progress", lastProgress);
        nbttagcompound.setBoolean("extending", extending);
    }

}
