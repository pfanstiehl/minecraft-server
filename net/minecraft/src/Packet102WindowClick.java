// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler, ItemStack

public class Packet102WindowClick extends Packet
{

    public int window_Id;
    public int inventorySlot;
    public int mouseClick;
    public short action;
    public ItemStack itemStack;
    public boolean holdingShift;

    public Packet102WindowClick()
    {
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleWindowClick(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        window_Id = datainputstream.readByte();
        inventorySlot = datainputstream.readShort();
        mouseClick = datainputstream.readByte();
        action = datainputstream.readShort();
        holdingShift = datainputstream.readBoolean();
        itemStack = func_40262_b(datainputstream);
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(window_Id);
        dataoutputstream.writeShort(inventorySlot);
        dataoutputstream.writeByte(mouseClick);
        dataoutputstream.writeShort(action);
        dataoutputstream.writeBoolean(holdingShift);
        writeItemStack(itemStack, dataoutputstream);
    }

    public int getPacketSize()
    {
        return 11;
    }
}
