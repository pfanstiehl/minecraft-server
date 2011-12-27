// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            PotionEffect, Potion

public class PotionHelper
{

    public static final String field_40562_a = null;
    public static final String sugarEffect = "-0+1-2-3&4-4+13";
    public static final String ghastTearEffect = "+0-1-2-3&4-4+13";
    public static final String spiderEyeEffect = "-0-1+2-3&4-4+13";
    public static final String fermentedSpiderEyeEffect = "-0+3-4+13";
    public static final String speckledMelonEffect = "+0-1+2-3&4-4+13";
    public static final String blazePowderEffect = "+0-1-2+3&4-4+13";
    public static final String magmaCreamEffect = "+0+1-2-3&4-4+13";
    public static final String redstoneEffect = "-5+6-7";
    public static final String glowstoneEffect = "+5-6-7";
    public static final String gunpowderEffect = "+14&13-13";
    private static final HashMap field_40565_l;
    private static final HashMap field_40566_m;
    private static final HashMap field_40563_n = new HashMap();
    private static final String potionPrefixList[] = {
        "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", 
        "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", 
        "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", 
        "potion.prefix.gross", "potion.prefix.stinky"
    };

    public PotionHelper()
    {
    }

    public static boolean checkFlag(int i, int j)
    {
        return (i & 1 << j) != 0;
    }

    private static int isFlagSet(int i, int j)
    {
        return checkFlag(i, j) ? 1 : 0;
    }

    private static int isFlagUnset(int i, int j)
    {
        return checkFlag(i, j) ? 0 : 1;
    }

    public static int func_40553_a(Collection collection)
    {
        int i = 0x385dc6;
        if(collection == null || collection.isEmpty())
        {
            return i;
        }
        float f = 0.0F;
        float f1 = 0.0F;
        float f2 = 0.0F;
        float f3 = 0.0F;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            PotionEffect potioneffect = (PotionEffect)iterator.next();
            int j = Potion.potionTypes[potioneffect.getPotionID()].getLiquidColor();
            int k = 0;
            while(k <= potioneffect.getAmplifier()) 
            {
                f += (float)(j >> 16 & 0xff) / 255F;
                f1 += (float)(j >> 8 & 0xff) / 255F;
                f2 += (float)(j >> 0 & 0xff) / 255F;
                f3++;
                k++;
            }
        }

