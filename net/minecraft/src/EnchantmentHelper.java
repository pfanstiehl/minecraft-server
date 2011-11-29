// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            ItemStack, NBTTagList, NBTTagCompound, Enchantment, 
//            IEnchantmentModifier, EnchantmentModifierDamage, InventoryPlayer, EnchantmentModifierLiving, 
//            Item, WeightedRandom, EnchantmentData, EnumEnchantmentType, 
//            DamageSource, EntityLiving

public class EnchantmentHelper
{

    private static final Random field_40647_a = new Random();
    private static final EnchantmentModifierDamage field_40645_b = new EnchantmentModifierDamage(null);
    private static final EnchantmentModifierLiving field_40646_c = new EnchantmentModifierLiving(null);

    public EnchantmentHelper()
    {
    }

    private static int func_40638_b(int i, ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        NBTTagList nbttaglist = itemstack.func_40609_p();
        if(nbttaglist == null)
        {
            return 0;
        }
        for(int j = 0; j < nbttaglist.tagCount(); j++)
        {
            short word0 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("id");
            short word1 = ((NBTTagCompound)nbttaglist.tagAt(j)).getShort("lvl");
            if(word0 == i)
            {
                return word1;
            }
        }

        return 0;
    }

    private static int func_40639_a(int i, ItemStack aitemstack[])
    {
        int j = 0;
        ItemStack aitemstack1[] = aitemstack;
        int k = aitemstack1.length;
        for(int l = 0; l < k; l++)
        {
            ItemStack itemstack = aitemstack1[l];
            int i1 = func_40638_b(i, itemstack);
            if(i1 > j)
            {
                j = i1;
            }
        }

        return j;
    }

