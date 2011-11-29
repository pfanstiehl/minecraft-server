// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemArmor, ItemSword, ItemTool, Item

public enum EnumEnchantmentType
{
    all("all", 0),
    armor("armor", 1),
    armor_feet("armor_feet", 2),
    armor_legs("armor_legs", 3),
    armor_torso("armor_torso", 4),
    armor_head("armor_head", 5),
    weapon("weapon", 6),
    digger("digger", 7);
/*
    public static final EnumEnchantmentType all;
    public static final EnumEnchantmentType armor;
    public static final EnumEnchantmentType armor_feet;
    public static final EnumEnchantmentType armor_legs;
    public static final EnumEnchantmentType armor_torso;
    public static final EnumEnchantmentType armor_head;
    public static final EnumEnchantmentType weapon;
    public static final EnumEnchantmentType digger;
*/
    private static final EnumEnchantmentType allEnchantmentTypes[]; /* synthetic field */
/*
    public static EnumEnchantmentType[] values()
    {
        return (EnumEnchantmentType[])allEnchantmentTypes.clone();
    }

    public static EnumEnchantmentType valueOf(String s)
    {
        return (EnumEnchantmentType)Enum.valueOf(net.minecraft.src.EnumEnchantmentType.class, s);
    }
*/
    private EnumEnchantmentType(String s, int i)
    {
//        super(s, i);
    }

    public boolean canEnchantItem(Item item)
    {
        if(this == all)
        {
            return true;
        }
        if(item instanceof ItemArmor)
        {
            if(this == armor)
            {
                return true;
            }
            ItemArmor itemarmor = (ItemArmor)item;
            if(itemarmor.armorType == 0)
            {
                return this == armor_head;
            }
            if(itemarmor.armorType == 2)
            {
                return this == armor_legs;
            }
            if(itemarmor.armorType == 1)
            {
                return this == armor_torso;
            }
            if(itemarmor.armorType == 3)
            {
                return this == armor_feet;
            } else
            {
                return false;
            }
        }
        if(item instanceof ItemSword)
        {
            return this == weapon;
        }
        if(item instanceof ItemTool)
        {
            return this == digger;
        } else
        {
            return false;
        }
    }

    static 
    {
/*
        all = new EnumEnchantmentType("all", 0);
        armor = new EnumEnchantmentType("armor", 1);
        armor_feet = new EnumEnchantmentType("armor_feet", 2);
        armor_legs = new EnumEnchantmentType("armor_legs", 3);
        armor_torso = new EnumEnchantmentType("armor_torso", 4);
        armor_head = new EnumEnchantmentType("armor_head", 5);
        weapon = new EnumEnchantmentType("weapon", 6);
        digger = new EnumEnchantmentType("digger", 7);
*/
        allEnchantmentTypes = (new EnumEnchantmentType[] {
            all, armor, armor_feet, armor_legs, armor_torso, armor_head, weapon, digger
        });
    }
}
