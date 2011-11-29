// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Enchantment, EnumEnchantmentType

public class EnchantmentFireAspect extends Enchantment
{

    protected EnchantmentFireAspect(int i, int j)
    {
        super(i, j, EnumEnchantmentType.weapon);
        setName("fire");
    }

    public int getMinEnchantability(int i)
    {
        return 10 + 20 * (i - 1);
    }

    public int getMaxEnchantability(int i)
    {
        return super.getMinEnchantability(i) + 50;
    }

    public int getMaxLevel()
    {
        return 2;
    }
}
