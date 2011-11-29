// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public class NibbleArray
{

    public final byte data[];
    private final int chunkSizeYShift;
    private final int field_35662_c;

    public NibbleArray(int i, int j)
    {
        data = new byte[i >> 1];
        chunkSizeYShift = j;
        field_35662_c = j + 4;
    }

    public NibbleArray(byte abyte0[], int i)
    {
        data = abyte0;
        chunkSizeYShift = i;
        field_35662_c = i + 4;
    }

    public int getNibble(int i, int j, int k)
    {
        int l = i << field_35662_c | k << chunkSizeYShift | j;
        int i1 = l >> 1;
        int j1 = l & 1;
        if(j1 == 0)
        {
            return data[i1] & 0xf;
        } else
        {
            return data[i1] >> 4 & 0xf;
        }
    }

    public void setNibble(int i, int j, int k, int l)
    {
        int i1 = i << field_35662_c | k << chunkSizeYShift | j;
        int j1 = i1 >> 1;
        int k1 = i1 & 1;
        if(k1 == 0)
        {
            data[j1] = (byte)(data[j1] & 0xf0 | l & 0xf);
        } else
        {
            data[j1] = (byte)(data[j1] & 0xf | (l & 0xf) << 4);
        }
    }

    public boolean isValid()
    {
        return data != null;
    }
}
