// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.ChunkProviderServer;
import net.minecraft.src.ConsoleCommandHandler;
import net.minecraft.src.ConsoleLogManager;
import net.minecraft.src.ConvertProgressUpdater;
import net.minecraft.src.EntityTracker;
import net.minecraft.src.ICommandListener;
import net.minecraft.src.ISaveFormat;
import net.minecraft.src.IServer;
import net.minecraft.src.IUpdatePlayerListBox;
import net.minecraft.src.NetworkListenThread;
import net.minecraft.src.Packet4UpdateTime;
import net.minecraft.src.PropertyManager;
import net.minecraft.src.RConConsoleSource;
import net.minecraft.src.RConThreadMain;
import net.minecraft.src.RConThreadQuery;
import net.minecraft.src.SaveConverterMcRegion;
import net.minecraft.src.SaveOldDir;
import net.minecraft.src.ServerCommand;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.ServerGUI;
import net.minecraft.src.StatList;
import net.minecraft.src.ThreadCommandReader;
import net.minecraft.src.ThreadServerApplication;
import net.minecraft.src.ThreadSleepForever;
import net.minecraft.src.Vec3D;
import net.minecraft.src.WorldInfo;
import net.minecraft.src.WorldManager;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldServer;
import net.minecraft.src.WorldServerMulti;
import net.minecraft.src.WorldSettings;