        f = (f / f3) * 255F;
        f1 = (f1 / f3) * 255F;
        f2 = (f2 / f3) * 255F;
        return (int)f << 16 | (int)f1 << 8 | (int)f2;
    }

    private static int func_40546_a(boolean flag, boolean flag1, boolean flag2, int i, int j, int k, int l)
    {
        int i1 = 0;
        if(flag)
        {
            i1 = isFlagUnset(l, j);
        } else
        if(i != -1)
        {
            if(i == 0 && countSetFlags(l) == j)
            {
                i1 = 1;
            } else
            if(i == 1 && countSetFlags(l) > j)
            {
                i1 = 1;
            } else
            if(i == 2 && countSetFlags(l) < j)
            {
                i1 = 1;
            }
        } else
        {
            i1 = isFlagSet(l, j);
        }
        if(flag1)
        {
            i1 *= k;
        }
        if(flag2)
        {
            i1 *= -1;
        }
        return i1;
    }

    private static int countSetFlags(int i)
    {
        int j;
        for(j = 0; i > 0; j++)
        {
            i &= i - 1;
        }

        return j;
    }

    private static int func_40554_a(String s, int i, int j, int k)
    {
        if(i >= s.length() || j < 0 || i >= j)
        {
            return 0;
        }
        int l = s.indexOf('|', i);
        if(l >= 0 && l < j)
        {
            int i1 = func_40554_a(s, i, l - 1, k);
            if(i1 > 0)
            {
                return i1;
            }
            int k1 = func_40554_a(s, l + 1, j, k);
            if(k1 > 0)
            {
                return k1;
            } else
            {
                return 0;
            }
        }
        int j1 = s.indexOf('&', i);
        if(j1 >= 0 && j1 < j)
        {
            int l1 = func_40554_a(s, i, j1 - 1, k);
            if(l1 <= 0)
            {
                return 0;
            }
            int i2 = func_40554_a(s, j1 + 1, j, k);
            if(i2 <= 0)
            {
                return 0;
            }
            if(l1 > i2)
            {
                return l1;
            } else
            {
                return i2;
            }
        }
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        byte byte0 = -1;
        int j2 = 0;
        int k2 = 0;
        int l2 = 0;
        for(int i3 = i; i3 < j; i3++)
        {
            char c = s.charAt(i3);
            if(c >= '0' && c <= '9')
            {
                if(flag)
                {
                    k2 = c - 48;
                    flag1 = true;
                } else
                {
                    j2 *= 10;
                    j2 += c - 48;
                    flag2 = true;
                }
                continue;
            }
            if(c == '*')
            {
                flag = true;
                continue;
            }
            if(c == '!')
            {
                if(flag2)
                {
                    l2 += func_40546_a(flag3, flag1, flag4, byte0, j2, k2, k);
                    flag2 = flag1 = flag = flag4 = flag3 = false;
                    j2 = k2 = 0;
                    byte0 = -1;
                }
                flag3 = true;
                continue;
            }
            if(c == '-')
            {
                if(flag2)
                {
                    l2 += func_40546_a(flag3, flag1, flag4, byte0, j2, k2, k);
                    flag2 = flag1 = flag = flag4 = flag3 = false;
                    j2 = k2 = 0;
                    byte0 = -1;
                }
                flag4 = true;
                continue;
            }
            if(c == '=' || c == '<' || c == '>')
            {
                if(flag2)
                {
                    l2 += func_40546_a(flag3, flag1, flag4, byte0, j2, k2, k);
                    flag2 = flag1 = flag = flag4 = flag3 = false;
                    j2 = k2 = 0;
                    byte0 = -1;
                }
                if(c == '=')
                {
                    byte0 = 0;
                    continue;
                }
                if(c == '<')
                {
                    byte0 = 2;
                    continue;
                }
                if(c == '>')
                {
                    byte0 = 1;
                }
                continue;
            }
            if(c == '+' && flag2)
            {
                l2 += func_40546_a(flag3, flag1, flag4, byte0, j2, k2, k);
                flag2 = flag1 = flag = flag4 = flag3 = false;
                j2 = k2 = 0;
                byte0 = -1;
            }
        }

        if(flag2)
        {
            l2 += func_40546_a(flag3, flag1, flag4, byte0, j2, k2, k);
        }
        return l2;
    }

    public static List getPotionEffects(int i, boolean flag)
    {
        ArrayList arraylist = null;
        Potion apotion[] = Potion.potionTypes;
        int j = apotion.length;
        for(int k = 0; k < j; k++)
        {
            Potion potion = apotion[k];
            if(potion == null || potion.func_40593_f() && !flag)
            {
                continue;
            }
            String s = (String)field_40565_l.get(Integer.valueOf(potion.getId()));
            if(s == null)
            {
                continue;
            }
            int l = func_40554_a(s, 0, s.length(), i);
            if(l <= 0)
            {
                continue;
            }
            int i1 = 0;
            String s1 = (String)field_40566_m.get(Integer.valueOf(potion.getId()));
            if(s1 != null)
            {
                i1 = func_40554_a(s1, 0, s1.length(), i);
                if(i1 < 0)
                {
                    i1 = 0;
                }
            }
            if(potion.isInstant())
            {
                l = 1;
            } else
            {
                l = 1200 * (l * 3 + (l - 1) * 2);
                l >>= i1;
                l = (int)Math.round((double)l * potion.func_40592_d());
                if((i & 0x4000) != 0)
                {
                    l = (int)Math.round((double)l * 0.75D + 0.5D);
                }
            }
            if(arraylist == null)
            {
                arraylist = new ArrayList();
            }
            arraylist.add(new PotionEffect(potion.getId(), l, i1));
        }

        return arraylist;
    }

    private static int handleBitFromIngredient(int i, int j, boolean flag, boolean flag1, boolean flag2)
    {
        if(flag2)
        {
            if(!checkFlag(i, j))
            {
                return 0;
            }
        } else
        if(flag)
        {
            i &= ~(1 << j);
        } else
        if(flag1)
        {
            if((i & 1 << j) != 0)
            {
                i &= ~(1 << j);
            } else
            {
                i |= 1 << j;
            }
        } else
        {
            i |= 1 << j;
        }
        return i;
    }

    public static int applyIngredient(int i, String s)
    {
        boolean flag = false;
        int j = s.length();
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        int k = 0;
        for(int l = ((flag) ? 1 : 0); l < j; l++)
        {
            char c = s.charAt(l);
            if(c >= '0' && c <= '9')
            {
                k *= 10;
                k += c - 48;
                flag1 = true;
                continue;
            }
            if(c == '!')
            {
                if(flag1)
                {
                    i = handleBitFromIngredient(i, k, flag3, flag2, flag4);
                    flag1 = flag3 = flag2 = flag4 = false;
                    k = 0;
                }
                flag2 = true;
                continue;
            }
            if(c == '-')
            {
                if(flag1)
                {
                    i = handleBitFromIngredient(i, k, flag3, flag2, flag4);
                    flag1 = flag3 = flag2 = flag4 = false;
                    k = 0;
                }
                flag3 = true;
                continue;
            }
            if(c == '+')
            {
                if(flag1)
                {
                    i = handleBitFromIngredient(i, k, flag3, flag2, flag4);
                    flag1 = flag3 = flag2 = flag4 = false;
                    k = 0;
                }
                continue;
            }
            if(c != '&')
            {
                continue;
            }
            if(flag1)
            {
                i = handleBitFromIngredient(i, k, flag3, flag2, flag4);
                flag1 = flag3 = flag2 = flag4 = false;
                k = 0;
            }
            flag4 = true;
        }

        if(flag1)
        {
            i = handleBitFromIngredient(i, k, flag3, flag2, flag4);
        }
        return i & 0x7fff;
    }

    static 
    {
        field_40565_l = new HashMap();
        field_40566_m = new HashMap();
        field_40565_l.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
        field_40565_l.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
        field_40565_l.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
        field_40565_l.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3");
        field_40565_l.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
        field_40565_l.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
        field_40565_l.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3");
        field_40565_l.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
        field_40565_l.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
        field_40566_m.put(Integer.valueOf(Potion.moveSpeed.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.damageBoost.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.regeneration.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.harm.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.heal.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.resistance.getId()), "5");
        field_40566_m.put(Integer.valueOf(Potion.poison.getId()), "5");
    }
}
