package com.aesireanempire.eplus.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import com.aesireanempire.eplus.EnchantingPlus;
import com.aesireanempire.eplus.lib.ConfigurationSettings;
import com.aesireanempire.eplus.lib.GuiIds;

import net.minecraft.entity.player.EntityPlayer;

import java.nio.ByteBuffer;

/**
 * Created by freyja
 */
public class GuiPacket extends BasePacket
{
    private int guiId;
    private int xPos;
    private int yPos;
    private int zPos;
    private String username;

    public GuiPacket()
    {

    }

    public GuiPacket(String displayName, int guidId, int xPos, int yPos, int zPos)
    {
        this.username = displayName;
        this.guiId = guidId;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    @Override public void readBytes(ByteArrayDataInput bytes)
    {
        int length = bytes.readInt();
        username = bytes.readUTF();
        guiId = bytes.readInt();
        xPos = bytes.readInt();
        yPos = bytes.readInt();
        zPos = bytes.readInt();
    }

    @Override public void writeBytes(ByteArrayDataOutput bytes)
    {
        bytes.writeInt(username.length());
        bytes.writeUTF(username);

        bytes.writeInt(guiId);
        bytes.writeInt(xPos);
        bytes.writeInt(yPos);
        bytes.writeInt(zPos);
    }

    @Override public void executeClient(EntityPlayer player)
    {

    }

    @Override public void executeServer(EntityPlayer player)
    {
        switch (guiId)
        {
            case GuiIds.ModTable:
                if (ConfigurationSettings.useMod)
                {
                    player.openGui(EnchantingPlus.INSTANCE, guiId, player.worldObj, xPos, yPos, zPos);
                }
                break;

            case GuiIds.VanillaTable:
                player.openGui(EnchantingPlus.INSTANCE, guiId, player.worldObj, xPos, yPos, zPos);
                break;
        }
    }
}
