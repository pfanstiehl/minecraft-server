// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityDamageSource, EntityDamageSourceIndirect, EntityPlayer, StatCollector, 
//            EntityLiving, EntityArrow, Entity, EntityFireball

public class DamageSource
{

    public static DamageSource inFire = (new DamageSource("inFire")).setFireDamage();
    public static DamageSource onFire = (new DamageSource("onFire")).setDamageBypassesArmor().setFireDamage();
    public static DamageSource lava = (new DamageSource("lava")).setFireDamage();
    public static DamageSource inWall = (new DamageSource("inWall")).setDamageBypassesArmor();
    public static DamageSource drown = (new DamageSource("drown")).setDamageBypassesArmor();
    public static DamageSource starve = (new DamageSource("starve")).setDamageBypassesArmor();
    public static DamageSource cactus = new DamageSource("cactus");
    public static DamageSource fall = (new DamageSource("fall")).setDamageBypassesArmor();
    public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).setDamageBypassesArmor().setDamageAllowedInCreativeMode();
    public static DamageSource generic = (new DamageSource("generic")).setDamageBypassesArmor();
    public static DamageSource explosion = new DamageSource("explosion");
    public static DamageSource magic = (new DamageSource("magic")).setDamageBypassesArmor();
    private boolean isUnblockable;
    private boolean isDamageAllowedInCreativeMode;
    private float hungerDamage;
    private boolean fireDamage;
    private boolean projectile;
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
        return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).setProjectile();
    }

    public static DamageSource causeFireballDamage(EntityFireball entityfireball, Entity entity)
    {
        return (new EntityDamageSourceIndirect("fireball", entityfireball, entity)).setFireDamage().setProjectile();
    }

    public static DamageSource causeThrownDamage(Entity entity, Entity entity1)
    {
        return (new EntityDamageSourceIndirect("thrown", entity, entity1)).setProjectile();
    }

    public static DamageSource causeIndirectMagicDamage(Entity entity, Entity entity1)
    {
        return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).setDamageBypassesArmor();
    }

    public boolean isProjectile()
    {
        return projectile;
    }

    public DamageSource setProjectile()
    {
        projectile = true;
        return this;
    }

    public boolean isUnblockable()
    {
        return isUnblockable;
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
        isUnblockable = false;
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
        isUnblockable = true;
        hungerDamage = 0.0F;
        return this;
    }

    protected DamageSource setDamageAllowedInCreativeMode()
    {
        isDamageAllowedInCreativeMode = true;
        return this;
    }

    protected DamageSource setFireDamage()
    {
        fireDamage = true;
        return this;
    }

    public String func_35075_a(EntityPlayer entityplayer)
    {
        return StatCollector.translateToLocalFormatted((new StringBuilder()).append("death.").append(damageType).toString(), new Object[] {
            entityplayer.username
        });
    }

    public boolean fireDamage()
    {
        return fireDamage;
    }

    public String getDamageType()
    {
        return damageType;
    }

}
