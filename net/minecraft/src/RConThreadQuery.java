// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.IOException;
import java.net.*;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            RConThreadBase, IServer, RConOutputStream, RConUtils, 
//            RConThreadQueryAuth

public class RConThreadQuery extends RConThreadBase
{

    private long lastAuthCheckTime;
    private int queryPort;
    private int serverPort;
    private int maxPlayers;
    private String serverMotd;
    private String worldName;
    private DatagramSocket querySocket;
    private byte buffer[];
    private DatagramPacket incomingPacket;
    private HashMap field_40452_p;
    private String queryHostname;
    private String serverHostname;
    private HashMap queryClients;
    private long field_40448_t;
    private RConOutputStream output;
    private long lastQueryResponseTime;

    public RConThreadQuery(IServer iserver)
    {
        super(iserver);
        querySocket = null;
        buffer = new byte[1460];
        incomingPacket = null;
        queryPort = iserver.getIntProperty("query.port", 0);
        serverHostname = iserver.getHostname();
        serverPort = iserver.getPort();
        serverMotd = iserver.getMotd();
        maxPlayers = iserver.getMaxPlayers();
        worldName = iserver.getWorldName();
        lastQueryResponseTime = 0L;
        queryHostname = "0.0.0.0";
        if(0 == serverHostname.length() || queryHostname.equals(serverHostname))
        {
            serverHostname = "0.0.0.0";
            try
            {
                InetAddress inetaddress = InetAddress.getLocalHost();
                queryHostname = inetaddress.getHostAddress();
            }
            catch(UnknownHostException unknownhostexception)
            {
                logWarning((new StringBuilder()).append("Unable to determine local host IP, please set server-ip in '").append(iserver.getSettingsFilename()).append("' : ").append(unknownhostexception.getMessage()).toString());
            }
        } else
        {
            queryHostname = serverHostname;
        }
        if(0 == queryPort)
        {
            queryPort = serverPort;
            log((new StringBuilder()).append("Setting default query port to ").append(queryPort).toString());
            iserver.setProperty("query.port", Integer.valueOf(queryPort));
            iserver.setProperty("debug", Boolean.valueOf(false));
            iserver.saveProperties();
        }
        field_40452_p = new HashMap();
        output = new RConOutputStream(1460);
        queryClients = new HashMap();
        field_40448_t = (new Date()).getTime();
    }

    private void sendResponsePacket(byte abyte0[], DatagramPacket datagrampacket)
        throws SocketException, IOException
    {
        querySocket.send(new DatagramPacket(abyte0, abyte0.length, datagrampacket.getSocketAddress()));
    }

    private boolean parseIncomingPacket(DatagramPacket datagrampacket)
        throws IOException
    {
        byte abyte0[] = datagrampacket.getData();
        int i = datagrampacket.getLength();
        SocketAddress socketaddress = datagrampacket.getSocketAddress();
        logInfo((new StringBuilder()).append("Packet len ").append(i).append(" [").append(socketaddress).append("]").toString());
        if(3 > i || -2 != abyte0[0] || -3 != abyte0[1])
        {
            logInfo((new StringBuilder()).append("Invalid packet [").append(socketaddress).append("]").toString());
            return false;
        }
        logInfo((new StringBuilder()).append("Packet '").append(RConUtils.getByteAsHexString(abyte0[2])).append("' [").append(socketaddress).append("]").toString());
        switch(abyte0[2])
        {
        case 9: // '\t'
            sendAuthChallenge(datagrampacket);
            logInfo((new StringBuilder()).append("Challenge [").append(socketaddress).append("]").toString());
            return true;

        case 0: // '\0'
            if(!verifyClientAuth(datagrampacket).booleanValue())
            {
                logInfo((new StringBuilder()).append("Invalid challenge [").append(socketaddress).append("]").toString());
                return false;
            }
            if(15 != i)
            {
                RConOutputStream rconoutputstream = new RConOutputStream(1460);
                rconoutputstream.writeInt(0);
                rconoutputstream.writeByteArray(getRequestID(datagrampacket.getSocketAddress()));
                rconoutputstream.writeString(serverMotd);
                rconoutputstream.writeString("SMP");
                rconoutputstream.writeString(worldName);
                rconoutputstream.writeString(Integer.toString(getNumberOfPlayers()));
                rconoutputstream.writeString(Integer.toString(maxPlayers));
                rconoutputstream.writeShort((short)serverPort);
                rconoutputstream.writeString(queryHostname);
                sendResponsePacket(rconoutputstream.toByteArray(), datagrampacket);
                logInfo((new StringBuilder()).append("Status [").append(socketaddress).append("]").toString());
            } else
            {
                sendResponsePacket(createQueryResponse(datagrampacket), datagrampacket);
                logInfo((new StringBuilder()).append("Rules [").append(socketaddress).append("]").toString());
            }
            break;
        }
        return true;
    }

