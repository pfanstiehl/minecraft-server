// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenClay, WorldGenSand, Block, WorldGenMinable, 
//            WorldGenFlowers, BlockFlower, WorldGenBigMushroom, WorldGenReed, 
//            WorldGenCactus, MapGenWaterlily, World, WorldGenerator, 
//            BiomeGenBase, WorldGenTallGrass, BlockTallGrass, WorldGenDeadBush, 
//            BlockDeadBush, WorldGenPumpkin, WorldGenLiquids

public class BiomeDecorator
{

    protected World curWorldObj;
    protected Random randomGenerator;
    protected int chunk_X;
    protected int chunk_Z;
    protected BiomeGenBase biomeGenerator;
    protected WorldGenerator clayGenerator;
    protected WorldGenerator sandGenerator;
    protected WorldGenerator gravelGenerator;
    protected WorldGenerator dirtGenerator;
    protected WorldGenerator gravelGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator ironGen;
    protected WorldGenerator goldGen;
    protected WorldGenerator redstoneGen;
    protected WorldGenerator diamondGen;
    protected WorldGenerator lapisGen;
    protected WorldGenerator plantYellowGen;
    protected WorldGenerator plantRedGen;
    protected WorldGenerator mushroomBrownGen;
    protected WorldGenerator mushroomRedGen;
    protected WorldGenerator field_40320_u;
    protected WorldGenerator field_35286_p;
    protected WorldGenerator field_35285_q;
    protected WorldGenerator field_40322_x;
    protected int field_40321_y;
    protected int treesPerChunk;
    protected int flowersPerChunk;
    protected int grassPerChunk;
    protected int deadBushPerChunk;
    protected int mushroomsPerChunk;
    protected int reedsPerChunk;
    protected int field_35289_x;
    protected int field_35288_y;
    protected int field_35287_z;
    protected int clayPerChunk;
    protected int field_40318_J;
    public boolean field_40319_K;

    public BiomeDecorator(BiomeGenBase biomegenbase)
    {
        clayGenerator = new WorldGenClay(4);
        sandGenerator = new WorldGenSand(7, Block.sand.blockID);
        gravelGenerator = new WorldGenSand(6, Block.gravel.blockID);
        dirtGenerator = new WorldGenMinable(Block.dirt.blockID, 32);
        gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
        coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
        plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
        mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        field_40320_u = new WorldGenBigMushroom();
        field_35286_p = new WorldGenReed();
        field_35285_q = new WorldGenCactus();
        field_40322_x = new MapGenWaterlily();
        field_40321_y = 0;
        treesPerChunk = 0;
        flowersPerChunk = 2;
        grassPerChunk = 1;
        deadBushPerChunk = 0;
        mushroomsPerChunk = 0;
        reedsPerChunk = 0;
        field_35289_x = 0;
        field_35288_y = 1;
        field_35287_z = 3;
        clayPerChunk = 1;
        field_40318_J = 0;
        field_40319_K = true;
        biomeGenerator = biomegenbase;
    }

    public void Decorates(World world, Random random, int i, int j)
    {
        if(curWorldObj != null)
        {
            throw new RuntimeException("Already decorating!!");
        } else
        {
            curWorldObj = world;
            randomGenerator = random;
            chunk_X = i;
            chunk_Z = j;
            func_35256_b();
            curWorldObj = null;
            randomGenerator = null;
            return;
        }
    }

