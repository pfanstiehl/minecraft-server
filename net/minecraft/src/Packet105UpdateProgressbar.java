// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet105UpdateProgressbar extends Packet
{

    public int windowId;
    public int progressBar;
    public int progressBarValue;

    public Packet105UpdateProgressbar()
    {
    }

    public Packet105UpdateProgressbar(int i, int j, int k)
    {
        windowId = i;
        progressBar = j;
        progressBarValue = k;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleUpdateProgressbar(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        windowId = datainputstream.readByte();
        progressBar = datainputstream.readShort();
        progressBarValue = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(windowId);
        dataoutputstream.writeShort(progressBar);
        dataoutputstream.writeShort(progressBarValue);
    }

    public int getPacketSize()
    {
        return 5;
    }
}
