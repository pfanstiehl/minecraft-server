// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler, ItemStack

public class Packet15Place extends Packet
{

    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int direction;
    public ItemStack itemStack;

    public Packet15Place()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        xPosition = datainputstream.readInt();
        yPosition = datainputstream.read();
        zPosition = datainputstream.readInt();
        direction = datainputstream.read();
        itemStack = func_40262_b(datainputstream);
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(xPosition);
        dataoutputstream.write(yPosition);
        dataoutputstream.writeInt(zPosition);
        dataoutputstream.write(direction);
        writeItemStack(itemStack, dataoutputstream);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handlePlace(this);
    }

    public int getPacketSize()
    {
        return 15;
    }
}
