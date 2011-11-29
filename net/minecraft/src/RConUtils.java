// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public class RConUtils
{

    public static char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

    public RConUtils()
    {
    }

    public static String getBytesAsString(byte abyte0[], int i, int j)
    {
        int k = j - 1;
        int l;
        for(l = i <= k ? i : k; 0 != abyte0[l] && l < k; l++) { }
        return new String(abyte0, i, l - i);
    }

    public static int getRemainingBytesAsLEInt(byte abyte0[], int i)
    {
        return getBytesAsLEInt(abyte0, i, abyte0.length);
    }

    public static int getBytesAsLEInt(byte abyte0[], int i, int j)
    {
        if(0 > j - i - 4)
        {
            return 0;
        } else
        {
            return abyte0[i + 3] << 24 | (abyte0[i + 2] & 0xff) << 16 | (abyte0[i + 1] & 0xff) << 8 | abyte0[i] & 0xff;
        }
    }

    public static int getBytesAsBEint(byte abyte0[], int i, int j)
    {
        if(0 > j - i - 4)
        {
            return 0;
        } else
        {
            return abyte0[i] << 24 | (abyte0[i + 1] & 0xff) << 16 | (abyte0[i + 2] & 0xff) << 8 | abyte0[i + 3] & 0xff;
        }
    }

    public static String getByteAsHexString(byte byte0)
    {
        return (new StringBuilder()).append("").append(hexDigits[(byte0 & 0xf0) >>> 4]).append(hexDigits[byte0 & 0xf]).toString();
    }

}
