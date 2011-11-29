// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EnumArmorMaterial

public class ItemArmor extends Item
{

    private static final int maxDamageArray[] = {
        11, 16, 15, 13
    };
    public final int armorType;
    public final int damageReduceAmount;
    public final int field_40252_bR;
    private final EnumArmorMaterial field_40251_bT;

    public ItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k)
    {
        super(i);
        field_40251_bT = enumarmormaterial;
        armorType = k;
        field_40252_bR = j;
        damageReduceAmount = enumarmormaterial.func_40495_b(k);
        setMaxDamage(enumarmormaterial.func_40497_a(k));
        maxStackSize = 1;
    }

    public int getItemEnchantability()
    {
        return field_40251_bT.getEnchantability();
    }

    static int[] func_40250_n()
    {
        return maxDamageArray;
    }

}
