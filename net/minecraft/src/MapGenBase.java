// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            World, IChunkProvider

public class MapGenBase
{

    protected int field_947_a;
    protected Random rand;
    protected World worldObj;

    public MapGenBase()
    {
        field_947_a = 8;
        rand = new Random();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        int k = field_947_a;
        worldObj = world;
        rand.setSeed(world.getRandomSeed());
        long l = rand.nextLong();
        long l1 = rand.nextLong();
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                long l2 = (long)i1 * l;
                long l3 = (long)j1 * l1;
                rand.setSeed(l2 ^ l3 ^ world.getRandomSeed());
                recursiveGenerate(world, i1, j1, i, j, abyte0);
            }

        }

    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
    }
}
