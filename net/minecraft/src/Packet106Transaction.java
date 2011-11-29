// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet106Transaction extends Packet
{

    public int windowId;
    public short shortWindowId;
    public boolean accepted;

    public Packet106Transaction()
    {
    }

    public Packet106Transaction(int i, short word0, boolean flag)
    {
        windowId = i;
        shortWindowId = word0;
        accepted = flag;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleTransaction(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        windowId = datainputstream.readByte();
        shortWindowId = datainputstream.readShort();
        accepted = datainputstream.readByte() != 0;
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(windowId);
        dataoutputstream.writeShort(shortWindowId);
        dataoutputstream.writeByte(accepted ? 1 : 0);
    }

    public int getPacketSize()
    {
        return 4;
    }
}
