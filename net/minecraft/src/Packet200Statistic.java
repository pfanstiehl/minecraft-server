// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet200Statistic extends Packet
{

    public int statisticId;
    public int amount;

    public Packet200Statistic()
    {
    }

    public Packet200Statistic(int i, int j)
    {
        statisticId = i;
        amount = j;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleStatistic(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        statisticId = datainputstream.readInt();
        amount = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(statisticId);
        dataoutputstream.writeByte(amount);
    }

    public int getPacketSize()
    {
        return 6;
    }
}
