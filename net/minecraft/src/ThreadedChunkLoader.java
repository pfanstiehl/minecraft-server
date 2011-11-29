// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IChunkLoader, IThreadedFileIO, ChunkCoordIntPair, ThreadedChunkLoaderPending, 
//            RegionFileCache, CompressedStreamTools, NBTTagCompound, ChunkLoader, 
//            Chunk, World, ThreadedFileIOBase

public class ThreadedChunkLoader
    implements IChunkLoader, IThreadedFileIO
{

    private List field_40330_a;
    private Set field_40328_b;
    private Object field_40329_c;
    private final File field_40327_d;

    public ThreadedChunkLoader(File file)
    {
        field_40330_a = new ArrayList();
        field_40328_b = new HashSet();
        field_40329_c = new Object();
        field_40327_d = file;
    }

    public Chunk loadChunk(World world, int i, int j)
        throws IOException
    {
        NBTTagCompound nbttagcompound = null;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
        synchronized(field_40329_c)
        {
            if(field_40328_b.contains(chunkcoordintpair))
            {
                int k = 0;
                do
                {
                    if(k >= field_40330_a.size())
                    {
                        break;
                    }
                    if(((ThreadedChunkLoaderPending)field_40330_a.get(k)).field_40613_a.equals(chunkcoordintpair))
                    {
                        nbttagcompound = ((ThreadedChunkLoaderPending)field_40330_a.get(k)).field_40612_b;
                        break;
                    }
                    k++;
                } while(true);
            }
        }
        if(nbttagcompound == null)
        {
            java.io.DataInputStream datainputstream = RegionFileCache.getChunkInputStream(field_40327_d, i, j);
            if(datainputstream != null)
            {
                nbttagcompound = CompressedStreamTools.read(datainputstream);
            } else
            {
                return null;
            }
        }
        if(!nbttagcompound.hasKey("Level"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing level data, skipping").toString());
            return null;
        }
        if(!nbttagcompound.getCompoundTag("Level").hasKey("Blocks"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing block data, skipping").toString());
            return null;
        }
        Chunk chunk = ChunkLoader.loadChunkIntoWorldFromCompound(world, nbttagcompound.getCompoundTag("Level"));
        if(!chunk.isAtLocation(i, j))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is in the wrong location; relocating. (Expected ").append(i).append(", ").append(j).append(", got ").append(chunk.xPosition).append(", ").append(chunk.zPosition).append(")").toString());
            nbttagcompound.setInteger("xPos", i);
            nbttagcompound.setInteger("zPos", j);
            chunk = ChunkLoader.loadChunkIntoWorldFromCompound(world, nbttagcompound.getCompoundTag("Level"));
        }
        chunk.func_25083_h();
        return chunk;
    }

    public void saveChunk(World world, Chunk chunk)
    {
        world.checkSessionLock();
        try
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.setTag("Level", nbttagcompound1);
            ChunkLoader.storeChunkInCompound(chunk, world, nbttagcompound1);
            func_40326_a(chunk.func_40543_i(), nbttagcompound);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void func_40326_a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound)
    {
        synchronized(field_40329_c)
        {
            if(field_40328_b.contains(chunkcoordintpair))
            {
                for(int i = 0; i < field_40330_a.size(); i++)
                {
                    if(((ThreadedChunkLoaderPending)field_40330_a.get(i)).field_40613_a.equals(chunkcoordintpair))
                    {
                        field_40330_a.set(i, new ThreadedChunkLoaderPending(chunkcoordintpair, nbttagcompound));
                        return;
                    }
                }

            }
            field_40330_a.add(new ThreadedChunkLoaderPending(chunkcoordintpair, nbttagcompound));
            field_40328_b.add(chunkcoordintpair);
            ThreadedFileIOBase.field_40514_a.func_40507_a(this);
            return;
        }
    }

    public boolean func_40324_c()
    {
        ThreadedChunkLoaderPending threadedchunkloaderpending = null;
        synchronized(field_40329_c)
        {
            if(field_40330_a.size() > 0)
            {
                threadedchunkloaderpending = (ThreadedChunkLoaderPending)field_40330_a.remove(0);
                field_40328_b.remove(threadedchunkloaderpending.field_40613_a);
            } else
            {
                return false;
            }
        }
        if(threadedchunkloaderpending != null)
        {
            try
            {
                func_40325_a(threadedchunkloaderpending);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return true;
    }

    public void func_40325_a(ThreadedChunkLoaderPending threadedchunkloaderpending)
        throws IOException
    {
        DataOutputStream dataoutputstream = RegionFileCache.getChunkOutputStream(field_40327_d, threadedchunkloaderpending.field_40613_a.chunkXPos, threadedchunkloaderpending.field_40613_a.chunkZPos);
        CompressedStreamTools.writeTo(threadedchunkloaderpending.field_40612_b, dataoutputstream);
        dataoutputstream.close();
    }

    public void saveExtraChunkData(World world, Chunk chunk)
    {
    }

    public void func_661_a()
    {
    }

    public void saveExtraData()
    {
    }
}
