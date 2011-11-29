// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            World

public abstract class WorldGenerator
{

    private final boolean field_41044_a;

    public WorldGenerator()
    {
        field_41044_a = false;
    }

    public WorldGenerator(boolean flag)
    {
        field_41044_a = flag;
    }

    public abstract boolean generate(World world, Random random, int i, int j, int k);

    public void func_420_a(double d, double d1, double d2)
    {
    }

    protected void func_41043_a(World world, int i, int j, int k, int l, int i1)
    {
        if(field_41044_a)
        {
            world.setBlockAndMetadataWithNotify(i, j, k, l, i1);
        } else
        {
            world.setBlockAndMetadata(i, j, k, l, i1);
        }
    }
}
