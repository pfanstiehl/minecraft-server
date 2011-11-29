// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Packet, DataWatcher, NetHandler

public class Packet40EntityMetadata extends Packet
{

    public int entityId;
    private List metadata;

    public Packet40EntityMetadata()
    {
    }

    public Packet40EntityMetadata(int i, DataWatcher datawatcher)
    {
        entityId = i;
        metadata = datawatcher.getChangedObjects();
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        entityId = datainputstream.readInt();
        metadata = DataWatcher.readWatchableObjects(datainputstream);
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(entityId);
        DataWatcher.writeObjectsInListToStream(metadata, dataoutputstream);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleEntityMetadata(this);
    }

    public int getPacketSize()
    {
        return 5;
    }
}
