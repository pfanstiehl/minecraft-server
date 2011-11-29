// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet100OpenWindow extends Packet
{

    public int windowId;
    public int inventoryType;
    public String windowTitle;
    public int slotsCount;

    public Packet100OpenWindow()
    {
    }

    public Packet100OpenWindow(int i, int j, String s, int k)
    {
        windowId = i;
        inventoryType = j;
        windowTitle = s;
        slotsCount = k;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleOpenWindow(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        windowId = datainputstream.readByte();
        inventoryType = datainputstream.readByte();
        windowTitle = readString(datainputstream, 16);
        slotsCount = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(windowId);
        dataoutputstream.writeByte(inventoryType);
        writeString(windowTitle, dataoutputstream);
        dataoutputstream.writeByte(slotsCount);
    }

    public int getPacketSize()
    {
        return 3 + windowTitle.length();
    }
}
