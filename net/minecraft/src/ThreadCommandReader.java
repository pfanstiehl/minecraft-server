// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import net.minecraft.server.MinecraftServer;

public class ThreadCommandReader extends Thread
{

    final MinecraftServer mcServer; /* synthetic field */

    public ThreadCommandReader(MinecraftServer minecraftserver)
    {
        mcServer = minecraftserver;
//        super();
    }

    public void run()
    {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try
        {
            while(!mcServer.serverStopped && MinecraftServer.isServerRunning(mcServer) && (s = bufferedreader.readLine()) != null) 
            {
                mcServer.addCommand(s, mcServer);
            }
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }
}
