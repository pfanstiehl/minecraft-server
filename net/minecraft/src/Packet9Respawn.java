// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet9Respawn extends Packet
{

    public long mapSeed;
    public int respawnDimension;
    public int difficultySetting;
    public int worldHeight;
    public int creativeMode;

    public Packet9Respawn()
    {
    }

    public Packet9Respawn(byte byte0, byte byte1, long l, int i, int j)
    {
        respawnDimension = byte0;
        difficultySetting = byte1;
        mapSeed = l;
        worldHeight = i;
        creativeMode = j;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleRespawn(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        respawnDimension = datainputstream.readByte();
        difficultySetting = datainputstream.readByte();
        creativeMode = datainputstream.readByte();
        worldHeight = datainputstream.readShort();
        mapSeed = datainputstream.readLong();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(respawnDimension);
        dataoutputstream.writeByte(difficultySetting);
        dataoutputstream.writeByte(creativeMode);
        dataoutputstream.writeShort(worldHeight);
        dataoutputstream.writeLong(mapSeed);
    }

    public int getPacketSize()
    {
        return 13;
    }
}
