// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityLiving, IMob, DataWatcher, NBTTagCompound, 
//            World, MathHelper, AxisAlignedBB, DamageSource, 
//            EntityPlayer, Item, Chunk

public class EntitySlime extends EntityLiving
    implements IMob
{

    public float field_40122_a;
    public float field_401_a;
    public float field_400_b;
    private int ticksTillJump;

    public EntitySlime(World world)
    {
        super(world);
        ticksTillJump = 0;
        texture = "/mob/slime.png";
        int i = 1 << rand.nextInt(3);
        yOffset = 0.0F;
        ticksTillJump = rand.nextInt(20) + 10;
        setSlimeSize(i);
        experienceValue = i;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte)1));
    }

    public void setSlimeSize(int i)
    {
        dataWatcher.updateObject(16, new Byte((byte)i));
        setSize(0.6F * (float)i, 0.6F * (float)i);
        setPosition(posX, posY, posZ);
        setEntityHealth(getMaxHealth());
    }

    public int getMaxHealth()
    {
        int i = getSlimeSize();
        return i * i;
    }

    public int getSlimeSize()
    {
        return dataWatcher.getWatchableObjectByte(16);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Size", getSlimeSize() - 1);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        setSlimeSize(nbttagcompound.getInteger("Size") + 1);
    }

    protected String func_40120_w()
    {
        return "slime";
    }

    protected String func_40118_E()
    {
        return "mob.slime";
    }

    public void onUpdate()
    {
        if(!worldObj.singleplayerWorld && worldObj.difficultySetting == 0 && getSlimeSize() > 0)
        {
            isDead = true;
        }
        field_401_a = field_401_a + (field_40122_a - field_401_a) * 0.5F;
        field_400_b = field_401_a;
        boolean flag = onGround;
        super.onUpdate();
        if(onGround && !flag)
        {
            int i = getSlimeSize();
            for(int j = 0; j < i * 8; j++)
            {
                float f = rand.nextFloat() * 3.141593F * 2.0F;
                float f1 = rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                worldObj.spawnParticle(func_40120_w(), posX + (double)f2, boundingBox.minY, posZ + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            if(func_40121_G())
            {
                worldObj.playSoundAtEntity(this, func_40118_E(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }
            field_40122_a = -0.5F;
        }
        func_40116_B();
    }

    protected void updateEntityActionState()
    {
        despawnEntity();
        EntityPlayer entityplayer = worldObj.func_40211_b(this, 16D);
        if(entityplayer != null)
        {
            faceEntity(entityplayer, 10F, 20F);
        }
        if(onGround && ticksTillJump-- <= 0)
        {
            ticksTillJump = func_40115_A();
            if(entityplayer != null)
            {
                ticksTillJump /= 3;
            }
            isJumping = true;
            if(func_40117_I())
            {
                worldObj.playSoundAtEntity(this, func_40118_E(), getSoundVolume(), ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }
            field_40122_a = 1.0F;
            moveStrafing = 1.0F - rand.nextFloat() * 2.0F;
            moveForward = 1 * getSlimeSize();
        } else
        {
            isJumping = false;
            if(onGround)
            {
                moveStrafing = moveForward = 0.0F;
            }
        }
    }

    protected void func_40116_B()
    {
        field_40122_a = field_40122_a * 0.6F;
    }

    protected int func_40115_A()
    {
        return rand.nextInt(20) + 10;
    }

    protected EntitySlime func_40114_y()
    {
        return new EntitySlime(worldObj);
    }

    public void setEntityDead()
    {
        int i = getSlimeSize();
        if(!worldObj.singleplayerWorld && i > 1 && getEntityHealth() <= 0)
        {
            int j = 2 + rand.nextInt(3);
            for(int k = 0; k < j; k++)
            {
                float f = (((float)(k % 2) - 0.5F) * (float)i) / 4F;
                float f1 = (((float)(k / 2) - 0.5F) * (float)i) / 4F;
                EntitySlime entityslime = func_40114_y();
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(posX + (double)f, posY + 0.5D, posZ + (double)f1, rand.nextFloat() * 360F, 0.0F);
                worldObj.entityJoinedWorld(entityslime);
            }

        }
        super.setEntityDead();
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
        if(func_40119_C())
        {
            int i = getSlimeSize();
            if(canEntityBeSeen(entityplayer) && (double)getDistanceToEntity(entityplayer) < 0.59999999999999998D * (double)i && entityplayer.attackEntityFrom(DamageSource.causeMobDamage(this), func_40113_D()))
            {
                worldObj.playSoundAtEntity(this, "mob.slimeattack", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    protected boolean func_40119_C()
    {
        return getSlimeSize() > 1;
    }

    protected int func_40113_D()
    {
        return getSlimeSize();
    }

    protected String getHurtSound()
    {
        return "mob.slime";
    }

    protected String getDeathSound()
    {
        return "mob.slime";
    }

    protected int getDropItemId()
    {
        if(getSlimeSize() == 1)
        {
            return Item.slimeBall.shiftedIndex;
        } else
        {
            return 0;
        }
    }

    public boolean getCanSpawnHere()
    {
        Chunk chunk = worldObj.getChunkFromBlockCoords(MathHelper.floor_double(posX), MathHelper.floor_double(posZ));
        if((getSlimeSize() == 1 || worldObj.difficultySetting > 0) && rand.nextInt(10) == 0 && chunk.getRandomWithSeed(0x3ad8025fL).nextInt(10) == 0 && posY < 40D)
        {
            return super.getCanSpawnHere();
        } else
        {
            return false;
        }
    }

    protected float getSoundVolume()
    {
        return 0.4F * (float)getSlimeSize();
    }

    protected int getVerticalFaceSpeed()
    {
        return 0;
    }

    protected boolean func_40117_I()
    {
        return getSlimeSize() > 1;
    }

    protected boolean func_40121_G()
    {
        return getSlimeSize() > 2;
    }
}
