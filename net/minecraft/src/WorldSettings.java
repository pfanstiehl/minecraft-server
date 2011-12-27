// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public final class WorldSettings
{

    private final long seed;
    private final int gameType;
    private final boolean mapFeaturesEnabled;
    private final boolean hardcoreEnabled;

    public WorldSettings(long l, int i, boolean flag, boolean flag1)
    {
        seed = l;
        gameType = i;
        mapFeaturesEnabled = flag;
        hardcoreEnabled = flag1;
    }

    public long getSeed()
    {
        return seed;
    }

    public int getGameType()
    {
        return gameType;
    }

    public boolean getHardcoreEnabled()
    {
        return hardcoreEnabled;
    }

    public boolean isMapFeaturesEnabled()
    {
        return mapFeaturesEnabled;
    }

    public static int validGameType(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
            return i;
        }
        return 0;
    }
}
