// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityDamageSource, EntityPlayer, Entity, StatCollector

public class EntityDamageSourceIndirect extends EntityDamageSource
{

    private Entity damageSourceEntity2;

    public EntityDamageSourceIndirect(String s, Entity entity, Entity entity1)
    {
        super(s, entity);
        damageSourceEntity2 = entity1;
    }

    public Entity getEntity()
    {
        return damageSourceEntity2;
    }

    public String func_35075_a(EntityPlayer entityplayer)
    {
        return StatCollector.translateToLocalFormatted((new StringBuilder()).append("death.").append(damageType).toString(), new Object[] {
            entityplayer.username, damageSourceEntity2.func_35150_Y()
        });
    }
}
