// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            World, Entity, NBTTagCompound, EntityItem, 
//            EntityXPOrb, EntityPainting, EntityArrow, EntitySnowball, 
//            EntityFireball, EntitySmallFireball, EntityEnderPearl, EntityEnderEye, 
//            EntityTNTPrimed, EntityFallingSand, EntityMinecart, EntityBoat, 
//            EntityLiving, EntityMob, EntityCreeper, EntitySkeleton, 
//            EntitySpider, EntityGiantZombie, EntityZombie, EntitySlime, 
//            EntityGhast, EntityPigZombie, EntityEnderman, EntityCaveSpider, 
//            EntitySilverfish, EntityBlaze, EntityMagmaCube, EntityDragon, 
//            EntityPig, EntitySheep, EntityCow, EntityChicken, 
//            EntitySquid, EntityWolf, EntityMooshroom, EntitySnowman, 
//            EntityVillager, EntityEnderCrystal

public class EntityList
{

    private static Map stringToClassMapping = new HashMap();
    private static Map classToStringMapping = new HashMap();
    private static Map IDtoClassMapping = new HashMap();
    private static Map classToIDMapping = new HashMap();

    public EntityList()
    {
    }

    private static void addMapping(Class class1, String s, int i)
    {
        stringToClassMapping.put(s, class1);
        classToStringMapping.put(class1, s);
        IDtoClassMapping.put(Integer.valueOf(i), class1);
        classToIDMapping.put(class1, Integer.valueOf(i));
    }

    public static Entity createEntityInWorld(String s, World world)
    {
        Entity entity = null;
        try
        {
            Class class1 = (Class)stringToClassMapping.get(s);
            if(class1 != null)
            {
                entity = (Entity)class1.getConstructor(new Class[] {
                    net.minecraft.src.World.class
                }).newInstance(new Object[] {
                    world
                });
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return entity;
    }

    public static Entity createEntityFromNBT(NBTTagCompound nbttagcompound, World world)
    {
        Entity entity = null;
        try
        {
            Class class1 = (Class)stringToClassMapping.get(nbttagcompound.getString("id"));
            if(class1 != null)
            {
                entity = (Entity)class1.getConstructor(new Class[] {
                    net.minecraft.src.World.class
                }).newInstance(new Object[] {
                    world
                });
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(entity != null)
        {
            entity.readFromNBT(nbttagcompound);
        } else
        {
            System.out.println((new StringBuilder()).append("Skipping Entity with id ").append(nbttagcompound.getString("id")).toString());
        }
        return entity;
    }

    public static int getEntityID(Entity entity)
    {
        return ((Integer)classToIDMapping.get(entity.getClass())).intValue();
    }

    public static String getEntityString(Entity entity)
    {
        return (String)classToStringMapping.get(entity.getClass());
    }

    static 
    {
        addMapping(net.minecraft.src.EntityItem.class, "Item", 1);
        addMapping(net.minecraft.src.EntityXPOrb.class, "XPOrb", 2);
        addMapping(net.minecraft.src.EntityPainting.class, "Painting", 9);
        addMapping(net.minecraft.src.EntityArrow.class, "Arrow", 10);
        addMapping(net.minecraft.src.EntitySnowball.class, "Snowball", 11);
        addMapping(net.minecraft.src.EntityFireball.class, "Fireball", 12);
        addMapping(net.minecraft.src.EntitySmallFireball.class, "SmallFireball", 13);
        addMapping(net.minecraft.src.EntityEnderPearl.class, "ThrownEnderpearl", 14);
        addMapping(net.minecraft.src.EntityEnderEye.class, "EyeOfEnderSignal", 15);
        addMapping(net.minecraft.src.EntityTNTPrimed.class, "PrimedTnt", 20);
        addMapping(net.minecraft.src.EntityFallingSand.class, "FallingSand", 21);
        addMapping(net.minecraft.src.EntityMinecart.class, "Minecart", 40);
        addMapping(net.minecraft.src.EntityBoat.class, "Boat", 41);
        addMapping(net.minecraft.src.EntityLiving.class, "Mob", 48);
        addMapping(net.minecraft.src.EntityMob.class, "Monster", 49);
        addMapping(net.minecraft.src.EntityCreeper.class, "Creeper", 50);
        addMapping(net.minecraft.src.EntitySkeleton.class, "Skeleton", 51);
        addMapping(net.minecraft.src.EntitySpider.class, "Spider", 52);
        addMapping(net.minecraft.src.EntityGiantZombie.class, "Giant", 53);
        addMapping(net.minecraft.src.EntityZombie.class, "Zombie", 54);
        addMapping(net.minecraft.src.EntitySlime.class, "Slime", 55);
        addMapping(net.minecraft.src.EntityGhast.class, "Ghast", 56);
        addMapping(net.minecraft.src.EntityPigZombie.class, "PigZombie", 57);
        addMapping(net.minecraft.src.EntityEnderman.class, "Enderman", 58);
        addMapping(net.minecraft.src.EntityCaveSpider.class, "CaveSpider", 59);
        addMapping(net.minecraft.src.EntitySilverfish.class, "Silverfish", 60);
        addMapping(net.minecraft.src.EntityBlaze.class, "Blaze", 61);
        addMapping(net.minecraft.src.EntityMagmaCube.class, "LavaSlime", 62);
        addMapping(net.minecraft.src.EntityDragon.class, "EnderDragon", 63);
        addMapping(net.minecraft.src.EntityPig.class, "Pig", 90);
        addMapping(net.minecraft.src.EntitySheep.class, "Sheep", 91);
        addMapping(net.minecraft.src.EntityCow.class, "Cow", 92);
        addMapping(net.minecraft.src.EntityChicken.class, "Chicken", 93);
        addMapping(net.minecraft.src.EntitySquid.class, "Squid", 94);
        addMapping(net.minecraft.src.EntityWolf.class, "Wolf", 95);
        addMapping(net.minecraft.src.EntityMooshroom.class, "MushroomCow", 96);
        addMapping(net.minecraft.src.EntitySnowman.class, "SnowMan", 97);
        addMapping(net.minecraft.src.EntityVillager.class, "Villager", 120);
        addMapping(net.minecraft.src.EntityEnderCrystal.class, "EnderCrystal", 200);
    }
}
