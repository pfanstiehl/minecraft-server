// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Packet, ItemStack, NetHandler

public class Packet104WindowItems extends Packet
{

    public int windowId;
    public ItemStack itemStack[];

    public Packet104WindowItems()
    {
    }

    public Packet104WindowItems(int i, List list)
    {
        windowId = i;
        itemStack = new ItemStack[list.size()];
        for(int j = 0; j < itemStack.length; j++)
        {
            ItemStack itemstack = (ItemStack)list.get(j);
            itemStack[j] = itemstack != null ? itemstack.copy() : null;
        }

    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        windowId = datainputstream.readByte();
        short word0 = datainputstream.readShort();
        itemStack = new ItemStack[word0];
        for(int i = 0; i < word0; i++)
        {
            itemStack[i] = func_40262_b(datainputstream);
        }

    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(windowId);
        dataoutputstream.writeShort(itemStack.length);
        for(int i = 0; i < itemStack.length; i++)
        {
            writeItemStack(itemStack[i], dataoutputstream);
        }

    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleWindowItems(this);
    }

    public int getPacketSize()
    {
        return 3 + itemStack.length * 5;
    }
}
