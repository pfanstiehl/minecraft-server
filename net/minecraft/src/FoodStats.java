// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemFood, EntityPlayer, World, DamageSource, 
//            NBTTagCompound

public class FoodStats
{

    private int foodLevel;
    private float foodSaturationLevel;
    private float foodExhaustionLevel;
    private int foodTimer;
    private int prevFoodLevel;

    public FoodStats()
    {
        foodTimer = 0;
        foodLevel = 20;
        prevFoodLevel = 20;
        foodSaturationLevel = 5F;
    }

    public void addFoodAndSaturationLevel(int i, float f)
    {
        foodLevel = Math.min(i + foodLevel, 20);
        foodSaturationLevel = Math.min(foodSaturationLevel + (float)i * f * 2.0F, foodLevel);
    }

    public void eatFood(ItemFood itemfood)
    {
        addFoodAndSaturationLevel(itemfood.getHealAmount(), itemfood.getSaturationFactor());
    }

    public void update(EntityPlayer entityplayer)
    {
        int i = entityplayer.worldObj.difficultySetting;
        prevFoodLevel = foodLevel;
        if(foodExhaustionLevel > 4F)
        {
            foodExhaustionLevel -= 4F;
            if(foodSaturationLevel > 0.0F)
            {
                foodSaturationLevel = Math.max(foodSaturationLevel - 1.0F, 0.0F);
            } else
            if(i > 0)
            {
                foodLevel = Math.max(foodLevel - 1, 0);
            }
        }
        if(foodLevel >= 18 && entityplayer.mustHeal())
        {
            foodTimer++;
            if(foodTimer >= 80)
            {
                entityplayer.heal(1);
                foodTimer = 0;
            }
        } else
        if(foodLevel <= 0)
        {
            foodTimer++;
            if(foodTimer >= 80)
            {
                if(entityplayer.getEntityHealth() > 10 || i >= 3 || entityplayer.getEntityHealth() > 1 && i >= 2)
                {
                    entityplayer.attackEntityFrom(DamageSource.starve, 1);
                }
                foodTimer = 0;
            }
        } else
        {
            foodTimer = 0;
        }
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        if(nbttagcompound.hasKey("foodLevel"))
        {
            foodLevel = nbttagcompound.getInteger("foodLevel");
            foodTimer = nbttagcompound.getInteger("foodTickTimer");
            foodSaturationLevel = nbttagcompound.getFloat("foodSaturationLevel");
            foodExhaustionLevel = nbttagcompound.getFloat("foodExhaustionLevel");
        }
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setInteger("foodLevel", foodLevel);
        nbttagcompound.setInteger("foodTickTimer", foodTimer);
        nbttagcompound.setFloat("foodSaturationLevel", foodSaturationLevel);
        nbttagcompound.setFloat("foodExhaustionLevel", foodExhaustionLevel);
    }

    public int getFoodLevel()
    {
        return foodLevel;
    }

    public boolean mustEat()
    {
        return foodLevel < 20;
    }

    public void addExhaustion(float f)
    {
        foodExhaustionLevel = Math.min(foodExhaustionLevel + f, 40F);
    }

    public float getSaturationLevel()
    {
        return foodSaturationLevel;
    }
}
