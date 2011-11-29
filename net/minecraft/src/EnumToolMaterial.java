// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public enum EnumToolMaterial
{
    WOOD("WOOD", 0, 0, 59, 2.0F, 0, 15),
    STONE("STONE", 1, 1, 131, 4F, 1, 5),
    IRON("IRON", 2, 2, 250, 6F, 2, 14),
    EMERALD("EMERALD", 3, 3, 1561, 8F, 3, 10),
    GOLD("GOLD", 4, 0, 32, 12F, 0, 22);
/*
    public static final EnumToolMaterial WOOD;
    public static final EnumToolMaterial STONE;
    public static final EnumToolMaterial IRON;
    public static final EnumToolMaterial EMERALD;
    public static final EnumToolMaterial GOLD;
*/
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiencyOnProperMaterial;
    private final int damageVsEntity;
    private final int enchantability;
    private static final EnumToolMaterial allToolMaterials[]; /* synthetic field */
/*
    public static EnumToolMaterial[] values()
    {
        return (EnumToolMaterial[])allToolMaterials.clone();
    }

    public static EnumToolMaterial valueOf(String s)
    {
        return (EnumToolMaterial)Enum.valueOf(net.minecraft.src.EnumToolMaterial.class, s);
    }
*/
    private EnumToolMaterial(String s, int i, int j, int k, float f, int l, int i1)
    {
//        super(s, i);
        harvestLevel = j;
        maxUses = k;
        efficiencyOnProperMaterial = f;
        damageVsEntity = l;
        enchantability = i1;
    }

    public int getMaxUses()
    {
        return maxUses;
    }

    public float getEfficiencyOnProperMaterial()
    {
        return efficiencyOnProperMaterial;
    }

    public int getDamageVsEntity()
    {
        return damageVsEntity;
    }

    public int getHarvestLevel()
    {
        return harvestLevel;
    }

    public int getEnchantability()
    {
        return enchantability;
    }

    static 
    {
/*
        WOOD = new EnumToolMaterial("WOOD", 0, 0, 59, 2.0F, 0, 15);
        STONE = new EnumToolMaterial("STONE", 1, 1, 131, 4F, 1, 5);
        IRON = new EnumToolMaterial("IRON", 2, 2, 250, 6F, 2, 14);
        EMERALD = new EnumToolMaterial("EMERALD", 3, 3, 1561, 8F, 3, 10);
        GOLD = new EnumToolMaterial("GOLD", 4, 0, 32, 12F, 0, 22);
*/
        allToolMaterials = (new EnumToolMaterial[] {
            WOOD, STONE, IRON, EMERALD, GOLD
        });
    }
}
