// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public final class ThreadServerApplication extends Thread
{

    final MinecraftServer mcServer; /* synthetic field */

    public ThreadServerApplication(String s, MinecraftServer minecraftserver)
    {
        super(s);
        mcServer = minecraftserver;
    }

    public void run()
    {
        mcServer.run();
    }
}