public class MinecraftServer
    implements Runnable, ICommandListener, IServer
{

    public static Logger logger = Logger.getLogger("Minecraft");
    public static HashMap field_6037_b = new HashMap();
    private String hostname;
    private int serverPort;
    public NetworkListenThread networkServer;
    public PropertyManager propertyManagerObj;
    public WorldServer worldMngr[];
    public long field_40027_f[];
    public long field_40028_g[][];
    public ServerConfigurationManager configManager;
    private ConsoleCommandHandler commandHandler;
    private boolean serverRunning;
    public boolean serverStopped;
    int deathTime;
    public String currentTask;
    public int percentDone;
    private List playersOnline;
    private List commands;
    public EntityTracker entityTracker[];
    public boolean onlineMode;
    public boolean spawnPeacefulMobs;
    public boolean pvpOn;
    public boolean allowFlight;
    public String motd;
    private RConThreadQuery rconQueryThread;
    private RConThreadMain rconMainThread;

    public MinecraftServer()
    {
        field_40027_f = new long[100];
        serverRunning = true;
        serverStopped = false;
        deathTime = 0;
        playersOnline = new ArrayList();
        commands = Collections.synchronizedList(new ArrayList());
        entityTracker = new EntityTracker[3];
        new ThreadSleepForever(this);
    }

    private boolean startServer()
        throws UnknownHostException
    {
        commandHandler = new ConsoleCommandHandler(this);
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);
        threadcommandreader.setDaemon(true);
        threadcommandreader.start();
        ConsoleLogManager.init();
        logger.info("Starting minecraft server version 1.0.1");
        if(Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L)
        {
            logger.warning("**** NOT ENOUGH RAM!");
            logger.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }
        logger.info("Loading properties");
        propertyManagerObj = new PropertyManager(new File("server.properties"));
        hostname = propertyManagerObj.getStringProperty("server-ip", "");
        onlineMode = propertyManagerObj.getBooleanProperty("online-mode", true);
        spawnPeacefulMobs = propertyManagerObj.getBooleanProperty("spawn-animals", true);
        pvpOn = propertyManagerObj.getBooleanProperty("pvp", true);
        allowFlight = propertyManagerObj.getBooleanProperty("allow-flight", false);
        motd = propertyManagerObj.getStringProperty("motd", "A Minecraft Server");
        motd.replace('\247', '$');
        InetAddress inetaddress = null;
        if(hostname.length() > 0)
        {
            inetaddress = InetAddress.getByName(hostname);
        }
        serverPort = propertyManagerObj.getIntProperty("server-port", 25565);
        logger.info((new StringBuilder()).append("Starting Minecraft server on ").append(hostname.length() != 0 ? hostname : "*").append(":").append(serverPort).toString());
        try
        {
            networkServer = new NetworkListenThread(this, inetaddress, serverPort);
        }
        catch(IOException ioexception)
        {
            logger.warning("**** FAILED TO BIND TO PORT!");
            logger.log(Level.WARNING, (new StringBuilder()).append("The exception was: ").append(ioexception.toString()).toString());
            logger.warning("Perhaps a server is already running on that port?");
            return false;
        }
        if(!onlineMode)
        {
            logger.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            logger.warning("The server will make no attempt to authenticate usernames. Beware.");
            logger.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            logger.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }
        configManager = new ServerConfigurationManager(this);
        entityTracker[0] = new EntityTracker(this, 0);
        entityTracker[1] = new EntityTracker(this, -1);
        entityTracker[2] = new EntityTracker(this, 1);
        long l = System.nanoTime();
        String s = propertyManagerObj.getStringProperty("level-name", "world");
        String s1 = propertyManagerObj.getStringProperty("level-seed", "");
        long l1 = (new Random()).nextLong();
        if(s1.length() > 0)
        {
            try
            {
                long l2 = Long.parseLong(s1);
                if(l2 != 0L)
                {
                    l1 = l2;
                }
            }
            catch(NumberFormatException numberformatexception)
            {
                l1 = s1.hashCode();
            }
        }
        logger.info((new StringBuilder()).append("Preparing level \"").append(s).append("\"").toString());
        initWorld(new SaveConverterMcRegion(new File(".")), s, l1);
        logger.info((new StringBuilder()).append("Done (").append(System.nanoTime() - l).append("ns)! For help, type \"help\" or \"?\"").toString());
        if(propertyManagerObj.getBooleanProperty("enable-query", false))
        {
            logger.info("Starting GS4 status listener");
            rconQueryThread = new RConThreadQuery(this);
            rconQueryThread.startThread();
        }
        if(propertyManagerObj.getBooleanProperty("enable-rcon", false))
        {
            logger.info("Starting remote control listener");
            rconMainThread = new RConThreadMain(this);
            rconMainThread.startThread();
        }
        return true;
    }

    private void initWorld(ISaveFormat isaveformat, String s, long l)
    {
        if(isaveformat.isOldSaveType(s))
        {
            logger.info("Converting map!");
            isaveformat.convertMapFormat(s, new ConvertProgressUpdater(this));
        }
        worldMngr = new WorldServer[3];
        field_40028_g = new long[worldMngr.length][100];
        int i = propertyManagerObj.getIntProperty("gamemode", 0);
        i = WorldSettings.validGameType(i);
        logger.info((new StringBuilder()).append("Default game type: ").append(i).toString());
        WorldSettings worldsettings = new WorldSettings(l, i, true, false);
        SaveOldDir saveolddir = new SaveOldDir(new File("."), s, true);
        for(int j = 0; j < worldMngr.length; j++)
        {
            byte byte0 = 0;
            if(j == 1)
            {
                byte0 = -1;
            }
            if(j == 2)
            {
                byte0 = 1;
            }
            if(j == 0)
            {
                worldMngr[j] = new WorldServer(this, saveolddir, s, byte0, worldsettings);
            } else
            {
                worldMngr[j] = new WorldServerMulti(this, saveolddir, s, byte0, worldsettings, worldMngr[0]);
            }
            worldMngr[j].addWorldAccess(new WorldManager(this, worldMngr[j]));
            worldMngr[j].difficultySetting = propertyManagerObj.getIntProperty("difficulty", 1);
            worldMngr[j].setAllowedSpawnTypes(propertyManagerObj.getBooleanProperty("spawn-monsters", true), spawnPeacefulMobs);
            worldMngr[j].getWorldInfo().setGameType(i);
            configManager.setPlayerManager(worldMngr);
        }

        char c = '\304';
        long l1 = System.currentTimeMillis();
label0:
        for(int k = 0; k < 1; k++)
        {
            logger.info((new StringBuilder()).append("Preparing start region for level ").append(k).toString());
            if(k != 0 && !propertyManagerObj.getBooleanProperty("allow-nether", true))
            {
                continue;
            }
            WorldServer worldserver = worldMngr[k];
            ChunkCoordinates chunkcoordinates = worldserver.getSpawnPoint();
            int i1 = -c;
            do
            {
                if(i1 > c || !serverRunning)
                {
                    continue label0;
                }
                for(int j1 = -c; j1 <= c && serverRunning; j1 += 16)
                {
                    long l2 = System.currentTimeMillis();
                    if(l2 < l1)
                    {
                        l1 = l2;
                    }
                    if(l2 > l1 + 1000L)
                    {
                        int k1 = (c * 2 + 1) * (c * 2 + 1);
                        int i2 = (i1 + c) * (c * 2 + 1) + (j1 + 1);
                        outputPercentRemaining("Preparing spawn area", (i2 * 100) / k1);
                        l1 = l2;
                    }
                    worldserver.chunkProviderServer.loadChunk(chunkcoordinates.posX + i1 >> 4, chunkcoordinates.posZ + j1 >> 4);
                    while(worldserver.updatingLighting() && serverRunning) ;
                }

                i1 += 16;
            } while(true);
        }

        clearCurrentTask();
    }

    private void outputPercentRemaining(String s, int i)
    {
        currentTask = s;
        percentDone = i;
        logger.info((new StringBuilder()).append(s).append(": ").append(i).append("%").toString());
    }

    private void clearCurrentTask()
    {
        currentTask = null;
        percentDone = 0;
    }

    private void saveServerWorld()
    {
        logger.info("Saving chunks");
        for(int i = 0; i < worldMngr.length; i++)
        {
            WorldServer worldserver = worldMngr[i];
            worldserver.saveWorld(true, null);
            worldserver.func_30006_w();
        }

    }

    private void stopServer()
    {
        logger.info("Stopping server");
        if(configManager != null)
        {
            configManager.savePlayerStates();
        }
        for(int i = 0; i < worldMngr.length; i++)
        {
            WorldServer worldserver = worldMngr[i];
            if(worldserver != null)
            {
                saveServerWorld();
            }
        }

    }

    public void initiateShutdown()
    {
        serverRunning = false;
    }

    public void run()
    {
        try
        {
            if(startServer())
            {
                long l = System.currentTimeMillis();
                long l1 = 0L;
                while(serverRunning) 
                {
                    long l2 = System.currentTimeMillis();
                    long l3 = l2 - l;
                    if(l3 > 2000L)
                    {
                        logger.warning("Can't keep up! Did the system time change, or is the server overloaded?");
                        l3 = 2000L;
                    }
                    if(l3 < 0L)
                    {
                        logger.warning("Time ran backwards! Did the system time change?");
                        l3 = 0L;
                    }
                    l1 += l3;
                    l = l2;
                    if(worldMngr[0].isAllPlayersFullyAsleep())
                    {
                        doTick();
                        l1 = 0L;
                    } else
                    {
                        while(l1 > 50L) 
                        {
                            l1 -= 50L;
                            doTick();
                        }
                    }
                    Thread.sleep(1L);
                }
            } else
            {
                while(serverRunning) 
                {
                    commandLineParser();
                    try
                    {
                        Thread.sleep(10L);
                    }
                    catch(InterruptedException interruptedexception)
                    {
                        interruptedexception.printStackTrace();
                    }
                }
            }
        }
        catch(Throwable throwable1)
        {
            throwable1.printStackTrace();
            logger.log(Level.SEVERE, "Unexpected exception", throwable1);
            while(serverRunning) 
            {
                commandLineParser();
                try
                {
                    Thread.sleep(10L);
                }
                catch(InterruptedException interruptedexception1)
                {
                    interruptedexception1.printStackTrace();
                }
            }
        }
        finally
        {
            try
            {
                stopServer();
                serverStopped = true;
            }
            catch(Throwable throwable2)
            {
                throwable2.printStackTrace();
            }
            finally
            {
                System.exit(0);
            }
        }
    }

    private void doTick()
    {
        long l = System.nanoTime();
        ArrayList arraylist = new ArrayList();
        for(Iterator iterator = field_6037_b.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            int j1 = ((Integer)field_6037_b.get(s)).intValue();
            if(j1 > 0)
            {
                field_6037_b.put(s, Integer.valueOf(j1 - 1));
            } else
            {
                arraylist.add(s);
            }
        }

        for(int i = 0; i < arraylist.size(); i++)
        {
            field_6037_b.remove(arraylist.get(i));
        }

        AxisAlignedBB.clearBoundingBoxPool();
        Vec3D.initialize();
        deathTime++;
        for(int j = 0; j < worldMngr.length; j++)
        {
            long l1 = System.nanoTime();
            if(j == 0 || propertyManagerObj.getBooleanProperty("allow-nether", true))
            {
                WorldServer worldserver = worldMngr[j];
                if(deathTime % 20 == 0)
                {
                    configManager.sendPacketToAllPlayersInDimension(new Packet4UpdateTime(worldserver.getWorldTime()), worldserver.worldProvider.worldType);
                }
                worldserver.tick();
                while(worldserver.updatingLighting()) ;
                worldserver.updateEntities();
            }
            field_40028_g[j][deathTime % 100] = System.nanoTime() - l1;
        }

        networkServer.handleNetworkListenThread();
        configManager.onTick();
        for(int k = 0; k < entityTracker.length; k++)
        {
            entityTracker[k].updateTrackedEntities();
        }

        for(int i1 = 0; i1 < playersOnline.size(); i1++)
        {
            ((IUpdatePlayerListBox)playersOnline.get(i1)).update();
        }

        try
        {
            commandLineParser();
        }
        catch(Exception exception)
        {
            logger.log(Level.WARNING, "Unexpected exception while parsing console command", exception);
        }
        field_40027_f[deathTime % 100] = System.nanoTime() - l;
    }

    public void addCommand(String s, ICommandListener icommandlistener)
    {
        commands.add(new ServerCommand(s, icommandlistener));
    }

    public void commandLineParser()
    {
        ServerCommand servercommand;
        for(; commands.size() > 0; commandHandler.handleCommand(servercommand))
        {
            servercommand = (ServerCommand)commands.remove(0);
        }

    }

    public void addToOnlinePlayerList(IUpdatePlayerListBox iupdateplayerlistbox)
    {
        playersOnline.add(iupdateplayerlistbox);
    }

    public static void main(String args[])
    {
        StatList.func_27092_a();
        try
        {
            MinecraftServer minecraftserver = new MinecraftServer();
            if(!java.awt.GraphicsEnvironment.isHeadless() && (args.length <= 0 || !args[0].equals("nogui")))
            {
                ServerGUI.initGui(minecraftserver);
            }
            (new ThreadServerApplication("Server thread", minecraftserver)).start();
        }
        catch(Exception exception)
        {
            logger.log(Level.SEVERE, "Failed to start the minecraft server", exception);
        }
    }

    public File getFile(String s)
    {
        return new File(s);
    }

    public void log(String s)
    {
        logger.info(s);
    }

    public void logWarning(String s)
    {
        logger.warning(s);
    }

    public String getUsername()
    {
        return "CONSOLE";
    }

    public WorldServer getWorldManager(int i)
    {
        if(i == -1)
        {
            return worldMngr[1];
        }
        if(i == 1)
        {
            return worldMngr[2];
        } else
        {
            return worldMngr[0];
        }
    }

    public EntityTracker getEntityTracker(int i)
    {
        if(i == -1)
        {
            return entityTracker[1];
        }
        if(i == 1)
        {
            return entityTracker[2];
        } else
        {
            return entityTracker[0];
        }
    }

    public int getIntProperty(String s, int i)
    {
        return propertyManagerObj.getIntProperty(s, i);
    }

    public String getStringProperty(String s, String s1)
    {
        return propertyManagerObj.getStringProperty(s, s1);
    }

    public void setProperty(String s, Object obj)
    {
        propertyManagerObj.setProperty(s, obj);
    }

    public void saveProperties()
    {
        propertyManagerObj.saveProperties();
    }

    public String getSettingsFilename()
    {
        File file = propertyManagerObj.func_40656_c();
        if(file != null)
        {
            return file.getAbsolutePath();
        } else
        {
            return "No settings file";
        }
    }

    public String getHostname()
    {
        return hostname;
    }

    public int getPort()
    {
        return serverPort;
    }

    public String getMotd()
    {
        return motd;
    }

    public String getVersionString()
    {
        return "1.0.1";
    }

    public int playersOnline()
    {
        return configManager.playersOnline();
    }

    public int getMaxPlayers()
    {
        return configManager.getMaxPlayers();
    }

    public String[] getPlayerNamesAsList()
    {
        return configManager.getPlayerNamesAsList();
    }

    public String getWorldName()
    {
        return propertyManagerObj.getStringProperty("level-name", "world");
    }

    public String getPlugin()
    {
        return "";
    }

    public void func_40010_o()
    {
    }

    public String handleRConCommand(String s)
    {
        RConConsoleSource.instance.resetLog();
        commandHandler.handleCommand(new ServerCommand(s, RConConsoleSource.instance));
        return RConConsoleSource.instance.getLogContents();
    }

    public boolean isDebuggingEnabled()
    {
        return false;
    }

    public void logSevere(String s)
    {
        logger.log(Level.SEVERE, s);
    }

    public void logIn(String s)
    {
        if(isDebuggingEnabled())
        {
            logger.log(Level.INFO, s);
        }
    }

    public String[] getBannedIPsList()
    {
        return (String[])configManager.getBannedIPsList().toArray(new String[0]);
    }

    public String[] getBannedPlayersList()
    {
        return (String[])configManager.getBannedPlayersList().toArray(new String[0]);
    }

    public static boolean isServerRunning(MinecraftServer minecraftserver)
    {
        return minecraftserver.serverRunning;
    }

}
