// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemBlock, Block

public class ItemMetadata extends ItemBlock
{

    private Block field_35420_a;

    public ItemMetadata(int i, Block block)
    {
        super(i);
        field_35420_a = block;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int i)
    {
        return i;
    }
}
