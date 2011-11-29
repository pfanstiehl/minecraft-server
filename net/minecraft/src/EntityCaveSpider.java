// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntitySpider, EntityLiving, World, PotionEffect, 
//            Potion, Entity

public class EntityCaveSpider extends EntitySpider
{

    public EntityCaveSpider(World world)
    {
        super(world);
        texture = "/mob/cavespider.png";
        setSize(0.7F, 0.5F);
    }

    public int getMaxHealth()
    {
        return 12;
    }

    protected boolean attackEntityAsMob(Entity entity)
    {
        if(super.attackEntityAsMob(entity))
        {
            if(entity instanceof EntityLiving)
            {
                byte byte0 = 0;
                if(worldObj.difficultySetting > 1)
                {
                    if(worldObj.difficultySetting == 2)
                    {
                        byte0 = 7;
                    } else
                    if(worldObj.difficultySetting == 3)
                    {
                        byte0 = 15;
                    }
                }
                if(byte0 > 0)
                {
                    ((EntityLiving)entity).func_35182_d(new PotionEffect(Potion.poisonPotion.potionId, byte0 * 20, 0));
                }
            }
            return true;
        } else
        {
            return false;
        }
    }
}
