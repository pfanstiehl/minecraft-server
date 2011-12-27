// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class PacketCount
{

    public static boolean allowCounting = true;
    private static final Map field_40617_b = new HashMap();
    private static final Map field_40618_c = new HashMap();
    private static final Object lock = new Object();

    public PacketCount()
    {
    }

    public static void countPacket(int i, long l)
    {
        if(!allowCounting)
        {
            return;
        }
        synchronized(lock)
        {
            if(field_40617_b.containsKey(Integer.valueOf(i)))
            {
                field_40617_b.put(Integer.valueOf(i), Long.valueOf(((Long)field_40617_b.get(Integer.valueOf(i))).longValue() + 1L));
                field_40618_c.put(Integer.valueOf(i), Long.valueOf(((Long)field_40618_c.get(Integer.valueOf(i))).longValue() + l));
            } else
            {
                field_40617_b.put(Integer.valueOf(i), Long.valueOf(1L));
                field_40618_c.put(Integer.valueOf(i), Long.valueOf(l));
            }
        }
    }

}
