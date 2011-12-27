// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            World, WorldProvider, IntHashMap, EntityAnimal, 
//            EntityWaterMob, Entity, EntityPlayer, ISaveHandler, 
//            ChunkProviderServer, TileEntity, WorldInfo, MathHelper, 
//            ServerConfigurationManager, Packet71Weather, Packet38EntityStatus, EntityTracker, 
//            Explosion, Packet60Explosion, Packet54PlayNoteBlock, Packet70Bed, 
//            WorldSettings, IChunkProvider

public class WorldServer extends World
{

    public ChunkProviderServer chunkProviderServer;
    public boolean disableSpawnProtection;
    public boolean levelSaving;
    private MinecraftServer mcServer;
    private IntHashMap field_34902_Q;

    public WorldServer(MinecraftServer minecraftserver, ISaveHandler isavehandler, String s, int i, WorldSettings worldsettings)
    {
        super(isavehandler, s, worldsettings, WorldProvider.getProviderForDimension(i));
        disableSpawnProtection = false;
        mcServer = minecraftserver;
        if(field_34902_Q == null)
        {
            field_34902_Q = new IntHashMap();
        }
    }

    public void updateEntityWithOptionalForce(Entity entity, boolean flag)
    {
        if(!mcServer.spawnPeacefulMobs && ((entity instanceof EntityAnimal) || (entity instanceof EntityWaterMob)))
        {
            entity.setEntityDead();
        }
        if(entity.riddenByEntity == null || !(entity.riddenByEntity instanceof EntityPlayer))
        {
            super.updateEntityWithOptionalForce(entity, flag);
        }
    }

    public void func_12017_b(Entity entity, boolean flag)
    {
        super.updateEntityWithOptionalForce(entity, flag);
    }

    protected IChunkProvider createChunkProvider()
    {
        IChunkLoader ichunkloader = worldFile.getChunkLoader(worldProvider);
        chunkProviderServer = new ChunkProviderServer(this, ichunkloader, worldProvider.getChunkProvider());
        return chunkProviderServer;
    }

    public List getTileEntityList(int i, int j, int k, int l, int i1, int j1)
    {
        ArrayList arraylist = new ArrayList();
        for(int k1 = 0; k1 < loadedTileEntityList.size(); k1++)
        {
            TileEntity tileentity = (TileEntity)loadedTileEntityList.get(k1);
            if(tileentity.xCoord >= i && tileentity.yCoord >= j && tileentity.zCoord >= k && tileentity.xCoord < l && tileentity.yCoord < i1 && tileentity.zCoord < j1)
            {
                arraylist.add(tileentity);
            }
        }

        return arraylist;
    }

    public boolean canMineBlock(EntityPlayer entityplayer, int i, int j, int k)
    {
        int l = MathHelper.abs(i - worldInfo.getSpawnX());
        int i1 = MathHelper.abs(k - worldInfo.getSpawnZ());
        if(l > i1)
        {
            i1 = l;
        }
        return i1 > 16 || mcServer.configManager.isOp(entityplayer.username);
    }

    protected void generateSpawnPoint()
    {
        if(field_34902_Q == null)
        {
            field_34902_Q = new IntHashMap();
        }
        super.generateSpawnPoint();
    }

    protected void obtainEntitySkin(Entity entity)
    {
        super.obtainEntitySkin(entity);
        field_34902_Q.addKey(entity.entityId, entity);
        Entity aentity[] = entity.getParts();
        if(aentity != null)
        {
            for(int i = 0; i < aentity.length; i++)
            {
                field_34902_Q.addKey(aentity[i].entityId, aentity[i]);
            }

        }
    }

    protected void releaseEntitySkin(Entity entity)
    {
        super.releaseEntitySkin(entity);
        field_34902_Q.removeObject(entity.entityId);
        Entity aentity[] = entity.getParts();
        if(aentity != null)
        {
            for(int i = 0; i < aentity.length; i++)
            {
                field_34902_Q.removeObject(aentity[i].entityId);
            }

        }
    }

    public Entity func_6158_a(int i)
    {
        return (Entity)field_34902_Q.lookup(i);
    }

    public boolean addLightningBolt(Entity entity)
    {
        if(super.addLightningBolt(entity))
        {
            mcServer.configManager.sendPacketToPlayersAroundPoint(entity.posX, entity.posY, entity.posZ, 512D, worldProvider.worldType, new Packet71Weather(entity));
            return true;
        } else
        {
            return false;
        }
    }

    public void sendTrackedEntityStatusUpdatePacket(Entity entity, byte byte0)
    {
        Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.entityId, byte0);
        mcServer.getEntityTracker(worldProvider.worldType).sendPacketToTrackedPlayersAndTrackedEntity(entity, packet38entitystatus);
    }

    public Explosion newExplosion(Entity entity, double d, double d1, double d2, 
            float f, boolean flag)
    {
        Explosion explosion = new Explosion(this, entity, d, d1, d2, f);
        explosion.isFlaming = flag;
        explosion.doExplosionA();
        explosion.doExplosionB(false);
        mcServer.configManager.sendPacketToPlayersAroundPoint(d, d1, d2, 64D, worldProvider.worldType, new Packet60Explosion(d, d1, d2, f, explosion.destroyedBlockPositions));
        return explosion;
    }

    public void playNoteAt(int i, int j, int k, int l, int i1)
    {
        super.playNoteAt(i, j, k, l, i1);
        mcServer.configManager.sendPacketToPlayersAroundPoint(i, j, k, 64D, worldProvider.worldType, new Packet54PlayNoteBlock(i, j, k, l, i1));
    }

    public void func_30006_w()
    {
        worldFile.func_22093_e();
    }

    protected void updateWeather()
    {
        boolean flag = isRaining();
        super.updateWeather();
        if(flag != isRaining())
        {
            if(flag)
            {
                mcServer.configManager.sendPacketToAllPlayers(new Packet70Bed(2, 0));
            } else
            {
                mcServer.configManager.sendPacketToAllPlayers(new Packet70Bed(1, 0));
            }
        }
    }
}
