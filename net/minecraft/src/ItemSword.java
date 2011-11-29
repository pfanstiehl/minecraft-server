// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EnumToolMaterial, Block, ItemStack, 
//            EnumAction, EntityPlayer, EntityLiving, Entity, 
//            World

public class ItemSword extends Item
{

    private int weaponDamage;
    private final EnumToolMaterial field_40249_bQ;

    public ItemSword(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i);
        field_40249_bQ = enumtoolmaterial;
        maxStackSize = 1;
        setMaxDamage(enumtoolmaterial.getMaxUses());
        weaponDamage = 4 + enumtoolmaterial.getDamageVsEntity();
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return block.blockID != Block.web.blockID ? 1.5F : 15F;
    }

    public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damageItem(1, entityliving1);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damageItem(2, entityliving);
        return true;
    }

    public int getDamageVsEntity(Entity entity)
    {
        return weaponDamage;
    }

    public EnumAction func_35406_b(ItemStack itemstack)
    {
        return EnumAction.block;
    }

    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.func_35201_a(itemstack, getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    public boolean canHarvestBlock(Block block)
    {
        return block.blockID == Block.web.blockID;
    }

    public int getItemEnchantability()
    {
        return field_40249_bQ.getEnchantability();
    }
}
