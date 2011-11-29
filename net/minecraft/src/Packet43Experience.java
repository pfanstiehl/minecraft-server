// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet43Experience extends Packet
{

    public float experience;
    public int experienceTotal;
    public int experienceLevel;

    public Packet43Experience()
    {
    }

    public Packet43Experience(float f, int i, int j)
    {
        experience = f;
        experienceTotal = i;
        experienceLevel = j;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        experience = datainputstream.readFloat();
        experienceLevel = datainputstream.readShort();
        experienceTotal = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeFloat(experience);
        dataoutputstream.writeShort(experienceLevel);
        dataoutputstream.writeShort(experienceTotal);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleExperience(this);
    }

    public int getPacketSize()
    {
        return 4;
    }
}
