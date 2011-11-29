// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package net.minecraft.src:
//            GuiStatsComponent

class GuiStatsListener
    implements ActionListener
{

    final GuiStatsComponent statsComponent; /* synthetic field */

    GuiStatsListener(GuiStatsComponent guistatscomponent)
    {
        statsComponent = guistatscomponent;
//        super();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        GuiStatsComponent.update(statsComponent);
    }
}
