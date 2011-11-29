// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, BiomeDecorator

public class BiomeGenPlains extends BiomeGenBase
{

    protected BiomeGenPlains(int i)
    {
        super(i);
        decorator.treesPerChunk = -999;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 10;
    }
}
