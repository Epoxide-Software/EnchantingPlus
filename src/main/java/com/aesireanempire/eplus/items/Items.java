package com.aesireanempire.eplus.items;

import com.aesireanempire.eplus.EnchantingPlus;
import com.aesireanempire.eplus.lib.ConfigurationSettings;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items
{

    public static void init()
    {
        EnchantingPlus.log.info("Initializing Items.");
        final Item tableUpgrade = new ItemTableUpgrade(ConfigurationSettings.upgradeID).setUnlocalizedName("tableUpgrade");
        LanguageRegistry.addName(tableUpgrade, "Table Upgrade");

        GameRegistry.registerItem(tableUpgrade, tableUpgrade.getUnlocalizedName());

        CraftingManager.getInstance()
                .addRecipe(new ItemStack(tableUpgrade), "gbg", "o o", "geg", 'b', Item.writableBook, 'o', Block.obsidian, 'e', Item.eyeOfEnder, 'g',
                        Item.ingotGold);

    }

}
