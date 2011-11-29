// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ICommandListener

public class RConConsoleSource
    implements ICommandListener
{

    public static final RConConsoleSource instance = new RConConsoleSource();
    private StringBuffer buffer;

    public RConConsoleSource()
    {
        buffer = new StringBuffer();
    }

    public void resetLog()
    {
        buffer.setLength(0);
    }

    public String getLogContents()
    {
        return buffer.toString();
    }

    public void log(String s)
    {
        buffer.append(s);
    }

    public String getUsername()
    {
        return "Rcon";
    }

}
