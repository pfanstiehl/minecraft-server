// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            IEnchantmentModifier, Enchantment, DamageSource, Empty3

final class EnchantmentModifierDamage
    implements IEnchantmentModifier
{

    public int damageModifier;
    public DamageSource damageSource;

    private EnchantmentModifierDamage()
    {
    }

    public void calculateModifier(Enchantment enchantment, int i)
    {
        damageModifier += enchantment.calcModifierDamage(i, damageSource);
    }

    EnchantmentModifierDamage(Empty3 empty3)
    {
        this();
    }
}
