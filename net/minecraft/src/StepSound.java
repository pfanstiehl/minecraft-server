// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


public class StepSound
{

    public final String stepSoundName;
    public final float stepSoundVolume;
    public final float stepSoundPitch;

    public StepSound(String s, float f, float f1)
    {
        stepSoundName = s;
        stepSoundVolume = f;
        stepSoundPitch = f1;
    }

    public float getVolume()
    {
        return stepSoundVolume;
    }

    public float getPitch()
    {
        return stepSoundPitch;
    }

    public String stepSoundDir()
    {
        return (new StringBuilder()).append("step.").append(stepSoundName).toString();
    }
}
