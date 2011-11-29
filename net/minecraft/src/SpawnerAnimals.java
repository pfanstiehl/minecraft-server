// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            World, ChunkPosition, EntityPlayer, MathHelper, 
//            ChunkCoordIntPair, EnumCreatureType, ChunkCoordinates, SpawnListEntry, 
//            EntityLiving, Material, EntitySpider, EntitySkeleton, 
//            EntitySheep, BiomeGenBase, WeightedRandom, EntityZombie

public final class SpawnerAnimals
{

    private static HashMap eligibleChunksForSpawning = new HashMap();
    protected static final Class nightSpawnEntities[];

    public SpawnerAnimals()
    {
    }

    protected static ChunkPosition getRandomSpawningPointInChunk(World world, int i, int j)
    {
        int k = i + world.rand.nextInt(16);
        int l = world.rand.nextInt(world.worldYMax);
        int i1 = j + world.rand.nextInt(16);
        return new ChunkPosition(k, l, i1);
    }

    public static final int performSpawning(World var0, boolean var1, boolean var2)
    {
        if(!var1 && !var2)
        {
            return 0;
        }
        else
        {
            eligibleChunksForSpawning.clear();

            int var3;
            int var6;
            for(var3 = 0; var3 < var0.playerEntities.size(); ++var3)
            {
                EntityPlayer var4 = (EntityPlayer)var0.playerEntities.get(var3);
                int var5 = MathHelper.floor_double(var4.posX / 16.0D);
                var6 = MathHelper.floor_double(var4.posZ / 16.0D);
                byte var7 = 8;

                for(int var8 = -var7; var8 <= var7; ++var8)
                {
                    for(int var9 = -var7; var9 <= var7; ++var9)
                    {
                        boolean var10 = var8 == -var7 || var8 == var7 || var9 == -var7 || var9 == var7;
                        ChunkCoordIntPair var11 = new ChunkCoordIntPair(var8 + var5, var9 + var6);
                        if(!var10)
                        {
                            eligibleChunksForSpawning.put(var11, Boolean.valueOf(false));
                        }
                        else if(!eligibleChunksForSpawning.containsKey(var11))
                        {
                            eligibleChunksForSpawning.put(var11, Boolean.valueOf(true));
                        }
                    }
                }
            }

            var3 = 0;
            ChunkCoordinates var31 = var0.getSpawnPoint();
            EnumCreatureType[] var32 = EnumCreatureType.values();
            var6 = var32.length;

            for(int var33 = 0; var33 < var6; ++var33)
            {
                EnumCreatureType var34 = var32[var33];
                if((!var34.getPeacefulCreature() || var2) && (var34.getPeacefulCreature() || var1) && var0.countEntities(var34.getCreatureClass()) <= var34.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256)
                {
                    Iterator var35 = eligibleChunksForSpawning.keySet().iterator();

                    label108:
                        while(var35.hasNext())
                        {
                            ChunkCoordIntPair var37 = (ChunkCoordIntPair)var35.next();
                            if(!((Boolean)eligibleChunksForSpawning.get(var37)).booleanValue())
                            {
                                ChunkPosition var36 = getRandomSpawningPointInChunk(var0, var37.chunkXPos * 16, var37.chunkZPos * 16);
                                int var12 = var36.x;
                                int var13 = var36.y;
                                int var14 = var36.z;
                                if(!var0.isBlockNormalCube(var12, var13, var14) && var0.getBlockMaterial(var12, var13, var14) == var34.getCreatureMaterial())
                                {
                                    int var15 = 0;
                                    int var16 = 0;

                                    while(var16 < 3)
                                    {
                                        int var17 = var12;
                                        int var18 = var13;
                                        int var19 = var14;
                                        byte var20 = 6;
                                        SpawnListEntry var21 = null;
                                        int var22 = 0;

                                        while(true)
                                        {
                                            if(var22 < 4)
                                            {
                                                label101:
                                                {
                                                    var17 += var0.rand.nextInt(var20) - var0.rand.nextInt(var20);
                                                    var18 += var0.rand.nextInt(1) - var0.rand.nextInt(1);
                                                    var19 += var0.rand.nextInt(var20) - var0.rand.nextInt(var20);
                                                    if(canCreatureTypeSpawnAtLocation(var34, var0, var17, var18, var19))
                                                    {
                                                        float var23 = (float)var17 + 0.5F;
                                                        float var24 = (float)var18;
                                                        float var25 = (float)var19 + 0.5F;
                                                        if(var0.getClosestPlayer((double)var23, (double)var24, (double)var25, 24.0D) == null)
                                                        {
                                                            float var26 = var23 - (float)var31.posX;
                                                            float var27 = var24 - (float)var31.posY;
                                                            float var28 = var25 - (float)var31.posZ;
                                                            float var29 = var26 * var26 + var27 * var27 + var28 * var28;
                                                            if(var29 >= 576.0F)
                                                            {
                                                                if(var21 == null)
                                                                {
                                                                    var21 = var0.func_40216_a(var34, var17, var18, var19);
                                                                    if(var21 == null)
                                                                    {
                                                                        break label101;
                                                                    }
                                                                }
    
                                                                EntityLiving var38;
                                                                try
                                                                {
                                                                    var38 = (EntityLiving)var21.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{var0});
                                                                }
                                                                catch (Exception var30)
                                                                {
                                                                    var30.printStackTrace();
                                                                    return var3;
                                                                }
    
                                                                var38.setLocationAndAngles((double)var23, (double)var24, (double)var25, var0.rand.nextFloat() * 360.0F, 0.0F);
                                                                if(var38.getCanSpawnHere())
                                                                {
                                                                    ++var15;
                                                                    var0.entityJoinedWorld(var38);
                                                                    creatureSpecificInit(var38, var0, var23, var24, var25);
                                                                    if(var15 >= var38.getMaxSpawnedInChunk())
                                                                    {
                                                                        continue label108;
                                                                    }
                                                                }
    
                                                                var3 += var15;
                                                            }
                                                        }
                                                    }
    
                                                    ++var22;
                                                    continue;
                                                }
                                            }

                                            ++var16;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                }
            }

            return var3;
        }
    }

    private static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
    {
        if(enumcreaturetype.getCreatureMaterial() == Material.water)
        {
            return world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockNormalCube(i, j + 1, k);
        } else
        {
            return world.isBlockNormalCube(i, j - 1, k) && !world.isBlockNormalCube(i, j, k) && !world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockNormalCube(i, j + 1, k);
        }
    }

    private static void creatureSpecificInit(EntityLiving entityliving, World world, float f, float f1, float f2)
    {
        if((entityliving instanceof EntitySpider) && world.rand.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);
            entityskeleton.setLocationAndAngles(f, f1, f2, entityliving.rotationYaw, 0.0F);
            world.entityJoinedWorld(entityskeleton);
            entityskeleton.mountEntity(entityliving);
        } else
        if(entityliving instanceof EntitySheep)
        {
            ((EntitySheep)entityliving).setFleeceColor(EntitySheep.getRandomFleeceColor(world.rand));
        }
    }

    public static void func_35573_a(World world, BiomeGenBase biomegenbase, int i, int j, int k, int l, Random random)
    {
        List list = biomegenbase.getSpawnableList(EnumCreatureType.creature);
        if(list.isEmpty())
        {
            return;
        }
        while(random.nextFloat() < biomegenbase.getBiome()) 
        {
            SpawnListEntry spawnlistentry = (SpawnListEntry)WeightedRandom.func_35689_a(world.rand, list);
            int i1 = spawnlistentry.field_35484_b + random.nextInt((1 + spawnlistentry.field_35485_c) - spawnlistentry.field_35484_b);
            int j1 = i + random.nextInt(k);
            int k1 = j + random.nextInt(l);
            int l1 = j1;
            int i2 = k1;
            int j2 = 0;
            while(j2 < i1) 
            {
                boolean flag = false;
                for(int k2 = 0; !flag && k2 < 4; k2++)
                {
                    int l2 = world.findTopSolidBlock(j1, k1);
                    if(canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world, j1, l2, k1))
                    {
                        float f = (float)j1 + 0.5F;
                        float f1 = l2;
                        float f2 = (float)k1 + 0.5F;
                        EntityLiving entityliving;
                        try
                        {
                            entityliving = (EntityLiving)spawnlistentry.entityClass.getConstructor(new Class[] {
                                net.minecraft.src.World.class
                            }).newInstance(new Object[] {
                                world
                            });
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                            continue;
                        }
                        entityliving.setLocationAndAngles(f, f1, f2, random.nextFloat() * 360F, 0.0F);
                        world.entityJoinedWorld(entityliving);
                        creatureSpecificInit(entityliving, world, f, f1, f2);
                        flag = true;
                    }
                    j1 += random.nextInt(5) - random.nextInt(5);
                    for(k1 += random.nextInt(5) - random.nextInt(5); j1 < i || j1 >= i + k || k1 < j || k1 >= j + k; k1 = (i2 + random.nextInt(5)) - random.nextInt(5))
                    {
                        j1 = (l1 + random.nextInt(5)) - random.nextInt(5);
                    }

                }

                j2++;
            }
        }
    }

    static 
    {
        nightSpawnEntities = (new Class[] {
            net.minecraft.src.EntitySpider.class, net.minecraft.src.EntityZombie.class, net.minecraft.src.EntitySkeleton.class
        });
    }
}
