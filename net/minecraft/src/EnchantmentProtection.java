// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Enchantment, EnumEnchantmentType, DamageSource

public class EnchantmentProtection extends Enchantment
{

    private static final String field_40378_w[] = {
        "all", "fire", "fall", "explosion", "projectile"
    };
    private static final int field_40382_x[] = {
        1, 10, 5, 5, 3
    };
    private static final int field_40381_y[] = {
        16, 8, 6, 8, 6
    };
    private static final int field_40380_z[] = {
        20, 12, 10, 12, 15
    };
    public final int field_40379_a;

    public EnchantmentProtection(int i, int j, int k)
    {
        super(i, j, EnumEnchantmentType.armor);
        field_40379_a = k;
        if(k == 2)
        {
            type = EnumEnchantmentType.armor_feet;
        }
    }

    public int getMinEnchantability(int i)
    {
        return field_40382_x[field_40379_a] + (i - 1) * field_40381_y[field_40379_a];
    }

    public int getMaxEnchantability(int i)
    {
        return getMinEnchantability(i) + field_40380_z[field_40379_a];
    }

    public int getMaxLevel()
    {
        return 4;
    }

    public int calcModifierDamage(int i, DamageSource damagesource)
    {
        if(damagesource.canHarmInCreative())
        {
            return 0;
        }
        int j = (6 + i * i) / 2;
        if(field_40379_a == 0)
        {
            return j;
        }
        if(field_40379_a == 1 && damagesource.func_40272_k())
        {
            return j;
        }
        if(field_40379_a == 2 && damagesource == DamageSource.fall)
        {
            return j * 2;
        }
        if(field_40379_a == 3 && damagesource == DamageSource.explosion)
        {
            return j;
        }
        if(field_40379_a == 4 && damagesource.func_40275_b())
        {
            return j;
        } else
        {
            return 0;
        }
    }

    public boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof EnchantmentProtection)
        {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection)enchantment;
            if(enchantmentprotection.field_40379_a == field_40379_a)
            {
                return false;
            }
            return field_40379_a == 2 || enchantmentprotection.field_40379_a == 2;
        } else
        {
            return super.canApplyTogether(enchantment);
        }
    }

}
