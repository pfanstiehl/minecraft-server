// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            PlayerManager, PropertyManager, WorldServer, ISaveHandler, 
//            EntityPlayerMP, ChunkProviderServer, IPlayerFileData, Packet201PlayerInfo, 
//            NetServerHandler, NetLoginHandler, NetworkManager, ItemInWorldManager, 
//            EntityTracker, WorldInfo, EntityPlayer, ChunkCoordinates, 
//            Packet70Bed, Packet9Respawn, World, Teleporter, 
//            Packet3Chat, Packet4UpdateTime, Packet, TileEntity

public class ServerConfigurationManager
{

    public static Logger logger = Logger.getLogger("Minecraft");
    public List playerEntities;
    private MinecraftServer mcServer;
    private PlayerManager playerManagerObj[];
    private int maxPlayers;
    private Set bannedPlayers;
    private Set bannedIPs;
    private Set ops;
    private Set whiteListedIPs;
    private File bannedPlayersFile;
    private File ipBanFile;
    private File opFile;
    private File whitelistPlayersFile;
    private IPlayerFileData playerNBTManagerObj;
    private boolean whiteListEnforced;
    private int field_35482_p;

    public ServerConfigurationManager(MinecraftServer minecraftserver)
    {
        playerEntities = new ArrayList();
        bannedPlayers = new HashSet();
        bannedIPs = new HashSet();
        ops = new HashSet();
        whiteListedIPs = new HashSet();
        field_35482_p = 0;
        playerManagerObj = new PlayerManager[3];
        mcServer = minecraftserver;
        bannedPlayersFile = minecraftserver.getFile("banned-players.txt");
        ipBanFile = minecraftserver.getFile("banned-ips.txt");
        opFile = minecraftserver.getFile("ops.txt");
        whitelistPlayersFile = minecraftserver.getFile("white-list.txt");
        int i = minecraftserver.propertyManagerObj.getIntProperty("view-distance", 10);
        playerManagerObj[0] = new PlayerManager(minecraftserver, 0, i);
        playerManagerObj[1] = new PlayerManager(minecraftserver, -1, i);
        playerManagerObj[2] = new PlayerManager(minecraftserver, 1, i);
        maxPlayers = minecraftserver.propertyManagerObj.getIntProperty("max-players", 20);
        whiteListEnforced = minecraftserver.propertyManagerObj.getBooleanProperty("white-list", false);
        readBannedPlayers();
        loadBannedList();
        loadOps();
        loadWhiteList();
        writeBannedPlayers();
        saveBannedList();
        saveOps();
        saveWhiteList();
    }

    public void setPlayerManager(WorldServer aworldserver[])
    {
        playerNBTManagerObj = aworldserver[0].getWorldFile().getPlayerNBTManager();
    }

    public void joinNewPlayerManager(EntityPlayerMP entityplayermp)
    {
        playerManagerObj[0].removePlayer(entityplayermp);
        playerManagerObj[1].removePlayer(entityplayermp);
        playerManagerObj[2].removePlayer(entityplayermp);
        getPlayerManager(entityplayermp.dimension).addPlayer(entityplayermp);
        WorldServer worldserver = mcServer.getWorldManager(entityplayermp.dimension);
        worldserver.chunkProviderServer.loadChunk((int)entityplayermp.posX >> 4, (int)entityplayermp.posZ >> 4);
    }

    public int getMaxTrackingDistance()
    {
        return playerManagerObj[0].getMaxTrackingDistance();
    }

    private PlayerManager getPlayerManager(int i)
    {
        if(i == -1)
        {
            return playerManagerObj[1];
        }
        if(i == 0)
        {
            return playerManagerObj[0];
        }
        if(i == 1)
        {
            return playerManagerObj[2];
        } else
        {
            return null;
        }
    }

    public void readPlayerDataFromFile(EntityPlayerMP entityplayermp)
    {
        playerNBTManagerObj.readPlayerData(entityplayermp);
    }

