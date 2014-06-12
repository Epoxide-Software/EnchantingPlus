package com.aesireanempire.eplus.network.packets;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import com.aesireanempire.eplus.lib.References;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import java.net.ProtocolException;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by freyja
 */
public abstract class BasePacket
{
    private static final String CHANNEL = References.MODID;
    private static final BiMap<Integer, Class<? extends BasePacket>> idMap;

    public abstract void readBytes(ByteArrayDataInput bytes);

    public abstract void writeBytes(ByteArrayDataOutput bytes);

    public abstract void executeClient(EntityPlayer player);

    public abstract void executeServer(EntityPlayer player);

    static
    {
        final ImmutableBiMap.Builder<Integer, Class<? extends BasePacket>> builder = ImmutableBiMap.builder();
        builder.put(0, EnchantPacket.class);
        builder.put(1, GuiPacket.class);
        builder.put(2, RepairPacket.class);
        builder.put(3, ErrorPacket.class);
        builder.put(4, UnlockPacket.class);

        idMap = builder.build();
    }

    public static BasePacket constructPacket(int packetId) throws ProtocolException, IllegalAccessException, InstantiationException
    {
        final Class<? extends BasePacket> clazz = idMap.get(packetId);
        if (clazz == null)
        {
            throw new ProtocolException("Unknown Packet Id!");
        } else
        {
            return clazz.newInstance();
        }
    }

    final int getPacketId()
    {
        if (idMap.inverse().containsKey(getClass()))
        {
            return idMap.inverse().get(getClass());
        } else
        {
            throw new RuntimeException("Packet " + getClass().getSimpleName() + " is missing a mapping!");
        }
    }

    public final Packet makePacket()
    {
        final ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeByte(getPacketId());
        writeBytes(output);
        return PacketDispatcher.getPacket(CHANNEL, output.toByteArray());
    }

    public void execute(EntityPlayer entityPlayer, Side side)
    {
        switch (side)
        {
            case CLIENT:
                executeClient(entityPlayer);
                break;
            case SERVER:
                executeServer(entityPlayer);
                break;
        }
    }
}
