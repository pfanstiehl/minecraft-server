// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            NBTBase

public class NBTTagByte extends NBTBase
{

    public byte byteValue;

    public NBTTagByte(String s)
    {
        super(s);
    }

    public NBTTagByte(String s, byte byte0)
    {
        super(s);
        byteValue = byte0;
    }

    void writeTagContents(DataOutput dataoutput)
        throws IOException
    {
        dataoutput.writeByte(byteValue);
    }

    void readTagContents(DataInput datainput)
        throws IOException
    {
        byteValue = datainput.readByte();
    }

    public byte getType()
    {
        return 1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(byteValue).toString();
    }

    public boolean equals(Object obj)
    {
        if(super.equals(obj))
        {
            NBTTagByte nbttagbyte = (NBTTagByte)obj;
            return byteValue == nbttagbyte.byteValue;
        } else
        {
            return false;
        }
    }

    public NBTBase cloneTag()
    {
        return new NBTTagByte(getKey(), byteValue);
    }
}
