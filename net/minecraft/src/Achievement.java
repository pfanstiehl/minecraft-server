// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            StatBase, ItemStack, StatCollector, AchievementList, 
//            Item, Block

public class Achievement extends StatBase
{

    public final int displayColumn;
    public final int displayRow;
    public final Achievement parentAchievement;
    private final String achievementDescription;
    public final ItemStack theItemStack;
    private boolean isSpecial;

    public Achievement(int i, String s, int j, int k, Item item, Achievement achievement)
    {
        this(i, s, j, k, new ItemStack(item), achievement);
    }

    public Achievement(int i, String s, int j, int k, Block block, Achievement achievement)
    {
        this(i, s, j, k, new ItemStack(block), achievement);
    }

    public Achievement(int i, String s, int j, int k, ItemStack itemstack, Achievement achievement)
    {
        super(0x500000 + i, StatCollector.translateToLocal((new StringBuilder()).append("achievement.").append(s).toString()));
        theItemStack = itemstack;
        achievementDescription = StatCollector.translateToLocal((new StringBuilder()).append("achievement.").append(s).append(".desc").toString());
        displayColumn = j;
        displayRow = k;
        if(j < AchievementList.minDisplayColumn)
        {
            AchievementList.minDisplayColumn = j;
        }
        if(k < AchievementList.minDisplayRow)
        {
            AchievementList.minDisplayRow = k;
        }
        if(j > AchievementList.maxDisplayColumn)
        {
            AchievementList.maxDisplayColumn = j;
        }
        if(k > AchievementList.maxDisplayRow)
        {
            AchievementList.maxDisplayRow = k;
        }
        parentAchievement = achievement;
    }

    public Achievement setIndependent()
    {
        isIndependent = true;
        return this;
    }

    public Achievement setSpecial()
    {
        isSpecial = true;
        return this;
    }

    public Achievement registerAchievement()
    {
        super.registerStat();
        AchievementList.achievementList.add(this);
        return this;
    }

    public StatBase registerStat()
    {
        return registerAchievement();
    }

    public StatBase initIndependentStat()
    {
        return setIndependent();
    }
}