    private byte[] createQueryResponse(DatagramPacket datagrampacket)
        throws IOException
    {
        long l = System.currentTimeMillis();
        if(l < lastQueryResponseTime + 5000L)
        {
            byte abyte0[] = output.toByteArray();
            byte abyte1[] = getRequestID(datagrampacket.getSocketAddress());
            abyte0[1] = abyte1[0];
            abyte0[2] = abyte1[1];
            abyte0[3] = abyte1[2];
            abyte0[4] = abyte1[3];
            return abyte0;
        }
        lastQueryResponseTime = l;
        output.reset();
        output.writeInt(0);
        output.writeByteArray(getRequestID(datagrampacket.getSocketAddress()));
        output.writeString("splitnum");
        output.writeInt(128);
        output.writeInt(0);
        output.writeString("hostname");
        output.writeString(serverMotd);
        output.writeString("gametype");
        output.writeString("SMP");
        output.writeString("game_id");
        output.writeString("MINECRAFT");
        output.writeString("version");
        output.writeString(server.getVersionString());
        output.writeString("plugins");
        output.writeString(server.getPlugin());
        output.writeString("map");
        output.writeString(worldName);
        output.writeString("numplayers");
        output.writeString((new StringBuilder()).append("").append(getNumberOfPlayers()).toString());
        output.writeString("maxplayers");
        output.writeString((new StringBuilder()).append("").append(maxPlayers).toString());
        output.writeString("hostport");
        output.writeString((new StringBuilder()).append("").append(serverPort).toString());
        output.writeString("hostip");
        output.writeString(queryHostname);
        output.writeInt(0);
        output.writeInt(1);
        output.writeString("player_");
        output.writeInt(0);
        String as[] = server.getPlayerNamesAsList();
        byte byte0 = (byte)as.length;
        for(byte byte1 = (byte)(byte0 - 1); byte1 >= 0; byte1--)
        {
            output.writeString(as[byte1]);
        }

        output.writeInt(0);
        return output.toByteArray();
    }

    private byte[] getRequestID(SocketAddress socketaddress)
    {
        return ((RConThreadQueryAuth)queryClients.get(socketaddress)).getRequestID();
    }

    private Boolean verifyClientAuth(DatagramPacket datagrampacket)
    {
        SocketAddress socketaddress = datagrampacket.getSocketAddress();
        if(!queryClients.containsKey(socketaddress))
        {
            return Boolean.valueOf(false);
        }
        byte abyte0[] = datagrampacket.getData();
        if(((RConThreadQueryAuth)queryClients.get(socketaddress)).getRandomChallenge() != RConUtils.getBytesAsBEint(abyte0, 7, datagrampacket.getLength()))
        {
            return Boolean.valueOf(false);
        } else
        {
            return Boolean.valueOf(true);
        }
    }

    private void sendAuthChallenge(DatagramPacket datagrampacket)
        throws SocketException, IOException
    {
        RConThreadQueryAuth rconthreadqueryauth = new RConThreadQueryAuth(this, datagrampacket);
        queryClients.put(datagrampacket.getSocketAddress(), rconthreadqueryauth);
        sendResponsePacket(rconthreadqueryauth.getChallengeValue(), datagrampacket);
    }

    private void cleanQueryClientsMap()
    {
        if(!running)
        {
            return;
        }
        long l = System.currentTimeMillis();
        if(l < lastAuthCheckTime + 30000L)
        {
            return;
        }
        lastAuthCheckTime = l;
        Iterator iterator = queryClients.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(((RConThreadQueryAuth)entry.getValue()).hasExpired(l).booleanValue())
            {
                iterator.remove();
            }
        } while(true);
    }

    public void run()
    {
        log((new StringBuilder()).append("Query running on ").append(serverHostname).append(":").append(queryPort).toString());
        lastAuthCheckTime = System.currentTimeMillis();
        incomingPacket = new DatagramPacket(buffer, buffer.length);
        try
        {
            while(running) 
            {
                try
                {
                    querySocket.receive(incomingPacket);
                    cleanQueryClientsMap();
                    parseIncomingPacket(incomingPacket);
                }
                catch(SocketTimeoutException sockettimeoutexception)
                {
                    cleanQueryClientsMap();
                }
                catch(PortUnreachableException portunreachableexception) { }
                catch(IOException ioexception)
                {
                    stopWithException(ioexception);
                }
            }
        }
        finally
        {
            closeAllSockets();
        }
    }

    public void startThread()
    {
        if(running)
        {
            return;
        }
        if(0 >= queryPort || 65535 < queryPort)
        {
            logWarning((new StringBuilder()).append("Invalid query port ").append(queryPort).append(" found in '").append(server.getSettingsFilename()).append("' (queries disabled)").toString());
            return;
        }
        if(initQuerySystem())
        {
            super.startThread();
        }
    }

    private void stopWithException(Exception exception)
    {
        if(!running)
        {
            return;
        }
        logWarning((new StringBuilder()).append("Unexpected exception, buggy JRE? (").append(exception.toString()).append(")").toString());
        if(!initQuerySystem())
        {
            logSevere("Failed to recover from buggy JRE, shutting down!");
            running = false;
            server.func_40010_o();
        }
    }

    private boolean initQuerySystem()
    {
        try
        {
            querySocket = new DatagramSocket(queryPort, InetAddress.getByName(serverHostname));
            registerSocket(querySocket);
            querySocket.setSoTimeout(500);
            return true;
        }
        catch(SocketException socketexception)
        {
            logWarning((new StringBuilder()).append("Unable to initialise query system on ").append(serverHostname).append(":").append(queryPort).append(" (Socket): ").append(socketexception.getMessage()).toString());
        }
        catch(UnknownHostException unknownhostexception)
        {
            logWarning((new StringBuilder()).append("Unable to initialise query system on ").append(serverHostname).append(":").append(queryPort).append(" (Unknown Host): ").append(unknownhostexception.getMessage()).toString());
        }
        catch(Exception exception)
        {
            logWarning((new StringBuilder()).append("Unable to initialise query system on ").append(serverHostname).append(":").append(queryPort).append(" (E): ").append(exception.getMessage()).toString());
        }
        return false;
    }
}
