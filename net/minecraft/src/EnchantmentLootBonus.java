// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Enchantment, EnumEnchantmentType

public class EnchantmentLootBonus extends Enchantment
{

    protected EnchantmentLootBonus(int i, int j, EnumEnchantmentType enumenchantmenttype)
    {
        super(i, j, enumenchantmenttype);
        setName("lootBonus");
        if(enumenchantmenttype == EnumEnchantmentType.digger)
        {
            setName("lootBonusDigger");
        }
    }

    public int getMinEnchantability(int i)
    {
        return 20 + (i - 1) * 12;
    }

    public int getMaxEnchantability(int i)
    {
        return super.getMinEnchantability(i) + 50;
    }

    public int getMaxLevel()
    {
        return 3;
    }

    public boolean canApplyTogether(Enchantment enchantment)
    {
        return super.canApplyTogether(enchantment) && enchantment.effectId != silkTouch.effectId;
    }
}
