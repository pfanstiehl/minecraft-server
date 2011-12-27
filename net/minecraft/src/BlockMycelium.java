// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World

public class BlockMycelium extends Block
{

	protected BlockMycelium(int i)
	{
		super(i, Material.grass);
		blockIndexInTexture = 77;
		setTickOnLoad(true);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j)
	{
		if(i == 1)
		{
			return 78;
		}
		return i != 0 ? 77 : 2;
	}

	// An update can cause the mycelium block to become dirt or to attempt to spread depending on certain
	// conditions.
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(world.singleplayerWorld)
		{
			return;
		}

		// Transform this mycelium block into dirt if light level too low.
		if(world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2)
		{
			world.setBlockWithNotify(x, y, z, Block.dirt.blockID);
		} 
		// If light level high enough, attempt to spread 4 times. Can spread to blocks in an axis aligned
		// box with dimensions 3x4x3 centred horizontally at this block and vertically the upper edge is
		// one block higher than this block.
		else if(world.getBlockLightValue(x, y + 1, z) >= 9)
		{
			for(int i = 0; i < 4; i++)
			{
				int xCandidate = (x + random.nextInt(3)) - 1;
				int yCandidate = (y + random.nextInt(5)) - 3;
				int zCandidate = (z + random.nextInt(3)) - 1;
				int blockCandidateID = world.getBlockId(xCandidate, yCandidate + 1, zCandidate);
				if(world.getBlockId(xCandidate, yCandidate, zCandidate) == Block.dirt.blockID && world.getBlockLightValue(xCandidate, yCandidate + 1, zCandidate) >= 4 && Block.lightOpacity[blockCandidateID] <= 2)
				{
					world.setBlockWithNotify(xCandidate, yCandidate, zCandidate, blockID);
				}
			}

		}
	}

	public int idDropped(int i, Random random, int j)
	{
		return Block.dirt.idDropped(0, random, j);
	}
}
