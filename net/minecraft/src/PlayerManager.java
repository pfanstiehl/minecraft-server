// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

// Referenced classes of package net.minecraft.src:
//            LongHashMap, PlayerInstance, EntityPlayerMP, WorldServer

public class PlayerManager
{

    public List players;
    private LongHashMap playerInstances;
    private List playerInstancesToUpdate;
    private MinecraftServer mcServer;
    private int playerDimension;
    private int playerViewRadius;
    private final int xzDirectionsConst[][] = {
        {
            1, 0
        }, {
            0, 1
        }, {
            -1, 0
        }, {
            0, -1
        }
    };

    public PlayerManager(MinecraftServer minecraftserver, int i, int j)
    {
        players = new ArrayList();
        playerInstances = new LongHashMap();
        playerInstancesToUpdate = new ArrayList();
        if(j > 15)
        {
            throw new IllegalArgumentException("Too big view radius!");
        }
        if(j < 3)
        {
            throw new IllegalArgumentException("Too small view radius!");
        } else
        {
            playerViewRadius = j;
            mcServer = minecraftserver;
            playerDimension = i;
            return;
        }
    }

    public WorldServer getMinecraftServer()
    {
        return mcServer.getWorldManager(playerDimension);
    }

    public void updatePlayerInstances()
    {
        for(int i = 0; i < playerInstancesToUpdate.size(); i++)
        {
            ((PlayerInstance)playerInstancesToUpdate.get(i)).onUpdate();
        }

        playerInstancesToUpdate.clear();
    }

    private PlayerInstance getPlayerInstance(int i, int j, boolean flag)
    {
        long l = (long)i + 0x7fffffffL | (long)j + 0x7fffffffL << 32;
        PlayerInstance playerinstance = (PlayerInstance)playerInstances.getValueByKey(l);
        if(playerinstance == null && flag)
        {
            playerinstance = new PlayerInstance(this, i, j);
            playerInstances.add(l, playerinstance);
        }
        return playerinstance;
    }

    public void markBlockNeedsUpdate(int i, int j, int k)
    {
        int l = i >> 4;
        int i1 = k >> 4;
        PlayerInstance playerinstance = getPlayerInstance(l, i1, false);
        if(playerinstance != null)
        {
            playerinstance.markBlockNeedsUpdate(i & 0xf, j, k & 0xf);
        }
    }

    public void addPlayer(EntityPlayerMP entityplayermp)
    {
        int i = (int)entityplayermp.posX >> 4;
        int j = (int)entityplayermp.posZ >> 4;
        entityplayermp.managedPosX = entityplayermp.posX;
        entityplayermp.managedPosZ = entityplayermp.posZ;
        int k = 0;
        int l = playerViewRadius;
        int i1 = 0;
        int j1 = 0;
        getPlayerInstance(i, j, true).addPlayer(entityplayermp);
        for(int k1 = 1; k1 <= l * 2; k1++)
        {
            for(int i2 = 0; i2 < 2; i2++)
            {
                int ai[] = xzDirectionsConst[k++ % 4];
                for(int j2 = 0; j2 < k1; j2++)
                {
                    i1 += ai[0];
                    j1 += ai[1];
                    getPlayerInstance(i + i1, j + j1, true).addPlayer(entityplayermp);
                }

            }

        }

        k %= 4;
        for(int l1 = 0; l1 < l * 2; l1++)
        {
            i1 += xzDirectionsConst[k][0];
            j1 += xzDirectionsConst[k][1];
            getPlayerInstance(i + i1, j + j1, true).addPlayer(entityplayermp);
        }

        players.add(entityplayermp);
    }

    public void removePlayer(EntityPlayerMP entityplayermp)
    {
        int i = (int)entityplayermp.managedPosX >> 4;
        int j = (int)entityplayermp.managedPosZ >> 4;
        for(int k = i - playerViewRadius; k <= i + playerViewRadius; k++)
        {
            for(int l = j - playerViewRadius; l <= j + playerViewRadius; l++)
            {
                PlayerInstance playerinstance = getPlayerInstance(k, l, false);
                if(playerinstance != null)
                {
                    playerinstance.removePlayer(entityplayermp);
                }
            }

        }

        players.remove(entityplayermp);
    }

    private boolean isOutsidePlayerViewRadius(int i, int j, int k, int l)
    {
        int i1 = i - k;
        int j1 = j - l;
        if(i1 < -playerViewRadius || i1 > playerViewRadius)
        {
            return false;
        }
        return j1 >= -playerViewRadius && j1 <= playerViewRadius;
    }

    public void updateMountedMovingPlayer(EntityPlayerMP entityplayermp)
    {
        int i = (int)entityplayermp.posX >> 4;
        int j = (int)entityplayermp.posZ >> 4;
        double d = entityplayermp.managedPosX - entityplayermp.posX;
        double d1 = entityplayermp.managedPosZ - entityplayermp.posZ;
        double d2 = d * d + d1 * d1;
        if(d2 < 64D)
        {
            return;
        }
        int k = (int)entityplayermp.managedPosX >> 4;
        int l = (int)entityplayermp.managedPosZ >> 4;
        int i1 = i - k;
        int j1 = j - l;
        if(i1 == 0 && j1 == 0)
        {
            return;
        }
        for(int k1 = i - playerViewRadius; k1 <= i + playerViewRadius; k1++)
        {
            for(int l1 = j - playerViewRadius; l1 <= j + playerViewRadius; l1++)
            {
                if(!isOutsidePlayerViewRadius(k1, l1, k, l))
                {
                    getPlayerInstance(k1, l1, true).addPlayer(entityplayermp);
                }
                if(isOutsidePlayerViewRadius(k1 - i1, l1 - j1, i, j))
                {
                    continue;
                }
                PlayerInstance playerinstance = getPlayerInstance(k1 - i1, l1 - j1, false);
                if(playerinstance != null)
                {
                    playerinstance.removePlayer(entityplayermp);
                }
            }

        }

        entityplayermp.managedPosX = entityplayermp.posX;
        entityplayermp.managedPosZ = entityplayermp.posZ;
    }

    public int getMaxTrackingDistance()
    {
        return playerViewRadius * 16 - 16;
    }

    static LongHashMap getPlayerInstances(PlayerManager playermanager)
    {
        return playermanager.playerInstances;
    }

    static List getPlayerInstancesToUpdate(PlayerManager playermanager)
    {
        return playermanager.playerInstancesToUpdate;
    }
}
