// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

// Referenced classes of package net.minecraft.src:
//            RConThreadBase, IServer, RConUtils

public class RConThreadClient extends RConThreadBase
{

    private boolean loggedIn;
    private Socket clientSocket;
    private byte buffer[];
    private String rconPassword;

    RConThreadClient(IServer iserver, Socket socket)
    {
        super(iserver);
        loggedIn = false;
        buffer = new byte[1460];
        clientSocket = socket;
        rconPassword = iserver.getStringProperty("rcon.password", "");
        log((new StringBuilder()).append("Rcon connection from: ").append(socket.getInetAddress()).toString());
    }

    public void run()
    {
        try
        {
            while(true) 
            {
                if(!running)
                {
                    break;
                }
                try
                {
                    BufferedInputStream bufferedinputstream = new BufferedInputStream(clientSocket.getInputStream());
                    int i = bufferedinputstream.read(buffer, 0, 1460);
                    if(10 > i)
                    {
                        return;
                    }
                    int j = 0;
                    int k = RConUtils.getBytesAsLEInt(buffer, 0, i);
                    if(k != i - 4)
                    {
                        return;
                    }
                    j += 4;
                    int l = RConUtils.getBytesAsLEInt(buffer, j, i);
                    j += 4;
                    int i1 = RConUtils.getRemainingBytesAsLEInt(buffer, j);
                    j += 4;
                    switch(i1)
                    {
                    case 3: // '\003'
                        String s = RConUtils.getBytesAsString(buffer, j, i);
                        j += s.length();
                        if(0 != s.length() && s.equals(rconPassword))
                        {
                            loggedIn = true;
                            sendResponse(l, 2, "");
                        } else
                        {
                            loggedIn = false;
                            sendLoginFailedResponse();
                        }
                        break;

                    case 2: // '\002'
                        if(loggedIn)
                        {
                            String s1 = RConUtils.getBytesAsString(buffer, j, i);
                            try
                            {
                                sendMultipacketResponse(l, server.handleRConCommand(s1));
                            }
                            catch(Exception exception1)
                            {
                                sendMultipacketResponse(l, (new StringBuilder()).append("Error executing: ").append(s1).append(" (").append(exception1.getMessage()).append(")").toString());
                            }
                        } else
                        {
                            sendLoginFailedResponse();
                        }
                        break;

                    default:
                        sendMultipacketResponse(l, String.format("Unknown request %s", new Object[] {
                            Integer.toHexString(i1)
                        }));
                        break;
                    }
                }
                catch(SocketTimeoutException sockettimeoutexception) { }
                catch(IOException ioexception)
                {
                    if(running)
                    {
                        log((new StringBuilder()).append("IO: ").append(ioexception.getMessage()).toString());
                    }
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        finally
        {
            closeSocket();
        }
    }

    private void sendResponse(int i, int j, String s)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1248);
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        dataoutputstream.writeInt(Integer.reverseBytes(s.length() + 10));
        dataoutputstream.writeInt(Integer.reverseBytes(i));
        dataoutputstream.writeInt(Integer.reverseBytes(j));
        dataoutputstream.writeBytes(s);
        dataoutputstream.write(0);
        dataoutputstream.write(0);
        clientSocket.getOutputStream().write(bytearrayoutputstream.toByteArray());
    }

    private void sendLoginFailedResponse()
        throws IOException
    {
        sendResponse(-1, 2, "");
    }

    private void sendMultipacketResponse(int i, String s)
        throws IOException
    {
        int j = s.length();
        do
        {
            int k = 4096 > j ? j : 4096;
            sendResponse(i, 0, s.substring(0, k));
            s = s.substring(k);
            j = s.length();
        } while(0 != j);
    }

    private void closeSocket()
    {
        if(null == clientSocket)
        {
            return;
        }
        try
        {
            clientSocket.close();
        }
        catch(IOException ioexception)
        {
            logWarning((new StringBuilder()).append("IO: ").append(ioexception.getMessage()).toString());
        }
        clientSocket = null;
    }
}
