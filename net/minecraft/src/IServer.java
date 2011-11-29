// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public interface IServer
{

    public abstract int getIntProperty(String s, int i);

    public abstract String getStringProperty(String s, String s1);

    public abstract void setProperty(String s, Object obj);

    public abstract void saveProperties();

    public abstract String getSettingsFilename();

    public abstract String getHostname();

    public abstract int getPort();

    public abstract String getMotd();

    public abstract String getVersionString();

    public abstract int playersOnline();

    public abstract int getMaxPlayers();

    public abstract String[] getPlayerNamesAsList();

    public abstract String getWorldName();

    public abstract String getPlugin();

    public abstract void func_40010_o();

    public abstract String handleRConCommand(String s);

    public abstract boolean isDebuggingEnabled();

    public abstract void log(String s);

    public abstract void logWarning(String s);

    public abstract void logSevere(String s);

    public abstract void logIn(String s);
}
