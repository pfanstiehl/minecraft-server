// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            DamageSource, EntityPlayer, Entity, StatCollector

public class EntityDamageSource extends DamageSource
{

    private Entity damageSourceEntity;

    public EntityDamageSource(String s, Entity entity)
    {
        super(s);
        damageSourceEntity = entity;
    }

    public Entity getEntity()
    {
        return damageSourceEntity;
    }

    public String func_35075_a(EntityPlayer entityplayer)
    {
        return StatCollector.translateToLocalFormatted((new StringBuilder()).append("death.").append(damageType).toString(), new Object[] {
            entityplayer.username, damageSourceEntity.func_35150_Y()
        });
    }
}
