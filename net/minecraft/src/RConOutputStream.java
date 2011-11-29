// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

public class RConOutputStream
{

    private ByteArrayOutputStream byteArrayOutput;
    private DataOutputStream output;

    public RConOutputStream(int i)
    {
        byteArrayOutput = new ByteArrayOutputStream(i);
        output = new DataOutputStream(byteArrayOutput);
    }

    public void writeByteArray(byte abyte0[])
        throws IOException
    {
        output.write(abyte0, 0, abyte0.length);
    }

    public void writeString(String s)
        throws IOException
    {
        output.writeBytes(s);
        output.write(0);
    }

    public void writeInt(int i)
        throws IOException
    {
        output.write(i);
    }

    public void writeShort(short word0)
        throws IOException
    {
        output.writeShort(Short.reverseBytes(word0));
    }

    public byte[] toByteArray()
    {
        return byteArrayOutput.toByteArray();
    }

    public void reset()
    {
        byteArrayOutput.reset();
    }
}
