// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            LongHashMap

class LongHashMapEntry
{

    final long key;
    Object value;
    LongHashMapEntry nextEntry;
    final int field_1026_d;

    LongHashMapEntry(int i, long l, Object obj, LongHashMapEntry longhashmapentry)
    {
        value = obj;
        nextEntry = longhashmapentry;
        key = l;
        field_1026_d = i;
    }

    public final long func_736_a()
    {
        return key;
    }

    public final Object func_735_b()
    {
        return value;
    }

    public final boolean equals(Object obj)
    {
        if(!(obj instanceof LongHashMapEntry))
        {
            return false;
        }
        LongHashMapEntry longhashmapentry = (LongHashMapEntry)obj;
        Long long1 = Long.valueOf(func_736_a());
        Long long2 = Long.valueOf(longhashmapentry.func_736_a());
        if(long1 == long2 || long1 != null && long1.equals(long2))
        {
            Object obj1 = func_735_b();
            Object obj2 = longhashmapentry.func_735_b();
            if(obj1 == obj2 || obj1 != null && obj1.equals(obj2))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return LongHashMap.getHashCode(key);
    }

    public final String toString()
    {
        return (new StringBuilder()).append(func_736_a()).append("=").append(func_735_b()).toString();
    }
}
