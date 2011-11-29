// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            TileEntity, World, EntityPlayer

public class TileEntityEnchantmentTable extends TileEntity
{

    public int field_40071_a;
    public float field_40068_b;
    public float field_40070_c;
    public float field_40066_d;
    public float field_40067_e;
    public float field_40064_f;
    public float field_40065_g;
    public float field_40073_h;
    public float field_40074_i;
    public float field_40072_j;
    private static Random field_40069_r = new Random();

    public TileEntityEnchantmentTable()
    {
    }

    public void updateEntity()
    {
        super.updateEntity();
        field_40065_g = field_40064_f;
        field_40074_i = field_40073_h;
        EntityPlayer entityplayer = worldObj.getClosestPlayer((float)xCoord + 0.5F, (float)yCoord + 0.5F, (float)zCoord + 0.5F, 3D);
        if(entityplayer != null)
        {
            double d = entityplayer.posX - (double)((float)xCoord + 0.5F);
            double d1 = entityplayer.posZ - (double)((float)zCoord + 0.5F);
            field_40072_j = (float)Math.atan2(d1, d);
            field_40064_f += 0.1F;
            if(field_40064_f < 0.5F || field_40069_r.nextInt(40) == 0)
            {
                float f3 = field_40066_d;
                do
                {
                    field_40066_d += field_40069_r.nextInt(4) - field_40069_r.nextInt(4);
                } while(f3 == field_40066_d);
            }
        } else
        {
            field_40072_j += 0.02F;
            field_40064_f -= 0.1F;
        }
        for(; field_40073_h >= 3.141593F; field_40073_h -= 6.283185F) { }
        for(; field_40073_h < -3.141593F; field_40073_h += 6.283185F) { }
        for(; field_40072_j >= 3.141593F; field_40072_j -= 6.283185F) { }
        for(; field_40072_j < -3.141593F; field_40072_j += 6.283185F) { }
        float f;
        for(f = field_40072_j - field_40073_h; f >= 3.141593F; f -= 6.283185F) { }
        for(; f < -3.141593F; f += 6.283185F) { }
        field_40073_h += f * 0.4F;
        if(field_40064_f < 0.0F)
        {
            field_40064_f = 0.0F;
        }
        if(field_40064_f > 1.0F)
        {
            field_40064_f = 1.0F;
        }
        field_40071_a++;
        field_40070_c = field_40068_b;
        float f1 = (field_40066_d - field_40068_b) * 0.4F;
        float f2 = 0.2F;
        if(f1 < -f2)
        {
            f1 = -f2;
        }
        if(f1 > f2)
        {
            f1 = f2;
        }
        field_40067_e += (f1 - field_40067_e) * 0.9F;
        field_40068_b = field_40068_b + field_40067_e;
    }

}
