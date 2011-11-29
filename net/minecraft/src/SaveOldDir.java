// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.File;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            PlayerNBTManager, WorldProviderHell, ThreadedChunkLoader, WorldProviderEnd, 
//            WorldInfo, ThreadedFileIOBase, RegionFileCache, WorldProvider, 
//            IChunkLoader

public class SaveOldDir extends PlayerNBTManager
{

    public SaveOldDir(File file, String s, boolean flag)
    {
        super(file, s, flag);
    }

    public IChunkLoader getChunkLoader(WorldProvider worldprovider)
    {
        File file = getWorldDir();
        if(worldprovider instanceof WorldProviderHell)
        {
            File file1 = new File(file, "DIM-1");
            file1.mkdirs();
            return new ThreadedChunkLoader(file1);
        }
        if(worldprovider instanceof WorldProviderEnd)
        {
            File file2 = new File(file, "DIM1");
            file2.mkdirs();
            return new ThreadedChunkLoader(file2);
        } else
        {
            return new ThreadedChunkLoader(file);
        }
    }

    public void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list)
    {
        worldinfo.setSaveVersion(19132);
        super.saveWorldInfoAndPlayer(worldinfo, list);
    }

    public void func_22093_e()
    {
        try
        {
            ThreadedFileIOBase.field_40514_a.func_40508_a();
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        RegionFileCache.clearRegionFileReferences();
    }
}
