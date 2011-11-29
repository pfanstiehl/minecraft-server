// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet201PlayerInfo extends Packet
{

    public String name;
    public boolean isConnected;
    public int ping;

    public Packet201PlayerInfo()
    {
    }

    public Packet201PlayerInfo(String s, boolean flag, int i)
    {
        name = s;
        isConnected = flag;
        ping = i;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        name = readString(datainputstream, 16);
        isConnected = datainputstream.readByte() != 0;
        ping = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        writeString(name, dataoutputstream);
        dataoutputstream.writeByte(isConnected ? 1 : 0);
        dataoutputstream.writeShort(ping);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handlePlayerInfo(this);
    }

    public int getPacketSize()
    {
        return name.length() + 2 + 1 + 2;
    }
}
