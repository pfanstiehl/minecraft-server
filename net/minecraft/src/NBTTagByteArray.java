// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            NBTBase

public class NBTTagByteArray extends NBTBase
{

    public byte byteArray[];

    public NBTTagByteArray(String s)
    {
        super(s);
    }

    public NBTTagByteArray(String s, byte abyte0[])
    {
        super(s);
        byteArray = abyte0;
    }

    void writeTagContents(DataOutput dataoutput)
        throws IOException
    {
        dataoutput.writeInt(byteArray.length);
        dataoutput.write(byteArray);
    }

    void readTagContents(DataInput datainput)
        throws IOException
    {
        int i = datainput.readInt();
        byteArray = new byte[i];
        datainput.readFully(byteArray);
    }

    public byte getType()
    {
        return 7;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[").append(byteArray.length).append(" bytes]").toString();
    }

    public boolean equals(Object obj)
    {
        if(super.equals(obj))
        {
            NBTTagByteArray nbttagbytearray = (NBTTagByteArray)obj;
            return byteArray == null && nbttagbytearray.byteArray == null || byteArray != null && byteArray.equals(nbttagbytearray.byteArray);
        } else
        {
            return false;
        }
    }

    public NBTBase cloneTag()
    {
        byte abyte0[] = new byte[byteArray.length];
        System.arraycopy(byteArray, 0, abyte0, 0, byteArray.length);
        return new NBTTagByteArray(getKey(), abyte0);
    }
}
