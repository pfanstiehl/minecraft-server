// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureMineshaftPieces, Block, 
//            World, TileEntityMobSpawner

public class ComponentMineshaftCorridor extends StructureComponent
{

    private final boolean field_35361_a;
    private final boolean field_35359_b;
    private boolean field_35360_c;
    private int field_35358_d;

    public ComponentMineshaftCorridor(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
        field_35361_a = random.nextInt(3) == 0;
        field_35359_b = !field_35361_a && random.nextInt(23) == 0;
        if(coordBaseMode == 2 || coordBaseMode == 0)
        {
            field_35358_d = structureboundingbox.bbDepth() / 5;
        } else
        {
            field_35358_d = structureboundingbox.bbWidth() / 5;
        }
    }

    public static StructureBoundingBox func_35357_a(List list, Random random, int i, int j, int k, int l)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);
        int i1 = random.nextInt(3) + 2;
        do
        {
            if(i1 <= 0)
            {
                break;
            }
            int j1 = i1 * 5;
            switch(l)
            {
            case 2: // '\002'
                structureboundingbox.x2 = i + 2;
                structureboundingbox.z1 = k - (j1 - 1);
                break;

            case 0: // '\0'
                structureboundingbox.x2 = i + 2;
                structureboundingbox.z2 = k + (j1 - 1);
                break;

            case 1: // '\001'
                structureboundingbox.x1 = i - (j1 - 1);
                structureboundingbox.z2 = k + 2;
                break;

            case 3: // '\003'
                structureboundingbox.x2 = i + (j1 - 1);
                structureboundingbox.z2 = k + 2;
                break;
            }
            if(StructureComponent.canFitInside(list, structureboundingbox) == null)
            {
                break;
            }
            i1--;
        } while(true);
        if(i1 > 0)
        {
            return structureboundingbox;
        } else
        {
            return null;
        }
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        int i = func_35305_c();
        int j = random.nextInt(4);
        switch(coordBaseMode)
        {
        case 2: // '\002'
            if(j <= 1)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1 - 1, coordBaseMode, i);
            } else
            if(j == 2)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1, 1, i);
            } else
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1, 3, i);
            }
            break;

        case 0: // '\0'
            if(j <= 1)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z2 + 1, coordBaseMode, i);
            } else
            if(j == 2)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z2 - 3, 1, i);
            } else
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z2 - 3, 3, i);
            }
            break;

        case 1: // '\001'
            if(j <= 1)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1, coordBaseMode, i);
            } else
            if(j == 2)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1 - 1, 2, i);
            } else
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z2 + 1, 0, i);
            }
            break;

        case 3: // '\003'
            if(j <= 1)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1, coordBaseMode, i);
            } else
            if(j == 2)
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 - 3, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z1 - 1, 2, i);
            } else
            {
                StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 - 3, (boundingBox.y1 - 1) + random.nextInt(3), boundingBox.z2 + 1, 0, i);
            }
            break;
        }
        if(i < 8)
        {
            if(coordBaseMode == 2 || coordBaseMode == 0)
            {
                for(int k = boundingBox.z1 + 3; k + 3 <= boundingBox.z2; k += 5)
                {
                    int i1 = random.nextInt(5);
                    if(i1 == 0)
                    {
                        StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x1 - 1, boundingBox.y1, k, 1, i + 1);
                    } else
                    if(i1 == 1)
                    {
                        StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, boundingBox.x2 + 1, boundingBox.y1, k, 3, i + 1);
                    }
                }

            } else
            {
                for(int l = boundingBox.x1 + 3; l + 3 <= boundingBox.x2; l += 5)
                {
                    int j1 = random.nextInt(5);
                    if(j1 == 0)
                    {
                        StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, l, boundingBox.y1, boundingBox.z1 - 1, 2, i + 1);
                        continue;
                    }
                    if(j1 == 1)
                    {
                        StructureMineshaftPieces.getNextComponent(structurecomponent, list, random, l, boundingBox.y1, boundingBox.z2 + 1, 0, i + 1);
                    }
                }

            }
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        int i = field_35358_d * 5 - 1;
        fillWithBlocks(world, structureboundingbox, 0, 0, 0, 2, 1, i, 0, 0, false);
        randomlyFillWithBlocks(world, structureboundingbox, random, 0.8F, 0, 2, 0, 2, 2, i, 0, 0, false);
        if(field_35359_b)
        {
            randomlyFillWithBlocks(world, structureboundingbox, random, 0.6F, 0, 0, 0, 2, 1, i, Block.web.blockID, 0, false);
        }
        for(int j = 0; j < field_35358_d; j++)
        {
            int l = 2 + j * 5;
            fillWithBlocks(world, structureboundingbox, 0, 0, l, 0, 1, l, Block.fence.blockID, 0, false);
            fillWithBlocks(world, structureboundingbox, 2, 0, l, 2, 1, l, Block.fence.blockID, 0, false);
            if(random.nextInt(4) != 0)
            {
                fillWithBlocks(world, structureboundingbox, 0, 2, l, 2, 2, l, Block.planks.blockID, 0, false);
            } else
            {
                fillWithBlocks(world, structureboundingbox, 0, 2, l, 0, 2, l, Block.planks.blockID, 0, false);
                fillWithBlocks(world, structureboundingbox, 2, 2, l, 2, 2, l, Block.planks.blockID, 0, false);
            }
            randomlyPlaceBlock(world, structureboundingbox, random, 0.1F, 0, 2, l - 1, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.1F, 2, 2, l - 1, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.1F, 0, 2, l + 1, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.1F, 2, 2, l + 1, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 0, 2, l - 2, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 2, 2, l - 2, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 0, 2, l + 2, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 2, 2, l + 2, Block.web.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 1, 2, l - 1, Block.torchWood.blockID, 0);
            randomlyPlaceBlock(world, structureboundingbox, random, 0.05F, 1, 2, l + 1, Block.torchWood.blockID, 0);
            if(random.nextInt(100) == 0)
            {
                createTreasureChestAtCurrentPosition(world, structureboundingbox, random, 2, 0, l - 1, StructureMineshaftPieces.getTreasurePieces(), 3 + random.nextInt(4));
            }
            if(random.nextInt(100) == 0)
            {
                createTreasureChestAtCurrentPosition(world, structureboundingbox, random, 0, 0, l + 1, StructureMineshaftPieces.getTreasurePieces(), 3 + random.nextInt(4));
            }
            if(!field_35359_b || field_35360_c)
            {
                continue;
            }
            int j1 = getYWithOffset(0);
            int k1 = (l - 1) + random.nextInt(3);
            int l1 = getXWithOffset(1, k1);
            k1 = getZWithOffset(1, k1);
            if(!structureboundingbox.isInBbVolume(l1, j1, k1))
            {
                continue;
            }
            field_35360_c = true;
            world.setBlockWithNotify(l1, j1, k1, Block.mobSpawner.blockID);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getBlockTileEntity(l1, j1, k1);
            if(tileentitymobspawner != null)
            {
                tileentitymobspawner.setMobID("CaveSpider");
            }
        }

        if(field_35361_a)
        {
            for(int k = 0; k <= i; k++)
            {
                int i1 = func_35297_a(world, 1, -1, k, structureboundingbox);
                if(i1 > 0 && Block.opaqueCubeLookup[i1])
                {
                    randomlyPlaceBlock(world, structureboundingbox, random, 0.7F, 1, 0, k, Block.rail.blockID, func_35301_c(Block.rail.blockID, 0));
                }
            }

        }
        return true;
    }
}
