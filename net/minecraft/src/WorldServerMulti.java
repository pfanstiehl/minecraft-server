// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            WorldServer, ISaveHandler, WorldSettings

public class WorldServerMulti extends WorldServer
{

    public WorldServerMulti(MinecraftServer minecraftserver, ISaveHandler isavehandler, String s, int i, WorldSettings worldsettings, WorldServer worldserver)
    {
        super(minecraftserver, isavehandler, s, i, worldsettings);
        mapStorage = worldserver.mapStorage;
    }
}
