// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public enum EnumAction
{
    none("none", 0),
    eat("eat", 1),
    drink("drink", 2),
    block("block", 3),
    bow("bow", 4);
/*
    public static final EnumAction none;
    public static final EnumAction eat;
    public static final EnumAction drink;
    public static final EnumAction block;
    public static final EnumAction bow;
*/
    private static final EnumAction allActions[]; /* synthetic field */
/*
    public static EnumAction[] values()
    {
        return (EnumAction[])allActions.clone();
    }

    public static EnumAction valueOf(String s)
    {
        return (EnumAction)Enum.valueOf(net.minecraft.src.EnumAction.class, s);
    }
*/
    private EnumAction(String s, int i)
    {
//        super(s, i);
    }

    static 
    {
/*
        none = new EnumAction("none", 0);
        eat = new EnumAction("eat", 1);
        drink = new EnumAction("drink", 2);
        block = new EnumAction("block", 3);
        bow = new EnumAction("bow", 4);
*/
        allActions = (new EnumAction[] {
            none, eat, drink, block, bow
        });
    }
}
