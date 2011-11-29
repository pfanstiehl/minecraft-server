// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            IEnchantmentModifier, Enchantment, EntityLiving, Empty3

final class EnchantmentModifierLiving
    implements IEnchantmentModifier
{

    public int livingModifier;
    public EntityLiving entityLiving;

    private EnchantmentModifierLiving()
    {
    }

    public void calculateModifier(Enchantment enchantment, int i)
    {
        livingModifier += enchantment.calcModifierLiving(i, entityLiving);
    }

    EnchantmentModifierLiving(Empty3 empty3)
    {
        this();
    }
}
