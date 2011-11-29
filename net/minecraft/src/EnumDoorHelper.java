// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EnumDoor

class EnumDoorHelper
{

    static final int doorEnum[]; /* synthetic field */

    static 
    {
        doorEnum = new int[EnumDoor.values().length];
        try
        {
            doorEnum[EnumDoor.OPENING.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            doorEnum[EnumDoor.WOOD_DOOR.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            doorEnum[EnumDoor.GRATES.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            doorEnum[EnumDoor.IRON_DOOR.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
    }
}
