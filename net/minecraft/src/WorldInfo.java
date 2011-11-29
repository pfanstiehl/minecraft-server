// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            NBTTagCompound, WorldSettings, EntityPlayer

public class WorldInfo
{

    private long randomSeed;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long worldTime;
    private long lastTimePlayed;
    private long sizeOnDisk;
    private NBTTagCompound playerTag;
    private int dimension;
    private String levelName;
    private int saveVersion;
    private boolean raining;
    private int rainTime;
    private boolean thundering;
    private int thunderTime;
    private int gameType;
    private boolean enableMapFeatures;
    private boolean hardcore;

    public WorldInfo(NBTTagCompound nbttagcompound)
    {
        hardcore = false;
        randomSeed = nbttagcompound.getLong("RandomSeed");
        gameType = nbttagcompound.getInteger("GameType");
        if(nbttagcompound.hasKey("MapFeatures"))
        {
            enableMapFeatures = nbttagcompound.getBoolean("MapFeatures");
        } else
        {
            enableMapFeatures = true;
        }
        spawnX = nbttagcompound.getInteger("SpawnX");
        spawnY = nbttagcompound.getInteger("SpawnY");
        spawnZ = nbttagcompound.getInteger("SpawnZ");
        worldTime = nbttagcompound.getLong("Time");
        lastTimePlayed = nbttagcompound.getLong("LastPlayed");
        sizeOnDisk = nbttagcompound.getLong("SizeOnDisk");
        levelName = nbttagcompound.getString("LevelName");
        saveVersion = nbttagcompound.getInteger("version");
        rainTime = nbttagcompound.getInteger("rainTime");
        raining = nbttagcompound.getBoolean("raining");
        thunderTime = nbttagcompound.getInteger("thunderTime");
        thundering = nbttagcompound.getBoolean("thundering");
        hardcore = nbttagcompound.getBoolean("hardcore");
        if(nbttagcompound.hasKey("Player"))
        {
            playerTag = nbttagcompound.getCompoundTag("Player");
            dimension = playerTag.getInteger("Dimension");
        }
    }

    public WorldInfo(WorldSettings worldsettings, String s)
    {
        hardcore = false;
        randomSeed = worldsettings.getSeed();
        gameType = worldsettings.getGameType();
        enableMapFeatures = worldsettings.getMapFeaturesEnabled();
        levelName = s;
        hardcore = worldsettings.getHardcoreEnabled();
    }

    public WorldInfo(WorldInfo worldinfo)
    {
        hardcore = false;
        randomSeed = worldinfo.randomSeed;
        gameType = worldinfo.gameType;
        enableMapFeatures = worldinfo.enableMapFeatures;
        spawnX = worldinfo.spawnX;
        spawnY = worldinfo.spawnY;
        spawnZ = worldinfo.spawnZ;
        worldTime = worldinfo.worldTime;
        lastTimePlayed = worldinfo.lastTimePlayed;
        sizeOnDisk = worldinfo.sizeOnDisk;
        playerTag = worldinfo.playerTag;
        dimension = worldinfo.dimension;
        levelName = worldinfo.levelName;
        saveVersion = worldinfo.saveVersion;
        rainTime = worldinfo.rainTime;
        raining = worldinfo.raining;
        thunderTime = worldinfo.thunderTime;
        thundering = worldinfo.thundering;
        hardcore = worldinfo.hardcore;
    }

    public NBTTagCompound getNBTTagCompound()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        saveNBTTag(nbttagcompound, playerTag);
        return nbttagcompound;
    }

    public NBTTagCompound storeLevelDataToNBT(List list)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        EntityPlayer entityplayer = null;
        NBTTagCompound nbttagcompound1 = null;
        if(list.size() > 0)
        {
            entityplayer = (EntityPlayer)list.get(0);
        }
        if(entityplayer != null)
        {
            nbttagcompound1 = new NBTTagCompound();
            entityplayer.writeToNBT(nbttagcompound1);
        }
        saveNBTTag(nbttagcompound, nbttagcompound1);
        return nbttagcompound;
    }

    private void saveNBTTag(NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1)
    {
        nbttagcompound.setLong("RandomSeed", randomSeed);
        nbttagcompound.setInteger("GameType", gameType);
        nbttagcompound.setBoolean("MapFeatures", enableMapFeatures);
        nbttagcompound.setInteger("SpawnX", spawnX);
        nbttagcompound.setInteger("SpawnY", spawnY);
        nbttagcompound.setInteger("SpawnZ", spawnZ);
        nbttagcompound.setLong("Time", worldTime);
        nbttagcompound.setLong("SizeOnDisk", sizeOnDisk);
        nbttagcompound.setLong("LastPlayed", System.currentTimeMillis());
        nbttagcompound.setString("LevelName", levelName);
        nbttagcompound.setInteger("version", saveVersion);
        nbttagcompound.setInteger("rainTime", rainTime);
        nbttagcompound.setBoolean("raining", raining);
        nbttagcompound.setInteger("thunderTime", thunderTime);
        nbttagcompound.setBoolean("thundering", thundering);
        nbttagcompound.setBoolean("hardcore", hardcore);
        if(nbttagcompound1 != null)
        {
            nbttagcompound.setCompoundTag("Player", nbttagcompound1);
        }
    }

    public long getRandomSeed()
    {
        return randomSeed;
    }

    public int getSpawnX()
    {
        return spawnX;
    }

    public int getSpawnY()
    {
        return spawnY;
    }

    public int getSpawnZ()
    {
        return spawnZ;
    }

    public long getWorldTime()
    {
        return worldTime;
    }

    public long getSizeOnDisk()
    {
        return sizeOnDisk;
    }

    public int getDimension()
    {
        return dimension;
    }

    public void setWorldTime(long l)
    {
        worldTime = l;
    }

    public void setSizeOnDisk(long l)
    {
        sizeOnDisk = l;
    }

    public void setSpawnPosition(int i, int j, int k)
    {
        spawnX = i;
        spawnY = j;
        spawnZ = k;
    }

    public void setWorldName(String s)
    {
        levelName = s;
    }

    public int getSaveVersion()
    {
        return saveVersion;
    }

    public void setSaveVersion(int i)
    {
        saveVersion = i;
    }

    public boolean getIsThundering()
    {
        return thundering;
    }

    public void setIsThundering(boolean flag)
    {
        thundering = flag;
    }

    public int getThunderTime()
    {
        return thunderTime;
    }

    public void setThunderTime(int i)
    {
        thunderTime = i;
    }

    public boolean getIsRaining()
    {
        return raining;
    }

    public void setIsRaining(boolean flag)
    {
        raining = flag;
    }

    public int getRainTime()
    {
        return rainTime;
    }

    public void setRainTime(int i)
    {
        rainTime = i;
    }

    public int getGameType()
    {
        return gameType;
    }

    public boolean getMapFeaturesEnabled()
    {
        return enableMapFeatures;
    }

    public void setGameType(int i)
    {
        gameType = i;
    }

    public boolean isHardcoreModeEnabled()
    {
        return hardcore;
    }
}
