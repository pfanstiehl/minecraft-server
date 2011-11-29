// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet254ServerPing extends Packet
{

    public Packet254ServerPing()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
    {
    }

    public void writePacketData(DataOutputStream dataoutputstream)
    {
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleServerPing(this);
    }

    public int getPacketSize()
    {
        return 0;
    }
}
