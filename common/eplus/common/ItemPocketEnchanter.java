package eplus.common;

import eplus.common.localization.LocalizationHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * User: Stengel
 * Date: 2/24/13
 * Time: 2:06 PM
 */
public class ItemPocketEnchanter extends Item {

    public ItemPocketEnchanter(int par1) {
        super(par1);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return CreativeTabs.tabDecorations;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(LocalizationHelper.getLocalString("pocketEnchanter.info"));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) { // changed by Slash
        if (!par2World.isRemote) {
            if (EnchantingPlus.useMod) {
            	par3EntityPlayer.openGui(EnchantingPlus.instance, 1, par2World, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ);
            }
        }
        return par1ItemStack;
    }
    
    @Override
    public String getTextureFile () { // created by Slash
        return "/eplus/items.png";
    }
    
    @SideOnly(Side.CLIENT) // created by Slash
    @Override
    public int getIconFromDamage(int i){
    	return 2; 
    }
    
    @SideOnly(Side.CLIENT) // created by Slash
    @Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
