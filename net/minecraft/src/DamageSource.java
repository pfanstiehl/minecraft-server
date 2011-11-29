// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityDamageSource, EntityDamageSourceIndirect, EntityPlayer, StatCollector, 
//            EntityLiving, EntityArrow, Entity, EntityFireball

public class DamageSource
{

    public static DamageSource inFire = (new DamageSource("inFire")).func_40276_j();
    public static DamageSource onFire = (new DamageSource("onFire")).setDamageBypassesArmor().func_40276_j();
    public static DamageSource lava = (new DamageSource("lava")).func_40276_j();
    public static DamageSource inWall = (new DamageSource("inWall")).setDamageBypassesArmor();
    public static DamageSource drown = (new DamageSource("drown")).setDamageBypassesArmor();
    public static DamageSource starve = (new DamageSource("starve")).setDamageBypassesArmor();
    public static DamageSource cactus = new DamageSource("cactus");
    public static DamageSource fall = (new DamageSource("fall")).setDamageBypassesArmor();
    public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource generic = (new DamageSource("generic")).setDamageBypassesArmor();
    public static DamageSource explosion = new DamageSource("explosion");
    public static DamageSource magic = (new DamageSource("magic")).setDamageBypassesArmor();
    private boolean isBlockable;
    private boolean isDamageAllowedInCreativeMode;
    private float hungerDamage;
    private boolean field_40278_q;
    private boolean field_40277_r;
    public String damageType;

    public static DamageSource causeMobDamage(EntityLiving entityliving)
    {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource causePlayerDamage(EntityPlayer entityplayer)
    {
        return new EntityDamageSource("player", entityplayer);
    }

    public static DamageSource causeArrowDamage(EntityArrow entityarrow, Entity entity)
    {
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).func_40273_c();
    }

    public static DamageSource causeFireballDamage(EntityFireball entityfireball, Entity entity)
    {
        return (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).func_40276_j().func_40273_c();
    }

    public static DamageSource causeThrownDamage(Entity entity, Entity entity1)
    {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).func_40273_c();
    }

    public static DamageSource func_40271_b(Entity entity, Entity entity1)
    {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).setDamageBypassesArmor();
    }

    public boolean func_40275_b()
    {
        return field_40277_r;
    }

    public DamageSource func_40273_c()
    {
        field_40277_r = true;
        return this;
    }

    public boolean unblockable()
    {
        return isBlockable;
    }

    public float getHungerDamage()
    {
        return hungerDamage;
    }

    public boolean canHarmInCreative()
    {
        return isDamageAllowedInCreativeMode;
    }

    protected DamageSource(String s)
    {
        isBlockable = false;
        isDamageAllowedInCreativeMode = false;
        hungerDamage = 0.3F;
        damageType = s;
    }

    public Entity getSourceOfDamage()
    {
        return getEntity();
    }

    public Entity getEntity()
    {
        return null;
    }

    protected DamageSource setDamageBypassesArmor()
    {
        isBlockable = true;
        hungerDamage = 0.0F;
        return this;
    }

    protected DamageSource setDamageAllowedInCreativeMode()
    {
        isDamageAllowedInCreativeMode = true;
        return this;
    }

    protected DamageSource func_40276_j()
    {
        field_40278_q = true;
        return this;
    }

    public String func_35075_a(EntityPlayer entityplayer)
    {
        return StatCollector.translateToLocalFormatted((new StringBuilder()).append("death.").append(damageType).toString(), new Object[] {
            entityplayer.username
        });
    }

    public boolean func_40272_k()
    {
        return field_40278_q;
    }

    public String func_40274_l()
    {
        return damageType;
    }

}
