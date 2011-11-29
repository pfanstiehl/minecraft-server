// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            NetworkAcceptThread, NetLoginHandler, NetworkManager, NetServerHandler

public class NetworkListenThread
{

    public static Logger logger = Logger.getLogger("Minecraft");
    private ServerSocket serverSocket;
    private Thread networkAcceptThread;
    public volatile boolean isListening;
    private int field_977_f;
    private ArrayList pendingConnections;
    private ArrayList playerList;
    public MinecraftServer mcServer;
    private HashMap field_35506_i;

    public NetworkListenThread(MinecraftServer minecraftserver, InetAddress inetaddress, int i)
        throws IOException
    {
        isListening = false;
        field_977_f = 0;
        pendingConnections = new ArrayList();
        playerList = new ArrayList();
        field_35506_i = new HashMap();
        mcServer = minecraftserver;
        serverSocket = new ServerSocket(i, 0, inetaddress);
        serverSocket.setPerformancePreferences(0, 2, 1);
        isListening = true;
        networkAcceptThread = new NetworkAcceptThread(this, "Listen thread", minecraftserver);
        networkAcceptThread.start();
    }

    public void func_35505_a(Socket socket)
    {
        InetAddress inetaddress = socket.getInetAddress();
        synchronized(field_35506_i)
        {
            field_35506_i.remove(inetaddress);
        }
    }

    public void addPlayer(NetServerHandler netserverhandler)
    {
        playerList.add(netserverhandler);
    }

    private void addPendingConnection(NetLoginHandler netloginhandler)
    {
        if(netloginhandler == null)
        {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else
        {
            pendingConnections.add(netloginhandler);
            return;
        }
    }

    public void handleNetworkListenThread()
    {
        for(int i = 0; i < pendingConnections.size(); i++)
        {
            NetLoginHandler netloginhandler = (NetLoginHandler)pendingConnections.get(i);
            try
            {
                netloginhandler.tryLogin();
            }
            catch(Exception exception)
            {
                netloginhandler.kickUser("Internal server error");
                logger.log(Level.WARNING, (new StringBuilder()).append("Failed to handle packet: ").append(exception).toString(), exception);
            }
            if(netloginhandler.finishedProcessing)
            {
                pendingConnections.remove(i--);
            }
            netloginhandler.netManager.wakeThreads();
        }

        for(int j = 0; j < playerList.size(); j++)
        {
            NetServerHandler netserverhandler = (NetServerHandler)playerList.get(j);
            try
            {
                netserverhandler.handlePackets();
            }
            catch(Exception exception1)
            {
                logger.log(Level.WARNING, (new StringBuilder()).append("Failed to handle packet: ").append(exception1).toString(), exception1);
                netserverhandler.kickPlayer("Internal server error");
            }
            if(netserverhandler.connectionClosed)
            {
                playerList.remove(j--);
            }
            netserverhandler.netManager.wakeThreads();
        }

    }

    static ServerSocket getServerSocket(NetworkListenThread networklistenthread)
    {
        return networklistenthread.serverSocket;
    }

    static HashMap func_35504_b(NetworkListenThread networklistenthread)
    {
        return networklistenthread.field_35506_i;
    }

    static int func_712_b(NetworkListenThread networklistenthread)
    {
        return networklistenthread.field_977_f++;
    }

    static void func_716_a(NetworkListenThread networklistenthread, NetLoginHandler netloginhandler)
    {
        networklistenthread.addPendingConnection(netloginhandler);
    }

}