    private static void func_40627_a(IEnchantmentModifier ienchantmentmodifier, ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return;
        }
        NBTTagList nbttaglist = itemstack.func_40609_p();
        if(nbttaglist == null)
        {
            return;
        }
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            short word0 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("id");
            short word1 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("lvl");
            if(Enchantment.enchantmentsList[word0] != null)
            {
                ienchantmentmodifier.calculateModifier(Enchantment.enchantmentsList[word0], word1);
            }
        }

    }

    private static void func_40640_a(IEnchantmentModifier ienchantmentmodifier, ItemStack aitemstack[])
    {
        ItemStack aitemstack1[] = aitemstack;
        int i = aitemstack1.length;
        for(int j = 0; j < i; j++)
        {
            ItemStack itemstack = aitemstack1[j];
            func_40627_a(ienchantmentmodifier, itemstack);
        }

    }

    public static int func_40634_a(InventoryPlayer inventoryplayer, DamageSource damagesource)
    {
        field_40645_b.damageModifier = 0;
        field_40645_b.damageSource = damagesource;
        func_40640_a(field_40645_b, inventoryplayer.armorInventory);
        if(field_40645_b.damageModifier > 25)
        {
            field_40645_b.damageModifier = 25;
        }
        return (field_40645_b.damageModifier + 1 >> 1) + field_40647_a.nextInt((field_40645_b.damageModifier >> 1) + 1);
    }

    public static int func_40641_a(InventoryPlayer inventoryplayer, EntityLiving entityliving)
    {
        field_40646_c.livingModifier = 0;
        field_40646_c.entityLiving = entityliving;
        func_40627_a(field_40646_c, inventoryplayer.getCurrentItem());
        if(field_40646_c.livingModifier > 0)
        {
            return 1 + field_40647_a.nextInt(field_40646_c.livingModifier);
        } else
        {
            return 0;
        }
    }

    public static int func_40637_b(InventoryPlayer inventoryplayer, EntityLiving entityliving)
    {
        return func_40638_b(Enchantment.knockback.effectId, inventoryplayer.getCurrentItem());
    }

    public static int func_40636_c(InventoryPlayer inventoryplayer, EntityLiving entityliving)
    {
        return func_40638_b(Enchantment.fireAspect.effectId, inventoryplayer.getCurrentItem());
    }

    public static int func_40628_a(InventoryPlayer inventoryplayer)
    {
        return func_40639_a(Enchantment.respiration.effectId, inventoryplayer.armorInventory);
    }

    public static int func_40630_b(InventoryPlayer inventoryplayer)
    {
        return func_40638_b(Enchantment.efficiency.effectId, inventoryplayer.getCurrentItem());
    }

    public static int func_40643_c(InventoryPlayer inventoryplayer)
    {
        return func_40638_b(Enchantment.unbreaking.effectId, inventoryplayer.getCurrentItem());
    }

    public static boolean func_40644_d(InventoryPlayer inventoryplayer)
    {
        return func_40638_b(Enchantment.silkTouch.effectId, inventoryplayer.getCurrentItem()) > 0;
    }

    public static int getFortuneModifier(InventoryPlayer inventoryplayer)
    {
        return func_40638_b(Enchantment.fortune.effectId, inventoryplayer.getCurrentItem());
    }

    public static int func_40633_f(InventoryPlayer inventoryplayer)
    {
        return func_40638_b(Enchantment.looting.effectId, inventoryplayer.getCurrentItem());
    }

    public static boolean func_40632_g(InventoryPlayer inventoryplayer)
    {
        return func_40639_a(Enchantment.aquaAffinity.effectId, inventoryplayer.armorInventory) > 0;
    }

    public static int func_40642_a(Random random, int i, int j, ItemStack itemstack)
    {
        Item item = itemstack.getItem();
        int k = item.getItemEnchantability();
        if(k <= 0)
        {
            return 0;
        }
        if(j > 30)
        {
            j = 30;
        }
        j = 1 + random.nextInt((j >> 1) + 1) + random.nextInt(j + 1);
        int l = random.nextInt(5) + j;
        if(i == 0)
        {
            return (l >> 1) + 1;
        }
        if(i == 1)
        {
            return (l * 2) / 3 + 1;
        } else
        {
            return l;
        }
    }

    public static List func_40629_a(Random random, ItemStack itemstack, int i)
    {
        Item item = itemstack.getItem();
        int j = item.getItemEnchantability();
        if(j <= 0)
        {
            return null;
        }
        j = 1 + random.nextInt((j >> 1) + 1) + random.nextInt((j >> 1) + 1);
        int k = j + i;
        float f = ((random.nextFloat() + random.nextFloat()) - 1.0F) * 0.25F;
        int l = (int)((float)k * (1.0F + f) + 0.5F);
        ArrayList arraylist = null;
        Map map = func_40631_a(l, itemstack);
        if(map != null && !map.isEmpty())
        {
            EnchantmentData enchantmentdata = (EnchantmentData)WeightedRandom.func_35689_a(random, map.values());
            if(enchantmentdata != null)
            {
                arraylist = new ArrayList();
                arraylist.add(enchantmentdata);
                for(int i1 = l >> 1; random.nextInt(50) <= i1; i1 >>= 1)
                {
                    Iterator iterator = map.keySet().iterator();
                    do
                    {
                        if(!iterator.hasNext())
                        {
                            break;
                        }
                        Integer integer = (Integer)iterator.next();
                        boolean flag = true;
                        Iterator iterator1 = arraylist.iterator();
                        do
                        {
                            if(!iterator1.hasNext())
                            {
                                break;
                            }
                            EnchantmentData enchantmentdata2 = (EnchantmentData)iterator1.next();
                            if(enchantmentdata2.field_40494_a.canApplyTogether(Enchantment.enchantmentsList[integer.intValue()]))
                            {
                                continue;
                            }
                            flag = false;
                            break;
                        } while(true);
                        if(!flag)
                        {
                            iterator.remove();
                        }
                    } while(true);
                    if(!map.isEmpty())
                    {
                        EnchantmentData enchantmentdata1 = (EnchantmentData)WeightedRandom.func_35689_a(random, map.values());
                        arraylist.add(enchantmentdata1);
                    }
                }

            }
        }
        return arraylist;
    }

    public static Map func_40631_a(int i, ItemStack itemstack)
    {
        Item item = itemstack.getItem();
        HashMap hashmap = null;
        Enchantment aenchantment[] = Enchantment.enchantmentsList;
        int j = aenchantment.length;
        for(int k = 0; k < j; k++)
        {
            Enchantment enchantment = aenchantment[k];
            if(enchantment == null || !enchantment.type.canEnchantItem(item))
            {
                continue;
            }
            for(int l = enchantment.getMinLevel(); l <= enchantment.getMaxLevel(); l++)
            {
                if(i < enchantment.getMinEnchantability(l) || i > enchantment.getMaxEnchantability(l))
                {
                    continue;
                }
                if(hashmap == null)
                {
                    hashmap = new HashMap();
                }
                hashmap.put(Integer.valueOf(enchantment.effectId), new EnchantmentData(enchantment, l));
            }

        }

        return hashmap;
    }

}
