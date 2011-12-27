// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockFlower, World, Block, BlockMycelium, 
//            WorldGenBigMushroom, WorldGenerator

public class BlockMushroom extends BlockFlower
{

    protected BlockMushroom(int i, int j)
    {
        super(i, j);
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setTickOnLoad(true);
    }

    
    // An update will randomly cause a mushroom to propagate if the number of mushrooms nearby does not
    // exceed a certain limit and if the randomly picked location to propagate to can support
    // a mushroom.
    public void updateTick(World world, int x, int y, int z, Random random)
    {
    	
    	// Occasionally attempt to propagate.
        if(random.nextInt(25) == 0)
        {
        	
        	// Fail to propagate if there are 5 mushrooms in an axis alligned box with dimensions
        	// 9x3x9 centred at this mushroom.
            byte radius = 4;
            int maxMushrooms = 5;
            for(int i = x - radius; i <= x + radius; i++)
            {
                for(int j = z - radius; j <= z + radius; j++)
                {
                    for(int k = y - 1; k <= y + 1; k++)
                    {
                        if(world.getBlockId(i, k, j) == blockID && --maxMushrooms <= 0)
                        {
                            return;
                        }
                    }

                }

            }

            // Check if a random position in an axis aligned box with dimensions 3x3x3 centred
            // on this mushroom can support a mushroom. Note that the vertical position will be on same
            // level as this mushroom half the time on average. If the position can support a mushroom,
            // use it as the new base for subsequent tries. Repeat this 5 times.
            int xCandidate = (x + random.nextInt(3)) - 1;
            int yCandidate = (y + random.nextInt(2)) - random.nextInt(2);
            int zCandidate = (z + random.nextInt(3)) - 1;
            for(int i = 0; i < 4; i++)
            {
                if(world.isAirBlock(xCandidate, yCandidate, zCandidate) && canBlockStay(world, xCandidate, yCandidate, zCandidate))
                {
                    x = xCandidate;
                    y = yCandidate;
                    z = zCandidate;
                }
                xCandidate = (x + random.nextInt(3)) - 1;
                yCandidate = (y + random.nextInt(2)) - random.nextInt(2);
                zCandidate = (z + random.nextInt(3)) - 1;
            }

            // Place mushroom if final position can support it.
            if(world.isAirBlock(xCandidate, yCandidate, zCandidate) && canBlockStay(world, xCandidate, yCandidate, zCandidate))
            {
                world.setBlockWithNotify(xCandidate, yCandidate, zCandidate, blockID);
            }
        }
    }

    protected boolean canThisPlantGrowOnThisBlockID(int i)
    {
        return Block.opaqueCubeLookup[i];
    }

    // Can this position support mushroom?
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	// Can't exist below or above limits of world that allow block placement.
        if(y < 0 || y >= world.worldYMax)
        {
            return false;
        }
        // Can exist on mycelium block under any conditions. Otherwise, light level must be below a
        // threshold and supporting block must be appropriate type.
        else
        {
            int supportingBlock = world.getBlockId(x, y - 1, z);
            return supportingBlock == Block.mycelium.blockID || world.getFullBlockLightValue(x, y, z) < 13 && canThisPlantGrowOnThisBlockID(supportingBlock);
        }
    }

    public boolean fertilizeMushroom(World world, int i, int j, int k, Random random)
    {
        int l = world.getBlockMetadata(i, j, k);
        world.setBlock(i, j, k, 0);
        WorldGenBigMushroom worldgenbigmushroom = null;
        if(blockID == Block.mushroomBrown.blockID)
        {
            worldgenbigmushroom = new WorldGenBigMushroom(0);
        } else
        if(blockID == Block.mushroomRed.blockID)
        {
            worldgenbigmushroom = new WorldGenBigMushroom(1);
        }
        if(worldgenbigmushroom == null || !worldgenbigmushroom.generate(world, random, i, j, k))
        {
            world.setBlockAndMetadata(i, j, k, blockID, l);
            return false;
        } else
        {
            return true;
        }
    }
}
