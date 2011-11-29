// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WeightedRandomChoice

public class SpawnListEntry extends WeightedRandomChoice
{

    public Class entityClass;
    public int field_35484_b;
    public int field_35485_c;

    public SpawnListEntry(Class class1, int i, int j, int k)
    {
        super(i);
        entityClass = class1;
        field_35484_b = j;
        field_35485_c = k;
    }
}