    protected void func_35256_b()
    {
        generateOres();
        for(int i = 0; i < field_35287_z; i++)
        {
            int i1 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k5 = chunk_Z + randomGenerator.nextInt(16) + 8;
            sandGenerator.generate(curWorldObj, randomGenerator, i1, curWorldObj.findTopSolidBlock(i1, k5), k5);
        }

        for(int j = 0; j < clayPerChunk; j++)
        {
            int j1 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l5 = chunk_Z + randomGenerator.nextInt(16) + 8;
            clayGenerator.generate(curWorldObj, randomGenerator, j1, curWorldObj.findTopSolidBlock(j1, l5), l5);
        }

        for(int k = 0; k < field_35288_y; k++)
        {
            int k1 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i6 = chunk_Z + randomGenerator.nextInt(16) + 8;
            sandGenerator.generate(curWorldObj, randomGenerator, k1, curWorldObj.findTopSolidBlock(k1, i6), i6);
        }

        int l = treesPerChunk;
        if(randomGenerator.nextInt(10) == 0)
        {
            l++;
        }
        for(int l1 = 0; l1 < l; l1++)
        {
            int j6 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k10 = chunk_Z + randomGenerator.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomeGenerator.getRandomWorldGenForTrees(randomGenerator);
            worldgenerator.func_420_a(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(curWorldObj, randomGenerator, j6, curWorldObj.getHeightValue(j6, k10), k10);
        }

        for(int i2 = 0; i2 < field_40318_J; i2++)
        {
            int k6 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l10 = chunk_Z + randomGenerator.nextInt(16) + 8;
            field_40320_u.generate(curWorldObj, randomGenerator, k6, curWorldObj.getHeightValue(k6, l10), l10);
        }

        for(int j2 = 0; j2 < flowersPerChunk; j2++)
        {
            int l6 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i11 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int l14 = chunk_Z + randomGenerator.nextInt(16) + 8;
            plantYellowGen.generate(curWorldObj, randomGenerator, l6, i11, l14);
            if(randomGenerator.nextInt(4) == 0)
            {
                int i7 = chunk_X + randomGenerator.nextInt(16) + 8;
                int j11 = randomGenerator.nextInt(curWorldObj.worldYMax);
                int i15 = chunk_Z + randomGenerator.nextInt(16) + 8;
                plantRedGen.generate(curWorldObj, randomGenerator, i7, j11, i15);
            }
        }

        for(int k2 = 0; k2 < grassPerChunk; k2++)
        {
            int j7 = 1;
            int k11 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j15 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int l17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenTallGrass(Block.tallGrass.blockID, j7)).generate(curWorldObj, randomGenerator, k11, j15, l17);
        }

        for(int l2 = 0; l2 < deadBushPerChunk; l2++)
        {
            int k7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l11 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int k15 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.deadBush.blockID)).generate(curWorldObj, randomGenerator, k7, l11, k15);
        }

        for(int i3 = 0; i3 < field_40321_y; i3++)
        {
            int l7 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i12 = chunk_Z + randomGenerator.nextInt(16) + 8;
            int l15;
            for(l15 = randomGenerator.nextInt(curWorldObj.worldYMax); l15 > 0 && curWorldObj.getBlockId(l7, l15 - 1, i12) == 0; l15--) { }
            field_40322_x.generate(curWorldObj, randomGenerator, l7, l15, i12);
        }

        for(int j3 = 0; j3 < mushroomsPerChunk; j3++)
        {
            if(randomGenerator.nextInt(4) == 0)
            {
                int i8 = chunk_X + randomGenerator.nextInt(16) + 8;
                int j12 = chunk_Z + randomGenerator.nextInt(16) + 8;
                int i16 = curWorldObj.getHeightValue(i8, j12);
                mushroomBrownGen.generate(curWorldObj, randomGenerator, i8, i16, j12);
            }
            if(randomGenerator.nextInt(8) == 0)
            {
                int j8 = chunk_X + randomGenerator.nextInt(16) + 8;
                int k12 = chunk_Z + randomGenerator.nextInt(16) + 8;
                int j16 = randomGenerator.nextInt(curWorldObj.worldYMax);
                mushroomRedGen.generate(curWorldObj, randomGenerator, j8, j16, k12);
            }
        }

        if(randomGenerator.nextInt(4) == 0)
        {
            int k3 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k8 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int l12 = chunk_Z + randomGenerator.nextInt(16) + 8;
            mushroomBrownGen.generate(curWorldObj, randomGenerator, k3, k8, l12);
        }
        if(randomGenerator.nextInt(8) == 0)
        {
            int l3 = chunk_X + randomGenerator.nextInt(16) + 8;
            int l8 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int i13 = chunk_Z + randomGenerator.nextInt(16) + 8;
            mushroomRedGen.generate(curWorldObj, randomGenerator, l3, l8, i13);
        }
        for(int i4 = 0; i4 < reedsPerChunk; i4++)
        {
            int i9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int j13 = chunk_Z + randomGenerator.nextInt(16) + 8;
            int k16 = randomGenerator.nextInt(curWorldObj.worldYMax);
            field_35286_p.generate(curWorldObj, randomGenerator, i9, k16, j13);
        }

        for(int j4 = 0; j4 < 10; j4++)
        {
            int j9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k13 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int l16 = chunk_Z + randomGenerator.nextInt(16) + 8;
            field_35286_p.generate(curWorldObj, randomGenerator, j9, k13, l16);
        }

        if(randomGenerator.nextInt(32) == 0)
        {
            int k4 = chunk_X + randomGenerator.nextInt(16) + 8;
            int k9 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int l13 = chunk_Z + randomGenerator.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(curWorldObj, randomGenerator, k4, k9, l13);
        }
        for(int l4 = 0; l4 < field_35289_x; l4++)
        {
            int l9 = chunk_X + randomGenerator.nextInt(16) + 8;
            int i14 = randomGenerator.nextInt(curWorldObj.worldYMax);
            int i17 = chunk_Z + randomGenerator.nextInt(16) + 8;
            field_35285_q.generate(curWorldObj, randomGenerator, l9, i14, i17);
        }

        if(field_40319_K)
        {
            for(int i5 = 0; i5 < 50; i5++)
            {
                int i10 = chunk_X + randomGenerator.nextInt(16) + 8;
                int j14 = randomGenerator.nextInt(randomGenerator.nextInt(curWorldObj.worldYMax - 8) + 8);
                int j17 = chunk_Z + randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(curWorldObj, randomGenerator, i10, j14, j17);
            }

            for(int j5 = 0; j5 < 20; j5++)
            {
                int j10 = chunk_X + randomGenerator.nextInt(16) + 8;
                int k14 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(curWorldObj.worldYMax - 16) + 8) + 8);
                int k17 = chunk_Z + randomGenerator.nextInt(16) + 8;
                (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(curWorldObj, randomGenerator, j10, k14, k17);
            }

        }
    }

    protected void func_35257_a(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + randomGenerator.nextInt(16);
            int j1 = randomGenerator.nextInt(k - j) + j;
            int k1 = chunk_Z + randomGenerator.nextInt(16);
            worldgenerator.generate(curWorldObj, randomGenerator, i1, j1, k1);
        }

    }

    protected void func_35254_b(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + randomGenerator.nextInt(16);
            int j1 = randomGenerator.nextInt(k) + randomGenerator.nextInt(k) + (j - k);
            int k1 = chunk_Z + randomGenerator.nextInt(16);
            worldgenerator.generate(curWorldObj, randomGenerator, i1, j1, k1);
        }

    }

    protected void generateOres()
    {
        func_35257_a(20, dirtGenerator, 0, curWorldObj.worldYMax);
        func_35257_a(10, gravelGen, 0, curWorldObj.worldYMax);
        func_35257_a(20, coalGen, 0, curWorldObj.worldYMax);
        func_35257_a(20, ironGen, 0, curWorldObj.worldYMax / 2);
        func_35257_a(2, goldGen, 0, curWorldObj.worldYMax / 4);
        func_35257_a(8, redstoneGen, 0, curWorldObj.worldYMax / 8);
        func_35257_a(1, diamondGen, 0, curWorldObj.worldYMax / 8);
        func_35254_b(1, lapisGen, curWorldObj.worldYMax / 8, curWorldObj.worldYMax / 8);
    }
}
