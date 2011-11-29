// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.ByteArrayOutputStream;

// Referenced classes of package net.minecraft.src:
//            RegionFile

class RegionFileChunkBuffer extends ByteArrayOutputStream
{

    private int chunkX;
    private int chunkZ;
    final RegionFile regionFile; /* synthetic field */

    public RegionFileChunkBuffer(RegionFile regionfile, int i, int j)
    {
        super(8096);
        regionFile = regionfile;
        chunkX = i;
        chunkZ = j;
    }

    public void close()
    {
        regionFile.write(chunkX, chunkZ, buf, count);
    }
}
