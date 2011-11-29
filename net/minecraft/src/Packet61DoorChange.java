// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet61DoorChange extends Packet
{

    public int sfxID;
    public int auxData;
    public int posX;
    public int posY;
    public int posZ;

    public Packet61DoorChange()
    {
    }

    public Packet61DoorChange(int i, int j, int k, int l, int i1)
    {
        sfxID = i;
        posX = j;
        posY = k;
        posZ = l;
        auxData = i1;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        sfxID = datainputstream.readInt();
        posX = datainputstream.readInt();
        posY = datainputstream.readByte();
        posZ = datainputstream.readInt();
        auxData = datainputstream.readInt();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(sfxID);
        dataoutputstream.writeInt(posX);
        dataoutputstream.writeByte(posY);
        dataoutputstream.writeInt(posZ);
        dataoutputstream.writeInt(auxData);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleDoorChange(this);
    }

    public int getPacketSize()
    {
        return 20;
    }
}
