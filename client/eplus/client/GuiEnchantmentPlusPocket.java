package eplus.client;

import eplus.common.EnchantingPlus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * User: Stengel
 * Date: 2/24/13
 * Time: 2:29 PM
 */
public class GuiEnchantmentPlusPocket extends GuiEnchantmentPlus {

    public GuiEnchantmentPlusPocket(EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z);

        this.allowDisenchanting = EnchantingPlus.allowPocketDisenchanting; // modified by Slash
        this.allowRepair = EnchantingPlus.allowPocketRepair; // modified by Slash
        this.allowTransfer = EnchantingPlus.allowTransfer; // modified by Slash
    }
}
