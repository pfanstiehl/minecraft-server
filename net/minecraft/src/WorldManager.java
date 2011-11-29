// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            IWorldAccess, WorldServer, WorldProvider, EntityTracker, 
//            ServerConfigurationManager, Packet61DoorChange, Entity, TileEntity, 
//            EntityPlayer

public class WorldManager
    implements IWorldAccess
{

    private MinecraftServer mcServer;
    private WorldServer world;

    public WorldManager(MinecraftServer minecraftserver, WorldServer worldserver)
    {
        mcServer = minecraftserver;
        world = worldserver;
    }

    public void spawnParticle(String s, double d, double d1, double d2, 
            double d3, double d4, double d5)
    {
    }

    public void obtainEntitySkin(Entity entity)
    {
        mcServer.getEntityTracker(world.worldProvider.worldType).trackEntity(entity);
    }

    public void releaseEntitySkin(Entity entity)
    {
        mcServer.getEntityTracker(world.worldProvider.worldType).untrackEntity(entity);
    }

    public void playSound(String s, double d, double d1, double d2, 
            float f, float f1)
    {
    }

    public void markBlockRangeNeedsUpdate(int i, int j, int k, int l, int i1, int j1)
    {
    }

    public void markBlockNeedsUpdate(int i, int j, int k)
    {
        mcServer.configManager.markBlockNeedsUpdate(i, j, k, world.worldProvider.worldType);
    }

    public void playRecord(String s, int i, int j, int k)
    {
    }

    public void doNothingWithTileEntity(int i, int j, int k, TileEntity tileentity)
    {
        mcServer.configManager.sentTileEntityToPlayer(i, j, k, tileentity);
    }

    public void playAuxSFX(EntityPlayer entityplayer, int i, int j, int k, int l, int i1)
    {
        mcServer.configManager.func_28171_a(entityplayer, j, k, l, 64D, world.worldProvider.worldType, new Packet61DoorChange(i, j, k, l, i1));
    }
}
