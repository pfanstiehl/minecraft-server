// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ChunkCoordIntPair, NBTTagCompound

class ThreadedChunkLoaderPending
{

    public final ChunkCoordIntPair field_40613_a;
    public final NBTTagCompound field_40612_b;

    public ThreadedChunkLoaderPending(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound)
    {
        field_40613_a = chunkcoordintpair;
        field_40612_b = nbttagcompound;
    }
}
