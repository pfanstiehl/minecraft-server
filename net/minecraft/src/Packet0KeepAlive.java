// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet0KeepAlive extends Packet
{

    public int randomId;

    public Packet0KeepAlive()
    {
    }

    public Packet0KeepAlive(int i)
    {
        randomId = i;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleKeepAlive(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        randomId = datainputstream.readInt();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(randomId);
    }

    public int getPacketSize()
    {
        return 4;
    }
}
