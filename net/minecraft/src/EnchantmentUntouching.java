// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Enchantment, EnumEnchantmentType

public class EnchantmentUntouching extends Enchantment
{

    protected EnchantmentUntouching(int i, int j)
    {
        super(i, j, EnumEnchantmentType.digger);
        setName("untouching");
    }

    public int getMinEnchantability(int i)
    {
        return 25;
    }

    public int getMaxEnchantability(int i)
    {
        return super.getMinEnchantability(i) + 50;
    }

    public int getMaxLevel()
    {
        return 1;
    }

    public boolean canApplyTogether(Enchantment enchantment)
    {
        return super.canApplyTogether(enchantment) && enchantment.effectId != fortune.effectId;
    }
}
