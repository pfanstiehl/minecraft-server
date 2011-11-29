// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.src:
//            Empty2

class ChunkFolderPattern
    implements FileFilter
{

    public static final Pattern folderRegexPattern = Pattern.compile("[0-9a-z]|([0-9a-z][0-9a-z])");

    private ChunkFolderPattern()
    {
    }

    public boolean accept(File file)
    {
        if(file.isDirectory())
        {
            Matcher matcher = folderRegexPattern.matcher(file.getName());
            return matcher.matches();
        } else
        {
            return false;
        }
    }

    ChunkFolderPattern(Empty2 empty2)
    {
        this();
    }

}
