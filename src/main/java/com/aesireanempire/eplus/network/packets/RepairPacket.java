package com.aesireanempire.eplus.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import com.aesireanempire.eplus.EnchantingPlus;
import com.aesireanempire.eplus.inventory.ContainerEnchantTable;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by freyja
 */
public class RepairPacket extends BasePacket
{
    protected int totalCost;
    protected int repairAmount;

    public RepairPacket()
    {

    }

    public RepairPacket(int totalCost, int repairAmount)
    {
        this.totalCost = totalCost;
        this.repairAmount = repairAmount;
    }

    @Override public void readBytes(ByteArrayDataInput bytes)
    {
        totalCost = bytes.readInt();
        repairAmount = bytes.readInt();
    }

    @Override public void writeBytes(ByteArrayDataOutput bytes)
    {
        bytes.writeInt(totalCost);
        bytes.writeInt(repairAmount);
    }

    @Override public void executeClient(EntityPlayer player)
    {

    }

    @Override public void executeServer(EntityPlayer player)
    {
        if (player.openContainer instanceof ContainerEnchantTable)
        {
            try
            {
                ((ContainerEnchantTable) player.openContainer).repair(player, totalCost, repairAmount);
            }
            catch (Exception e)
            {
                EnchantingPlus.log.info("Repair failed because: " + e.getLocalizedMessage());
                EnchantingPlus.sendPacketToPlayer(new ErrorPacket(e.getLocalizedMessage()), player);
            }
            player.openContainer.detectAndSendChanges();
        }
    }
}