    public void playerLoggedIn(EntityPlayerMP entityplayermp)
    {
        sendPacketToAllPlayers(new Packet201PlayerInfo(entityplayermp.username, true, 1000));
        playerEntities.add(entityplayermp);
        WorldServer worldserver = mcServer.getWorldManager(entityplayermp.dimension);
        worldserver.chunkProviderServer.loadChunk((int)entityplayermp.posX >> 4, (int)entityplayermp.posZ >> 4);
        for(; worldserver.getCollidingBoundingBoxes(entityplayermp, entityplayermp.boundingBox).size() != 0; entityplayermp.setPosition(entityplayermp.posX, entityplayermp.posY + 1.0D, entityplayermp.posZ)) { }
        worldserver.entityJoinedWorld(entityplayermp);
        getPlayerManager(entityplayermp.dimension).addPlayer(entityplayermp);
        for(int i = 0; i < playerEntities.size(); i++)
        {
            EntityPlayerMP entityplayermp1 = (EntityPlayerMP)playerEntities.get(i);
            entityplayermp.playerNetServerHandler.sendPacket(new Packet201PlayerInfo(entityplayermp1.username, true, entityplayermp1.ping));
        }

    }

    public void serverUpdateMountedMovingPlayer(EntityPlayerMP entityplayermp)
    {
        getPlayerManager(entityplayermp.dimension).updateMountedMovingPlayer(entityplayermp);
    }

    public void playerLoggedOut(EntityPlayerMP entityplayermp)
    {
        playerNBTManagerObj.writePlayerData(entityplayermp);
        mcServer.getWorldManager(entityplayermp.dimension).removePlayerForLogoff(entityplayermp);
        playerEntities.remove(entityplayermp);
        getPlayerManager(entityplayermp.dimension).removePlayer(entityplayermp);
        sendPacketToAllPlayers(new Packet201PlayerInfo(entityplayermp.username, false, 9999));
    }

    public EntityPlayerMP login(NetLoginHandler netloginhandler, String s)
    {
        if(bannedPlayers.contains(s.trim().toLowerCase()))
        {
            netloginhandler.kickUser("You are banned from this server!");
            return null;
        }
        if(!isAllowedToLogin(s))
        {
            netloginhandler.kickUser("You are not white-listed on this server!");
            return null;
        }
        String s1 = netloginhandler.netManager.getRemoteAddress().toString();
        s1 = s1.substring(s1.indexOf("/") + 1);
        s1 = s1.substring(0, s1.indexOf(":"));
        if(bannedIPs.contains(s1))
        {
            netloginhandler.kickUser("Your IP address is banned from this server!");
            return null;
        }
        if(playerEntities.size() >= maxPlayers)
        {
            netloginhandler.kickUser("The server is full!");
            return null;
        }
        for(int i = 0; i < playerEntities.size(); i++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(i);
            if(entityplayermp.username.equalsIgnoreCase(s))
            {
                entityplayermp.playerNetServerHandler.kickPlayer("You logged in from another location");
            }
        }

        return new EntityPlayerMP(mcServer, mcServer.getWorldManager(0), s, new ItemInWorldManager(mcServer.getWorldManager(0)));
    }

