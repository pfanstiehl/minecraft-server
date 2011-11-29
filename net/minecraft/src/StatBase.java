// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StatList, AchievementMap, StatTypeSimple, StatTypeTime, 
//            StatTypeDistance, IStatType

public class StatBase
{

    public final int statId;
    public final String statName;
    public boolean isIndependent;
    public String statGuid;
    private final IStatType type;
    private static NumberFormat numberFormat;
    public static IStatType simpleStatType = new StatTypeSimple();
    private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
    public static IStatType timeStatType = new StatTypeTime();
    public static IStatType distanceStatType = new StatTypeDistance();

    public StatBase(int i, String s, IStatType istattype)
    {
        isIndependent = false;
        statId = i;
        statName = s;
        type = istattype;
    }

    public StatBase(int i, String s)
    {
        this(i, s, simpleStatType);
    }

    public StatBase initIndependentStat()
    {
        isIndependent = true;
        return this;
    }

    public StatBase registerStat()
    {
        if(StatList.oneShotStats.containsKey(Integer.valueOf(statId)))
        {
            throw new RuntimeException((new StringBuilder()).append("Duplicate stat id: \"").append(((StatBase)StatList.oneShotStats.get(Integer.valueOf(statId))).statName).append("\" and \"").append(statName).append("\" at id ").append(statId).toString());
        } else
        {
            StatList.field_25123_a.add(this);
            StatList.oneShotStats.put(Integer.valueOf(statId), this);
            statGuid = AchievementMap.getGuid(statId);
            return this;
        }
    }

    public String toString()
    {
        return statName;
    }

    static 
    {
        numberFormat = NumberFormat.getIntegerInstance(Locale.US);
    }
}
