// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, PotionEffect, NetHandler

public class Packet42RemoveEntityEffect extends Packet
{

    public int entityId;
    public byte effectId;

    public Packet42RemoveEntityEffect()
    {
    }

    public Packet42RemoveEntityEffect(int i, PotionEffect potioneffect)
    {
        entityId = i;
        effectId = (byte)(potioneffect.getPotionID() & 0xff);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        entityId = datainputstream.readInt();
        effectId = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(entityId);
        dataoutputstream.writeByte(effectId);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleRemoveEntityEffect(this);
    }

    public int getPacketSize()
    {
        return 5;
    }
}
