// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IThreadedFileIO

public class ThreadedFileIOBase
    implements Runnable
{

    public static final ThreadedFileIOBase threadedIOInstance = new ThreadedFileIOBase();
    private List threadedIOQueue;
    private volatile long writeQueuedCounter;
    private volatile long savedIOCounter;
    private volatile boolean isThreadWaiting;

    private ThreadedFileIOBase()
    {
        threadedIOQueue = Collections.synchronizedList(new ArrayList());
        writeQueuedCounter = 0L;
        savedIOCounter = 0L;
        isThreadWaiting = false;
        Thread thread = new Thread(this, "File IO Thread");
        thread.setPriority(1);
        thread.start();
    }

    public void run()
    {
        do
        {
            func_40509_b();
        } while(true);
    }

    private void func_40509_b()
    {
        for(int i = 0; i < threadedIOQueue.size(); i++)
        {
            IThreadedFileIO ithreadedfileio = (IThreadedFileIO)threadedIOQueue.get(i);
            boolean flag = ithreadedfileio.writeNextIO();
            if(!flag)
            {
                threadedIOQueue.remove(i--);
                savedIOCounter++;
            }
            try
            {
                if(!isThreadWaiting)
                {
                    Thread.sleep(10L);
                } else
                {
                    Thread.sleep(0L);
                }
            }
            catch(InterruptedException interruptedexception1)
            {
                interruptedexception1.printStackTrace();
            }
        }

        if(threadedIOQueue.isEmpty())
        {
            try
            {
                Thread.sleep(25L);
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
        }
    }

    public void queueIO(IThreadedFileIO ithreadedfileio)
    {
        if(threadedIOQueue.contains(ithreadedfileio))
        {
            return;
        } else
        {
            writeQueuedCounter++;
            threadedIOQueue.add(ithreadedfileio);
            return;
        }
    }

    public void waitForFinish()
        throws InterruptedException
    {
        isThreadWaiting = true;
        while(writeQueuedCounter != savedIOCounter) 
        {
            Thread.sleep(10L);
        }
        isThreadWaiting = false;
    }

}
