// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet27Position extends Packet
{

    private float strafeMovement;
    private float fowardMovement;
    private boolean isSneaking;
    private boolean isInJump;
    private float pitchRotation;
    private float yawRotation;

    public Packet27Position()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        strafeMovement = datainputstream.readFloat();
        fowardMovement = datainputstream.readFloat();
        pitchRotation = datainputstream.readFloat();
        yawRotation = datainputstream.readFloat();
        isSneaking = datainputstream.readBoolean();
        isInJump = datainputstream.readBoolean();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeFloat(strafeMovement);
        dataoutputstream.writeFloat(fowardMovement);
        dataoutputstream.writeFloat(pitchRotation);
        dataoutputstream.writeFloat(yawRotation);
        dataoutputstream.writeBoolean(isSneaking);
        dataoutputstream.writeBoolean(isInJump);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleMovementTypePacket(this);
    }

    public int getPacketSize()
    {
        return 18;
    }

    public float getStrafeMovement()
    {
        return strafeMovement;
    }

    public float getPitchRotation()
    {
        return pitchRotation;
    }

    public float getForwardMovement()
    {
        return fowardMovement;
    }

    public float getYawRotation()
    {
        return yawRotation;
    }

    public boolean isSneaking()
    {
        return isSneaking;
    }

    public boolean isInJump()
    {
        return isInJump;
    }
}