    public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP entityplayermp, int i, boolean flag)
    {
        mcServer.getEntityTracker(entityplayermp.dimension).removeTrackedPlayerSymmetric(entityplayermp);
        mcServer.getEntityTracker(entityplayermp.dimension).untrackEntity(entityplayermp);
        getPlayerManager(entityplayermp.dimension).removePlayer(entityplayermp);
        playerEntities.remove(entityplayermp);
        mcServer.getWorldManager(entityplayermp.dimension).removePlayer(entityplayermp);
        ChunkCoordinates chunkcoordinates = entityplayermp.getSpawnChunk();
        entityplayermp.dimension = i;
        EntityPlayerMP entityplayermp1 = new EntityPlayerMP(mcServer, mcServer.getWorldManager(entityplayermp.dimension), entityplayermp.username, new ItemInWorldManager(mcServer.getWorldManager(entityplayermp.dimension)));
        if(flag)
        {
            entityplayermp1.func_41031_d(entityplayermp);
        }
        entityplayermp1.entityId = entityplayermp.entityId;
        entityplayermp1.playerNetServerHandler = entityplayermp.playerNetServerHandler;
        WorldServer worldserver = mcServer.getWorldManager(entityplayermp.dimension);
        entityplayermp1.itemInWorldManager.toggleGameType(entityplayermp.itemInWorldManager.getGameType());
        entityplayermp1.itemInWorldManager.func_35695_b(worldserver.getWorldInfo().getGameType());
        if(chunkcoordinates != null)
        {
            ChunkCoordinates chunkcoordinates1 = EntityPlayer.verifyBedCoordinates(mcServer.getWorldManager(entityplayermp.dimension), chunkcoordinates);
            if(chunkcoordinates1 != null)
            {
                entityplayermp1.setLocationAndAngles((float)chunkcoordinates1.posX + 0.5F, (float)chunkcoordinates1.posY + 0.1F, (float)chunkcoordinates1.posZ + 0.5F, 0.0F, 0.0F);
                entityplayermp1.setSpawnChunk(chunkcoordinates);
            } else
            {
                entityplayermp1.playerNetServerHandler.sendPacket(new Packet70Bed(0, 0));
            }
        }
        worldserver.chunkProviderServer.loadChunk((int)entityplayermp1.posX >> 4, (int)entityplayermp1.posZ >> 4);
        for(; worldserver.getCollidingBoundingBoxes(entityplayermp1, entityplayermp1.boundingBox).size() != 0; entityplayermp1.setPosition(entityplayermp1.posX, entityplayermp1.posY + 1.0D, entityplayermp1.posZ)) { }
        entityplayermp1.playerNetServerHandler.sendPacket(new Packet9Respawn((byte)entityplayermp1.dimension, (byte)entityplayermp1.worldObj.difficultySetting, entityplayermp1.worldObj.getRandomSeed(), entityplayermp1.worldObj.worldYMax, entityplayermp1.itemInWorldManager.getGameType()));
        entityplayermp1.playerNetServerHandler.teleportTo(entityplayermp1.posX, entityplayermp1.posY, entityplayermp1.posZ, entityplayermp1.rotationYaw, entityplayermp1.rotationPitch);
        func_28170_a(entityplayermp1, worldserver);
        getPlayerManager(entityplayermp1.dimension).addPlayer(entityplayermp1);
        worldserver.entityJoinedWorld(entityplayermp1);
        playerEntities.add(entityplayermp1);
        entityplayermp1.func_20057_k();
        entityplayermp1.func_22068_s();
        return entityplayermp1;
    }

    public void sendPlayerToOtherDimension(EntityPlayerMP entityplayermp, int i)
    {
        int j = entityplayermp.dimension;
        WorldServer worldserver = mcServer.getWorldManager(entityplayermp.dimension);
        entityplayermp.dimension = i;
        WorldServer worldserver1 = mcServer.getWorldManager(entityplayermp.dimension);
        entityplayermp.playerNetServerHandler.sendPacket(new Packet9Respawn((byte)entityplayermp.dimension, (byte)entityplayermp.worldObj.difficultySetting, worldserver1.getRandomSeed(), worldserver1.worldYMax, entityplayermp.itemInWorldManager.getGameType()));
        worldserver.removePlayer(entityplayermp);
        entityplayermp.isDead = false;
        double d = entityplayermp.posX;
        double d1 = entityplayermp.posZ;
        double d2 = 8D;
        if(entityplayermp.dimension == -1)
        {
            d /= d2;
            d1 /= d2;
            entityplayermp.setLocationAndAngles(d, entityplayermp.posY, d1, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
            if(entityplayermp.isEntityAlive())
            {
                worldserver.updateEntityWithOptionalForce(entityplayermp, false);
            }
        } else
        if(entityplayermp.dimension == 0)
        {
            d *= d2;
            d1 *= d2;
            entityplayermp.setLocationAndAngles(d, entityplayermp.posY, d1, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
            if(entityplayermp.isEntityAlive())
            {
                worldserver.updateEntityWithOptionalForce(entityplayermp, false);
            }
        } else
        {
            ChunkCoordinates chunkcoordinates = worldserver1.func_40212_d();
            d = chunkcoordinates.posX;
            entityplayermp.posY = chunkcoordinates.posY;
            d1 = chunkcoordinates.posZ;
            entityplayermp.setLocationAndAngles(d, entityplayermp.posY, d1, 90F, 0.0F);
            if(entityplayermp.isEntityAlive())
            {
                worldserver.updateEntityWithOptionalForce(entityplayermp, false);
            }
        }
        if(j != 1 && entityplayermp.isEntityAlive())
        {
            worldserver1.entityJoinedWorld(entityplayermp);
            entityplayermp.setLocationAndAngles(d, entityplayermp.posY, d1, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
            worldserver1.updateEntityWithOptionalForce(entityplayermp, false);
            worldserver1.chunkProviderServer.chunkLoadOverride = true;
            (new Teleporter()).setExitLocation(worldserver1, entityplayermp);
            worldserver1.chunkProviderServer.chunkLoadOverride = false;
        }
        joinNewPlayerManager(entityplayermp);
        entityplayermp.playerNetServerHandler.teleportTo(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
        entityplayermp.Sets(worldserver1);
        entityplayermp.itemInWorldManager.setWorld(worldserver1);
        func_28170_a(entityplayermp, worldserver1);
        func_30008_g(entityplayermp);
    }

    public void onTick()
    {
        if(++field_35482_p > 200)
        {
            field_35482_p = 0;
        }
        if(field_35482_p < playerEntities.size())
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(field_35482_p);
            sendPacketToAllPlayers(new Packet201PlayerInfo(entityplayermp.username, true, entityplayermp.ping));
        }
        for(int i = 0; i < playerManagerObj.length; i++)
        {
            playerManagerObj[i].updatePlayerInstances();
        }

    }

    public void markBlockNeedsUpdate(int i, int j, int k, int l)
    {
        getPlayerManager(l).markBlockNeedsUpdate(i, j, k);
    }

    public void sendPacketToAllPlayers(Packet packet)
    {
        for(int i = 0; i < playerEntities.size(); i++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(i);
            entityplayermp.playerNetServerHandler.sendPacket(packet);
        }

    }

    public void sendPacketToAllPlayersInDimension(Packet packet, int i)
    {
        for(int j = 0; j < playerEntities.size(); j++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(j);
            if(entityplayermp.dimension == i)
            {
                entityplayermp.playerNetServerHandler.sendPacket(packet);
            }
        }

    }

    public String getPlayerList()
    {
        String s = "";
        for(int i = 0; i < playerEntities.size(); i++)
        {
            if(i > 0)
            {
                s = (new StringBuilder()).append(s).append(", ").toString();
            }
            s = (new StringBuilder()).append(s).append(((EntityPlayerMP)playerEntities.get(i)).username).toString();
        }

        return s;
    }

    public String[] getPlayerNamesAsList()
    {
        String as[] = new String[playerEntities.size()];
        for(int i = 0; i < playerEntities.size(); i++)
        {
            as[i] = ((EntityPlayerMP)playerEntities.get(i)).username;
        }

        return as;
    }

    public void banPlayer(String s)
    {
        bannedPlayers.add(s.toLowerCase());
        writeBannedPlayers();
    }

    public void pardonPlayer(String s)
    {
        bannedPlayers.remove(s.toLowerCase());
        writeBannedPlayers();
    }

    private void readBannedPlayers()
    {
        try
        {
            bannedPlayers.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(bannedPlayersFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                bannedPlayers.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to load ban list: ").append(exception).toString());
        }
    }

    private void writeBannedPlayers()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(bannedPlayersFile, false));
            String s;
            for(Iterator iterator = bannedPlayers.iterator(); iterator.hasNext(); printwriter.println(s))
            {
                s = (String)iterator.next();
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to save ban list: ").append(exception).toString());
        }
    }

    public Set getBannedPlayersList()
    {
        return bannedPlayers;
    }

    public Set getBannedIPsList()
    {
        return bannedIPs;
    }

    public void banIP(String s)
    {
        bannedIPs.add(s.toLowerCase());
        saveBannedList();
    }

    public void pardonIP(String s)
    {
        bannedIPs.remove(s.toLowerCase());
        saveBannedList();
    }

    private void loadBannedList()
    {
        try
        {
            bannedIPs.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(ipBanFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                bannedIPs.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to load ip ban list: ").append(exception).toString());
        }
    }

    private void saveBannedList()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(ipBanFile, false));
            String s;
            for(Iterator iterator = bannedIPs.iterator(); iterator.hasNext(); printwriter.println(s))
            {
                s = (String)iterator.next();
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to save ip ban list: ").append(exception).toString());
        }
    }

    public void addOp(String s)
    {
        ops.add(s.toLowerCase());
        saveOps();
    }

    public void removeOp(String s)
    {
        ops.remove(s.toLowerCase());
        saveOps();
    }

    private void loadOps()
    {
        try
        {
            ops.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(opFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                ops.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to load operators list: ").append(exception).toString());
        }
    }

    private void saveOps()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(opFile, false));
            String s;
            for(Iterator iterator = ops.iterator(); iterator.hasNext(); printwriter.println(s))
            {
                s = (String)iterator.next();
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to save operators list: ").append(exception).toString());
        }
    }

    private void loadWhiteList()
    {
        try
        {
            whiteListedIPs.clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(whitelistPlayersFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                whiteListedIPs.add(s.trim().toLowerCase());
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to load white-list: ").append(exception).toString());
        }
    }

    private void saveWhiteList()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(whitelistPlayersFile, false));
            String s;
            for(Iterator iterator = whiteListedIPs.iterator(); iterator.hasNext(); printwriter.println(s))
            {
                s = (String)iterator.next();
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            logger.warning((new StringBuilder()).append("Failed to save white-list: ").append(exception).toString());
        }
    }

    public boolean isAllowedToLogin(String s)
    {
        s = s.trim().toLowerCase();
        return !whiteListEnforced || ops.contains(s) || whiteListedIPs.contains(s);
    }

    public boolean isOp(String s)
    {
        return ops.contains(s.trim().toLowerCase());
    }

    public EntityPlayerMP getPlayerEntity(String s)
    {
        for(int i = 0; i < playerEntities.size(); i++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(i);
            if(entityplayermp.username.equalsIgnoreCase(s))
            {
                return entityplayermp;
            }
        }

        return null;
    }

    public void sendChatMessageToPlayer(String s, String s1)
    {
        EntityPlayerMP entityplayermp = getPlayerEntity(s);
        if(entityplayermp != null)
        {
            entityplayermp.playerNetServerHandler.sendPacket(new Packet3Chat(s1));
        }
    }

    public void sendPacketToPlayersAroundPoint(double d, double d1, double d2, double d3, int i, Packet packet)
    {
        func_28171_a(null, d, d1, d2, d3, i, packet);
    }

    public void func_28171_a(EntityPlayer entityplayer, double d, double d1, double d2, 
            double d3, int i, Packet packet)
    {
        for(int j = 0; j < playerEntities.size(); j++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(j);
            if(entityplayermp == entityplayer || entityplayermp.dimension != i)
            {
                continue;
            }
            double d4 = d - entityplayermp.posX;
            double d5 = d1 - entityplayermp.posY;
            double d6 = d2 - entityplayermp.posZ;
            if(d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3)
            {
                entityplayermp.playerNetServerHandler.sendPacket(packet);
            }
        }

    }

    public void sendChatMessageToAllOps(String s)
    {
        Packet3Chat packet3chat = new Packet3Chat(s);
        for(int i = 0; i < playerEntities.size(); i++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)playerEntities.get(i);
            if(isOp(entityplayermp.username))
            {
                entityplayermp.playerNetServerHandler.sendPacket(packet3chat);
            }
        }

    }

    public boolean sendPacketToPlayer(String s, Packet packet)
    {
        EntityPlayerMP entityplayermp = getPlayerEntity(s);
        if(entityplayermp != null)
        {
            entityplayermp.playerNetServerHandler.sendPacket(packet);
            return true;
        } else
        {
            return false;
        }
    }

    public void savePlayerStates()
    {
        for(int i = 0; i < playerEntities.size(); i++)
        {
            playerNBTManagerObj.writePlayerData((EntityPlayer)playerEntities.get(i));
        }

    }

    public void sentTileEntityToPlayer(int i, int j, int k, TileEntity tileentity)
    {
    }

    public void addToWhiteList(String s)
    {
        whiteListedIPs.add(s);
        saveWhiteList();
    }

    public void removeFromWhiteList(String s)
    {
        whiteListedIPs.remove(s);
        saveWhiteList();
    }

    public Set getWhiteListedIPs()
    {
        return whiteListedIPs;
    }

    public void reloadWhiteList()
    {
        loadWhiteList();
    }

    public void func_28170_a(EntityPlayerMP entityplayermp, WorldServer worldserver)
    {
        entityplayermp.playerNetServerHandler.sendPacket(new Packet4UpdateTime(worldserver.getWorldTime()));
        if(worldserver.isRaining())
        {
            entityplayermp.playerNetServerHandler.sendPacket(new Packet70Bed(1, 0));
        }
    }

    public void func_30008_g(EntityPlayerMP entityplayermp)
    {
        entityplayermp.func_28017_a(entityplayermp.personalCraftingInventory);
        entityplayermp.func_30001_B();
    }

    public int playersOnline()
    {
        return playerEntities.size();
    }

    public int getMaxPlayers()
    {
        return maxPlayers;
    }

}
