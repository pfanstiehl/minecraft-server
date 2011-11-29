// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IServer

public abstract class RConThreadBase
    implements Runnable
{

    protected boolean running;
    protected IServer server;
    protected Thread rconThread;
    protected int field_40415_d;
    protected List socketList;
    protected List serverSocketList;

    RConThreadBase(IServer iserver)
    {
        running = false;
        field_40415_d = 5;
        socketList = new ArrayList();
        serverSocketList = new ArrayList();
        server = iserver;
        if(server.isDebuggingEnabled())
        {
            logWarning("Debugging is enabled, performance maybe reduced!");
        }
    }

    public synchronized void startThread()
    {
        rconThread = new Thread(this);
        rconThread.start();
        running = true;
    }

    public boolean isRunning()
    {
        return running;
    }

    protected void logInfo(String s)
    {
        server.logIn(s);
    }

    protected void log(String s)
    {
        server.log(s);
    }

    protected void logWarning(String s)
    {
        server.logWarning(s);
    }

    protected void logSevere(String s)
    {
        server.logSevere(s);
    }

    protected int getNumberOfPlayers()
    {
        return server.playersOnline();
    }

    protected void registerSocket(DatagramSocket datagramsocket)
    {
        logInfo((new StringBuilder()).append("registerSocket: ").append(datagramsocket).toString());
        socketList.add(datagramsocket);
    }

    protected boolean closeSocket(DatagramSocket datagramsocket, boolean flag)
    {
        logInfo((new StringBuilder()).append("closeSocket: ").append(datagramsocket).toString());
        if(null == datagramsocket)
        {
            return false;
        }
        boolean flag1 = false;
        if(!datagramsocket.isClosed())
        {
            datagramsocket.close();
            flag1 = true;
        }
        if(flag)
        {
            socketList.remove(datagramsocket);
        }
        return flag1;
    }

    protected boolean closeServerSocket(ServerSocket serversocket)
    {
        return closeServerSocket_do(serversocket, true);
    }

    protected boolean closeServerSocket_do(ServerSocket serversocket, boolean flag)
    {
        logInfo((new StringBuilder()).append("closeSocket: ").append(serversocket).toString());
        if(null == serversocket)
        {
            return false;
        }
        boolean flag1 = false;
        try
        {
            if(!serversocket.isClosed())
            {
                serversocket.close();
                flag1 = true;
            }
        }
        catch(IOException ioexception)
        {
            logWarning((new StringBuilder()).append("IO: ").append(ioexception.getMessage()).toString());
        }
        if(flag)
        {
            serverSocketList.remove(serversocket);
        }
        return flag1;
    }

    protected void closeAllSockets()
    {
        clos(false);
    }

    protected void clos(boolean flag)
    {
        int i = 0;
        Iterator iterator = socketList.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            DatagramSocket datagramsocket = (DatagramSocket)iterator.next();
            if(closeSocket(datagramsocket, false))
            {
                i++;
            }
        } while(true);
        socketList.clear();
        iterator = serverSocketList.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            ServerSocket serversocket = (ServerSocket)iterator.next();
            if(closeServerSocket_do(serversocket, false))
            {
                i++;
            }
        } while(true);
        serverSocketList.clear();
        if(flag && 0 < i)
        {
            logWarning((new StringBuilder()).append("Force closed ").append(i).append(" sockets").toString());
        }
    }
}
