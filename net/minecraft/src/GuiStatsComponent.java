// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.Timer;
import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            GuiStatsListener, NetworkManager

public class GuiStatsComponent extends JComponent
{

    private static final DecimalFormat field_40573_a = new DecimalFormat("########0.000");
    private int memoryUse[];
    private int updateCounter;
    private String displayStrings[];
    private final MinecraftServer field_40572_e;

    public GuiStatsComponent(MinecraftServer minecraftserver)
    {
        memoryUse = new int[256];
        updateCounter = 0;
        displayStrings = new String[10];
        field_40572_e = minecraftserver;
        setPreferredSize(new Dimension(256, 226));
        setMinimumSize(new Dimension(256, 226));
        setMaximumSize(new Dimension(256, 226));
        (new Timer(500, new GuiStatsListener(this))).start();
        setBackground(Color.BLACK);
    }

    private void updateStats()
    {
        long l = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.gc();
        displayStrings[0] = (new StringBuilder()).append("Memory use: ").append(l / 1024L / 1024L).append(" mb (").append((Runtime.getRuntime().freeMemory() * 100L) / Runtime.getRuntime().maxMemory()).append("% free)").toString();
        displayStrings[1] = (new StringBuilder()).append("Threads: ").append(NetworkManager.numReadThreads).append(" + ").append(NetworkManager.numWriteThreads).toString();
        displayStrings[2] = (new StringBuilder()).append("Avg tick: ").append(field_40573_a.format(func_40571_a(field_40572_e.field_40027_f) * 9.9999999999999995E-07D)).append(" ms").toString();
        for(int i = 0; i < field_40572_e.worldMngr.length; i++)
        {
            displayStrings[3 + i] = (new StringBuilder()).append("Lvl ").append(i).append(" tick: ").append(field_40573_a.format(func_40571_a(field_40572_e.field_40028_g[i]) * 9.9999999999999995E-07D)).append(" ms").toString();
        }

        memoryUse[updateCounter++ & 0xff] = (int)((l * 100L) / Runtime.getRuntime().maxMemory());
        repaint();
    }

    private double func_40571_a(long al[])
    {
        long l = 0L;
        for(int i = 0; i < al.length; i++)
        {
            l += al[i];
        }

        return (double)l / (double)al.length;
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(0xffffff));
        g.fillRect(0, 0, 256, 226);
        for(int i = 0; i < 256; i++)
        {
            int k = memoryUse[i + updateCounter & 0xff];
            g.setColor(new Color(k + 28 << 16));
            g.fillRect(i, 100 - k, 1, k);
        }

        g.setColor(Color.BLACK);
        for(int j = 0; j < displayStrings.length; j++)
        {
            String s = displayStrings[j];
            if(s != null)
            {
                g.drawString(s, 32, 116 + j * 16);
            }
        }

    }

    static void update(GuiStatsComponent guistatscomponent)
    {
        guistatscomponent.updateStats();
    }

}
