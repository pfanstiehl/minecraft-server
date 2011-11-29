// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityMob, World

public class EntityGiantZombie extends EntityMob
{

    public EntityGiantZombie(World world)
    {
        super(world);
        texture = "/mob/zombie.png";
        moveSpeed = 0.5F;
        attackStrength = 50;
        yOffset *= 6F;
        setSize(width * 6F, height * 6F);
    }

    public int getMaxHealth()
    {
        return 100;
    }

    protected float getBlockPathWeight(int i, int j, int k)
    {
        return worldObj.getLightBrightness(i, j, k) - 0.5F;
    }
}
