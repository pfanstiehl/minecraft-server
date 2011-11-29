// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WorldProvider, WorldChunkManagerHell, BiomeGenBase, ChunkProviderHell, 
//            World, Block, IChunkProvider

public class WorldProviderHell extends WorldProvider
{

    public WorldProviderHell()
    {
    }

    public void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
        canSleepInWorld = true;
        isHellWorld = true;
        hasNoSky = true;
        worldType = -1;
    }

    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;
        for(int i = 0; i <= 15; i++)
        {
            float f1 = 1.0F - (float)i / 15F;
            lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }

    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderHell(worldObj, worldObj.getRandomSeed());
    }

    public boolean canCoordinateBeSpawn(int i, int j)
    {
        int k = worldObj.getFirstUncoveredBlock(i, j);
        if(k == Block.bedrock.blockID)
        {
            return false;
        }
        if(k == 0)
        {
            return false;
        }
        return Block.opaqueCubeLookup[k];
    }

    public float calculateCelestialAngle(long l, float f)
    {
        return 0.5F;
    }

    public boolean canRespawnHere()
    {
        return false;
    }
}
