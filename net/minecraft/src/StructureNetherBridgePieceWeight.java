// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


class StructureNetherBridgePieceWeight
{

    public Class field_40655_a;
    public final int field_40653_b;
    public int field_40654_c;
    public int field_40651_d;
    public boolean field_40652_e;

    public StructureNetherBridgePieceWeight(Class class1, int i, int j, boolean flag)
    {
        field_40655_a = class1;
        field_40653_b = i;
        field_40651_d = j;
        field_40652_e = flag;
    }

    public StructureNetherBridgePieceWeight(Class class1, int i, int j)
    {
        this(class1, i, j, false);
    }

    public boolean func_40649_a(int i)
    {
        return field_40651_d == 0 || field_40654_c < field_40651_d;
    }

    public boolean func_40650_a()
    {
        return field_40651_d == 0 || field_40654_c < field_40651_d;
    }
}
