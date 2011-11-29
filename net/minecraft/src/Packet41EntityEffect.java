// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, PotionEffect, NetHandler

public class Packet41EntityEffect extends Packet
{

    public int entityId;
    public byte effectId;
    public byte effectAmp;
    public short duration;

    public Packet41EntityEffect()
    {
    }

    public Packet41EntityEffect(int i, PotionEffect potioneffect)
    {
        entityId = i;
        effectId = (byte)(potioneffect.getPotionID() & 0xff);
        effectAmp = (byte)(potioneffect.getAmplifier() & 0xff);
        duration = (short)potioneffect.getDuration();
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        entityId = datainputstream.readInt();
        effectId = datainputstream.readByte();
        effectAmp = datainputstream.readByte();
        duration = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(entityId);
        dataoutputstream.writeByte(effectId);
        dataoutputstream.writeByte(effectAmp);
        dataoutputstream.writeShort(duration);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleEntityEffect(this);
    }

    public int getPacketSize()
    {
        return 8;
    }
}
