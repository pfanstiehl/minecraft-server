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
    public static final Potion moveSpeed = (new Potion(1, false, 0x7cafc6)).setPotionName("potion.moveSpeed").setIconIndex(0, 0);
    public static final Potion moveSlowdown = (new Potion(2, true, 0x5a6c81)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0);
    public static final Potion digSpeed = (new Potion(3, false, 0xd9c043)).setPotionName("potion.digSpeed").setIconIndex(2, 0).func_40591_a(1.5D);
    public static final Potion digSlowdown = (new Potion(4, true, 0x4a4217)).setPotionName("potion.digSlowDown").setIconIndex(3, 0);
    public static final Potion damageBoost = (new Potion(5, false, 0x932423)).setPotionName("potion.damageBoost").setIconIndex(4, 0);
    public static final Potion heal = (new PotionHealth(6, false, 0xf82423)).setPotionName("potion.heal");
    public static final Potion harm = (new PotionHealth(7, true, 0x430a09)).setPotionName("potion.harm");
    public static final Potion jump = (new Potion(8, false, 0x786297)).setPotionName("potion.jump").setIconIndex(2, 1);
    public static final Potion confusion = (new Potion(9, true, 0x551d4a)).setPotionName("potion.confusion").setIconIndex(3, 1).func_40591_a(0.25D);
    public static final Potion regeneration = (new Potion(10, false, 0xcd5cab)).setPotionName("potion.regeneration").setIconIndex(7, 0).func_40591_a(0.25D);
    public static final Potion resistance = (new Potion(11, false, 0x99453a)).setPotionName("potion.resistance").setIconIndex(6, 1);
    public static final Potion fireResistance = (new Potion(12, false, 0xe49a3a)).setPotionName("potion.fireResistance").setIconIndex(7, 1);
    public static final Potion waterBreathing = (new Potion(13, false, 0x2e5299)).setPotionName("potion.waterBreathing").setIconIndex(0, 2);
    public static final Potion invisibility = (new Potion(14, false, 0x7f8392)).setPotionName("potion.invisibility").setIconIndex(0, 1).func_40590_e();
    public static final Potion blindness = (new Potion(15, true, 0x1f1f23)).setPotionName("potion.blindness").setIconIndex(5, 1).func_40591_a(0.25D);
    public static final Potion nightVision = (new Potion(16, false, 0x1f1fa1)).setPotionName("potion.nightVision").setIconIndex(4, 1).func_40590_e();
    public static final Potion hunger = (new Potion(17, true, 0x587653)).setPotionName("potion.hunger").setIconIndex(1, 1);
    public static final Potion weakness = (new Potion(18, true, 0x484d48)).setPotionName("potion.weakness").setIconIndex(5, 0);
    public static final Potion poison = (new Potion(19, true, 0x4e9331)).setPotionName("potion.poison").setIconIndex(6, 0).func_40591_a(0.25D);
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
    public final int id;
    private String name;
    private int statusIconIndex;
    private final boolean isBadEffect;
    private double field_40598_L;
    private boolean field_40599_M;
    private final int liquidColor;

    protected Potion(int i, boolean flag, int j)
    {
        name = "";
        statusIconIndex = -1;
        id = i;
        potionTypes[i] = this;
        isBadEffect = flag;
        if(flag)
        {
            field_40598_L = 0.5D;
        } else
        {
            field_40598_L = 1.0D;
        }
        liquidColor = j;
    }

    protected Potion setIconIndex(int i, int j)
    {
        statusIconIndex = i + j * 8;
        return this;
    }

    public int getId()
    {
        return id;
    }

    public void performEffect(EntityLiving entityliving, int i)
    {
        if(id == regeneration.id)
        {
            if(entityliving.getEntityHealth() < entityliving.getMaxHealth())
            {
                entityliving.heal(1);
            }
        } else
        if(id == poison.id)
        {
            if(entityliving.getEntityHealth() > 1)
            {
                entityliving.attackEntityFrom(DamageSource.magic, 1);
            }
        } else
        if(id == hunger.id && (entityliving instanceof EntityPlayer))
        {
            ((EntityPlayer)entityliving).addExhaustion(0.025F * (float)(i + 1));
        } else
        if(id == heal.id && !entityliving.isEntityUndead() || id == harm.id && entityliving.isEntityUndead())
        {
            entityliving.heal(6 << i);
        } else
        if(id == harm.id && !entityliving.isEntityUndead() || id == heal.id && entityliving.isEntityUndead())
        {
            entityliving.attackEntityFrom(DamageSource.magic, 6 << i);
        }
    }

    public void affectEntity(EntityLiving entityliving, EntityLiving entityliving1, int i, double d)
    {
        if(id == heal.id && !entityliving1.isEntityUndead() || id == harm.id && entityliving1.isEntityUndead())
        {
            int j = (int)(d * (double)(6 << i) + 0.5D);
            entityliving1.heal(j);
        } else
        if(id == harm.id && !entityliving1.isEntityUndead() || id == heal.id && entityliving1.isEntityUndead())
        {
            int k = (int)(d * (double)(6 << i) + 0.5D);
            if(entityliving == null)
            {
                entityliving1.attackEntityFrom(DamageSource.magic, k);
            } else
            {
                entityliving1.attackEntityFrom(DamageSource.causeIndirectMagicDamage(entityliving1, entityliving), k);
            }
        }
    }

    public boolean isInstant()
    {
        return false;
    }

    public boolean isReady(int i, int j)
    {
        if(id == regeneration.id || id == poison.id)
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
        return id == hunger.id;
    }

    public Potion setPotionName(String s)
    {
        name = s;
        return this;
    }

    public String getName()
    {
        return name;
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

    public int getLiquidColor()
    {
        return liquidColor;
    }

}
