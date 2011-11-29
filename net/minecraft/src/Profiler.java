// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

public class Profiler
{

    public static boolean profilingEnabled = false;
    private static List sectionList = new ArrayList();
    private static List timestampList = new ArrayList();
    private static String profilingSection = "";
    private static Map profilingMap = new HashMap();

    public Profiler()
    {
    }

    public static void startSection(String s)
    {
        if(!profilingEnabled)
        {
            return;
        }
        if(profilingSection.length() > 0)
        {
            profilingSection = (new StringBuilder()).append(profilingSection).append(".").toString();
        }
        profilingSection = (new StringBuilder()).append(profilingSection).append(s).toString();
        sectionList.add(profilingSection);
        timestampList.add(Long.valueOf(System.nanoTime()));
    }

    public static void endSection()
    {
        if(!profilingEnabled)
        {
            return;
        }
        long l = System.nanoTime();
        long l1 = ((Long)timestampList.remove(timestampList.size() - 1)).longValue();
        sectionList.remove(sectionList.size() - 1);
        long l2 = l - l1;
        if(profilingMap.containsKey(profilingSection))
        {
            profilingMap.put(profilingSection, Long.valueOf(((Long)profilingMap.get(profilingSection)).longValue() + l2));
        } else
        {
            profilingMap.put(profilingSection, Long.valueOf(l2));
        }
        profilingSection = sectionList.size() <= 0 ? "" : (String)sectionList.get(sectionList.size() - 1);
    }

    public static void endStartSection(String s)
    {
        endSection();
        startSection(s);
    }

}
