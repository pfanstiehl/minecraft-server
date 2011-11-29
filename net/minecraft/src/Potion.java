// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityLiving, DamageSource, EntityPlayer, PotionHealth

public class Potion
{

    public static final Potion potionTypes[] = new Potion[32];
    public static final Potion field_35453_b = null;
    public static final Potion moveSpeed = (new Potion(1, false, 0x7cafc6)).setPotionName("potion.moveSpeed").func_40589_a(0, 0);
    public static final Potion moveSlowdown = (new Potion(2, true, 0x5a6c81)).setPotionName("potion.moveSlowdown").func_40589_a(1, 0);
    public static final Potion digSpeed = (new Potion(3, false, 0xd9c043)).setPotionName("potion.digSpeed").func_40589_a(2, 0).func_40591_a(1.5D);
    public static final Potion digSlowdown = (new Potion(4, true, 0x4a4217)).setPotionName("potion.digSlowDown").func_40589_a(3, 0);
    public static final Potion damageBoost = (new Potion(5, false, 0x932423)).setPotionName("potion.damageBoost").func_40589_a(4, 0);
    public static final Potion heal = (new PotionHealth(6, false, 0xf82423)).setPotionName("potion.heal");
    public static final Potion harm = (new PotionHealth(7, true, 0x430a09)).setPotionName("potion.harm");
    public static final Potion jump = (new Potion(8, false, 0x786297)).setPotionName("potion.jump").func_40589_a(2, 1);
    public static final Potion confusion = (new Potion(9, true, 0x551d4a)).setPotionName("potion.confusion").func_40589_a(3, 1).func_40591_a(0.25D);
    public static final Potion regenerationPotion = (new Potion(10, false, 0xcd5cab)).setPotionName("potion.regeneration").func_40589_a(7, 0).func_40591_a(0.25D);
    public static final Potion resistance = (new Potion(11, false, 0x99453a)).setPotionName("potion.resistance").func_40589_a(6, 1);
    public static final Potion fireResistancePotion = (new Potion(12, false, 0xe49a3a)).setPotionName("potion.fireResistance").func_40589_a(7, 1);
    public static final Potion waterBreathingPotion = (new Potion(13, false, 0x2e5299)).setPotionName("potion.waterBreathing").func_40589_a(0, 2);
    public static final Potion invisibilityPotion = (new Potion(14, false, 0x7f8392)).setPotionName("potion.invisibility").func_40589_a(0, 1).func_40590_e();
    public static final Potion blindnessPotion = (new Potion(15, true, 0x1f1f23)).setPotionName("potion.blindness").func_40589_a(5, 1).func_40591_a(0.25D);
    public static final Potion nightVisionPotion = (new Potion(16, false, 0x1f1fa1)).setPotionName("potion.nightVision").func_40589_a(4, 1).func_40590_e();
    public static final Potion hungerPotion = (new Potion(17, true, 0x587653)).setPotionName("potion.hunger").func_40589_a(1, 1);
    public static final Potion weaknessPotion = (new Potion(18, true, 0x484d48)).setPotionName("potion.weakness").func_40589_a(5, 0);
    public static final Potion poisonPotion = (new Potion(19, true, 0x4e9331)).setPotionName("potion.poison").func_40589_a(6, 0).func_40591_a(0.25D);
    public static final Potion field_35465_v = null;
    public static final Potion field_35464_w = null;
    public static final Potion field_35474_x = null;
    public static final Potion field_35473_y = null;
    public static final Potion field_35472_z = null;
    public static final Potion field_35444_A = null;
    public static final Potion field_35445_B = null;
    public static final Potion field_35446_C = null;
    public static final Potion field_35440_D = null;
    public static final Potion field_35441_E = null;
    public static final Potion field_35442_F = null;
    public static final Potion field_35443_G = null;
    public final int potionId;
    private String potionName;
    private int field_40601_J;
    private final boolean field_40602_K;
    private double field_40598_L;
    private boolean field_40599_M;
    private final int field_40600_N;

    protected Potion(int i, boolean flag, int j)
    {
        potionName = "";
        field_40601_J = -1;
        potionId = i;
        potionTypes[i] = this;
        field_40602_K = flag;
        if(flag)
        {
            field_40598_L = 0.5D;
        } else
        {
            field_40598_L = 1.0D;
        }
        field_40600_N = j;
    }

    protected Potion func_40589_a(int i, int j)
    {
        field_40601_J = i + j * 8;
        return this;
    }

    public int func_40594_a()
    {
        return potionId;
    }

    public void func_35438_a(EntityLiving entityliving, int i)
    {
        if(potionId == regenerationPotion.potionId)
        {
            if(entityliving.getEntityHealth() < entityliving.getMaxHealth())
            {
                entityliving.heal(1);
            }
        } else
        if(potionId == poisonPotion.potionId)
        {
            if(entityliving.getEntityHealth() > 1)
            {
                entityliving.attackEntityFrom(DamageSource.magic, 1);
            }
        } else
        if(potionId == hungerPotion.potionId && (entityliving instanceof EntityPlayer))
        {
            ((EntityPlayer)entityliving).addExhaustion(0.025F * (float)(i + 1));
        } else
        if(potionId == heal.potionId && !entityliving.func_40100_at() || potionId == harm.potionId && entityliving.func_40100_at())
        {
            entityliving.heal(6 << i);
        } else
        if(potionId == harm.potionId && !entityliving.func_40100_at() || potionId == heal.potionId && entityliving.func_40100_at())
        {
            entityliving.attackEntityFrom(DamageSource.magic, 6 << i);
        }
    }

    public void func_40588_a(EntityLiving entityliving, EntityLiving entityliving1, int i, double d)
    {
        if(potionId == heal.potionId && !entityliving1.func_40100_at() || potionId == harm.potionId && entityliving1.func_40100_at())
        {
            int j = (int)(d * (double)(6 << i) + 0.5D);
            entityliving1.heal(j);
        } else
        if(potionId == harm.potionId && !entityliving1.func_40100_at() || potionId == heal.potionId && entityliving1.func_40100_at())
        {
            int k = (int)(d * (double)(6 << i) + 0.5D);
            if(entityliving == null)
            {
                entityliving1.attackEntityFrom(DamageSource.magic, k);
            } else
            {
                entityliving1.attackEntityFrom(DamageSource.func_40271_b(entityliving1, entityliving), k);
            }
        }
    }

    public boolean func_40595_b()
    {
        return false;
    }

    public boolean isReady(int i, int j)
    {
        if(potionId == regenerationPotion.potionId || potionId == poisonPotion.potionId)
        {
            int k = 25 >> j;
            if(k > 0)
            {
                return i % k == 0;
            } else
            {
                return true;
            }
        }
        return potionId == hungerPotion.potionId;
    }

    public Potion setPotionName(String s)
    {
        potionName = s;
        return this;
    }

    public String func_40596_c()
    {
        return potionName;
    }

    protected Potion func_40591_a(double d)
    {
        field_40598_L = d;
        return this;
    }

    public double func_40592_d()
    {
        return field_40598_L;
    }

    public Potion func_40590_e()
    {
        field_40599_M = true;
        return this;
    }

    public boolean func_40593_f()
    {
        return field_40599_M;
    }

    public int func_40597_g()
    {
        return field_40600_N;
    }

}
