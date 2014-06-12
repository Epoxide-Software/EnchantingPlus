package com.aesireanempire.eplus.network.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import com.aesireanempire.eplus.EnchantingPlus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.net.ProtocolException;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by freyja
 */
public class ChannelHandler implements IPacketHandler
{

    @Override public void onPacketData(INetworkManager iNetworkManager, Packet250CustomPayload packet, Player player)
    {
        try
        {
            final EntityPlayer entityPlayer = (EntityPlayer) player;
            final ByteArrayDataInput input = ByteStreams.newDataInput(packet.data);
            final int packetId = input.readUnsignedByte();

            final BasePacket basePacket = BasePacket.constructPacket(packetId);
            basePacket.readBytes(input);
            basePacket.execute(entityPlayer, entityPlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
        } catch (final ProtocolException ex)
        {
            if (player instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
                EnchantingPlus.log.warning(((EntityPlayer) player).username + " cause a Protocol Exception!");
            }
        } catch (final Exception ex)
        {
            throw new RuntimeException("Unexpected Reflection exception during Packet construction!", ex);
        }
    }
}
