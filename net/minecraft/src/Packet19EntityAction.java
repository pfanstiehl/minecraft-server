// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet19EntityAction extends Packet
{

    public int entityId;
    public int state;

    public Packet19EntityAction()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        entityId = datainputstream.readInt();
        state = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(entityId);
        dataoutputstream.writeByte(state);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleEntityAction(this);
    }

    public int getPacketSize()
    {
        return 5;
    }
}
