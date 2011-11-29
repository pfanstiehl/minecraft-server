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

    public static final ThreadedFileIOBase field_40514_a = new ThreadedFileIOBase();
    private List field_40512_b;
    private volatile long field_40513_c;
    private volatile long field_40510_d;
    private volatile boolean field_40511_e;

    private ThreadedFileIOBase()
    {
        field_40512_b = Collections.synchronizedList(new ArrayList());
        field_40513_c = 0L;
        field_40510_d = 0L;
        field_40511_e = false;
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
        for(int i = 0; i < field_40512_b.size(); i++)
        {
            IThreadedFileIO ithreadedfileio = (IThreadedFileIO)field_40512_b.get(i);
            boolean flag = ithreadedfileio.func_40324_c();
            if(!flag)
            {
                field_40512_b.remove(i--);
                field_40510_d++;
            }
            try
            {
                if(!field_40511_e)
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

        if(field_40512_b.isEmpty())
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

    public void func_40507_a(IThreadedFileIO ithreadedfileio)
    {
        if(field_40512_b.contains(ithreadedfileio))
        {
            return;
        } else
        {
            field_40513_c++;
            field_40512_b.add(ithreadedfileio);
            return;
        }
    }

    public void func_40508_a()
        throws InterruptedException
    {
        field_40511_e = true;
        while(field_40513_c != field_40510_d) 
        {
            Thread.sleep(10L);
        }
        field_40511_e = false;
    }

}
