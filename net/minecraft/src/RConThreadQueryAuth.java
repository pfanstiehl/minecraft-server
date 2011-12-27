// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.net.DatagramPacket;
import java.util.Date;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            RConThreadQuery

class RConThreadQueryAuth
{

    private long timestamp;
    private int randomChallenge;
    private byte requestID[];
    private byte challengeValue[];
    private String requestIDstring;
    final RConThreadQuery queryThread; /* synthetic field */

    public RConThreadQueryAuth(RConThreadQuery rconthreadquery, DatagramPacket datagrampacket)
    {
        queryThread = rconthreadquery;
//        super();
        timestamp = (new Date()).getTime();
        byte abyte0[] = datagrampacket.getData();
        requestID = new byte[4];
        requestID[0] = abyte0[3];
        requestID[1] = abyte0[4];
        requestID[2] = abyte0[5];
        requestID[3] = abyte0[6];
        requestIDstring = new String(requestID);
        randomChallenge = (new Random()).nextInt(0x1000000);
        challengeValue = String.format("\t%s%d\0", new Object[] {
            requestIDstring, Integer.valueOf(randomChallenge)
        }).getBytes();
    }

    public Boolean hasExpired(long l)
    {
        return Boolean.valueOf(timestamp < l);
    }

    public int getRandomChallenge()
    {
        return randomChallenge;
    }

    public byte[] getChallengeValue()
    {
        return challengeValue;
    }

    public byte[] getRequestID()
    {
        return requestID;
    }
}
