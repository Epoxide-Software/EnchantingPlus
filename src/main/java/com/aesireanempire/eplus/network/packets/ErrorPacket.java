package com.aesireanempire.eplus.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import com.aesireanempire.eplus.gui.GuiModTable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * Created by freyja
 */
public class ErrorPacket extends BasePacket
{
    protected String error;

    public ErrorPacket()
    {

    }

    public ErrorPacket(String localizedMessage)
    {
        this.error = localizedMessage;
    }

    @Override public void readBytes(ByteArrayDataInput bytes)
    {
        error = bytes.readUTF();
    }

    @Override public void writeBytes(ByteArrayDataOutput bytes)
    {
        bytes.writeUTF(error);

    }

    @Override public void executeClient(EntityPlayer player)
    {
        Minecraft client = FMLClientHandler.instance().getClient();

        if (client.currentScreen instanceof GuiModTable)
        {
            ((GuiModTable) client.currentScreen).error = error;
        }
    }

    @Override public void executeServer(EntityPlayer player)
    {

    }
}
