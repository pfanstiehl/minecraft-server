// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet8UpdateHealth extends Packet
{

    public int healthMP;
    public int food;
    public float foodSaturation;

    public Packet8UpdateHealth()
    {
    }

    public Packet8UpdateHealth(int i, int j, float f)
    {
        healthMP = i;
        food = j;
        foodSaturation = f;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        healthMP = datainputstream.readShort();
        food = datainputstream.readShort();
        foodSaturation = datainputstream.readFloat();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(healthMP);
        dataoutputstream.writeShort(food);
        dataoutputstream.writeFloat(foodSaturation);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleHealth(this);
    }

    public int getPacketSize()
    {
        return 8;
    }
}
