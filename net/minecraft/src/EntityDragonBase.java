// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityLiving, World, DragonPart, DamageSource

public class EntityDragonBase extends EntityLiving
{

    protected int maxHealth;

    public EntityDragonBase(World world)
    {
        super(world);
        maxHealth = 100;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public boolean attackEntityFromPart(DragonPart dragonpart, DamageSource damagesource, int i)
    {
        return attackEntityFrom(damagesource, i);
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        return false;
    }

    protected boolean func_40137_e(DamageSource damagesource, int i)
    {
        return super.attackEntityFrom(damagesource, i);
    }
}
