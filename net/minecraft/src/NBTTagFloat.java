// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            NBTBase

public class NBTTagFloat extends NBTBase
{

    public float floatValue;

    public NBTTagFloat(String s)
    {
        super(s);
    }

    public NBTTagFloat(String s, float f)
    {
        super(s);
        floatValue = f;
    }

    void writeTagContents(DataOutput dataoutput)
        throws IOException
    {
        dataoutput.writeFloat(floatValue);
    }

    void readTagContents(DataInput datainput)
        throws IOException
    {
        floatValue = datainput.readFloat();
    }

    public byte getType()
    {
        return 5;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(floatValue).toString();
    }

    public NBTBase cloneTag()
    {
        return new NBTTagFloat(getKey(), floatValue);
    }

    public boolean equals(Object obj)
    {
        if(super.equals(obj))
        {
            NBTTagFloat nbttagfloat = (NBTTagFloat)obj;
            return floatValue == nbttagfloat.floatValue;
        } else
        {
            return false;
        }
    }
}
