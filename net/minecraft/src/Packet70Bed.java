// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet70Bed extends Packet
{

    public static final String bedChat[] = {
        "tile.bed.notValid", null, null, "gameMode.changed"
    };
    public int bedState;
    public int gameMode;

    public Packet70Bed()
    {
    }

    public Packet70Bed(int i, int j)
    {
        bedState = i;
        gameMode = j;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        bedState = datainputstream.readByte();
        gameMode = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(bedState);
        dataoutputstream.writeByte(gameMode);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleBed(this);
    }

    public int getPacketSize()
    {
        return 2;
    }

}
