// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.IOException;
import java.net.*;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            RConThreadBase, IServer, RConThreadClient

public class RConThreadMain extends RConThreadBase
{

    private int rconPort;
    private int serverPort;
    private String hostname;
    private ServerSocket serverSocket;
    private String rconPassword;
    private HashMap clientThreads;

    public RConThreadMain(IServer iserver)
    {
        super(iserver);
        serverSocket = null;
        rconPort = iserver.getIntProperty("rcon.port", 0);
        rconPassword = iserver.getStringProperty("rcon.password", "");
        hostname = iserver.getHostname();
        serverPort = iserver.getPort();
        if(0 == rconPort)
        {
            rconPort = serverPort + 10;
            log((new StringBuilder()).append("Setting default rcon port to ").append(rconPort).toString());
            iserver.setProperty("rcon.port", Integer.valueOf(rconPort));
            if(0 == rconPassword.length())
            {
                iserver.setProperty("rcon.password", "");
            }
            iserver.saveProperties();
        }
        if(0 == hostname.length())
        {
            hostname = "0.0.0.0";
        }
        initClientTh();
        serverSocket = null;
    }

    private void initClientTh()
    {
        clientThreads = new HashMap();
    }

    private void cleanClientThreadsMap()
    {
        Iterator iterator = clientThreads.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(!((RConThreadClient)entry.getValue()).isRunning())
            {
                iterator.remove();
            }
        } while(true);
    }

    public void run()
    {
        log((new StringBuilder()).append("RCON running on ").append(hostname).append(":").append(rconPort).toString());
        try
        {
            do
            {
                if(!running)
                {
                    break;
                }
                try
                {
                    Socket socket = serverSocket.accept();
                    socket.setSoTimeout(500);
                    RConThreadClient rconthreadclient = new RConThreadClient(server, socket);
                    rconthreadclient.startThread();
                    clientThreads.put(socket.getRemoteSocketAddress(), rconthreadclient);
                    cleanClientThreadsMap();
                }
                catch(SocketTimeoutException sockettimeoutexception)
                {
                    cleanClientThreadsMap();
                }
                catch(IOException ioexception)
                {
                    if(running)
                    {
                        log((new StringBuilder()).append("IO: ").append(ioexception.getMessage()).toString());
                    }
                }
            } while(true);
        }
        finally
        {
            closeServerSocket(serverSocket);
        }
    }

    public void startThread()
    {
        if(0 == rconPassword.length())
        {
            logWarning((new StringBuilder()).append("No rcon password set in '").append(server.getSettingsFilename()).append("', rcon disabled!").toString());
            return;
        }
        if(0 >= rconPort || 65535 < rconPort)
        {
            logWarning((new StringBuilder()).append("Invalid rcon port ").append(rconPort).append(" found in '").append(server.getSettingsFilename()).append("', rcon disabled!").toString());
            return;
        }
        if(running)
        {
            return;
        }
        try
        {
            serverSocket = new ServerSocket(rconPort, 0, InetAddress.getByName(hostname));
            serverSocket.setSoTimeout(500);
            super.startThread();
        }
        catch(IOException ioexception)
        {
            logWarning((new StringBuilder()).append("Unable to initialise rcon on ").append(hostname).append(":").append(rconPort).append(" : ").append(ioexception.getMessage()).toString());
        }
    }
}
