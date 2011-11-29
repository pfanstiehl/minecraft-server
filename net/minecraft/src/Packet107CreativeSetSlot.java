// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler, ItemStack

public class Packet107CreativeSetSlot extends Packet
{

    public int slot;
    public ItemStack itemStack;

    public Packet107CreativeSetSlot()
    {
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleCreativeSlot(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        slot = datainputstream.readShort();
        itemStack = func_40262_b(datainputstream);
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(slot);
        writeItemStack(itemStack, dataoutputstream);
    }

    public int getPacketSize()
    {
        return 8;
    }
}
