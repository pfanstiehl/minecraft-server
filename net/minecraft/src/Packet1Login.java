// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet1Login extends Packet
{

    public int protocolVersion;
    public String username;
    public long mapSeed;
    public int serverMode;
    public byte worldType;
    public byte difficultySetting;
    public byte worldHeight;
    public byte maxPlayers;

    public Packet1Login()
    {
    }

    public Packet1Login(String s, int i, long l, int j, byte byte0, byte byte1, 
            byte byte2, byte byte3)
    {
        username = s;
        protocolVersion = i;
        mapSeed = l;
        worldType = byte0;
        difficultySetting = byte1;
        serverMode = j;
        worldHeight = byte2;
        maxPlayers = byte3;
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        protocolVersion = datainputstream.readInt();
        username = readString(datainputstream, 16);
        mapSeed = datainputstream.readLong();
        serverMode = datainputstream.readInt();
        worldType = datainputstream.readByte();
        difficultySetting = datainputstream.readByte();
        worldHeight = datainputstream.readByte();
        maxPlayers = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(protocolVersion);
        writeString(username, dataoutputstream);
        dataoutputstream.writeLong(mapSeed);
        dataoutputstream.writeInt(serverMode);
        dataoutputstream.writeByte(worldType);
        dataoutputstream.writeByte(difficultySetting);
        dataoutputstream.writeByte(worldHeight);
        dataoutputstream.writeByte(maxPlayers);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleLogin(this);
    }

    public int getPacketSize()
    {
        return 4 + username.length() + 4 + 7 + 4;
    }
}
