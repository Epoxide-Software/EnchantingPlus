package com.aesireanempire.eplus.handlers;

import com.aesireanempire.eplus.lib.References;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * @author Freyja Lesser GNU Public License v3
 *         (http://www.gnu.org/licenses/lgpl.html)
 */
public class VersionTickHandler implements ITickHandler
{
    private boolean messageSent;

    public void sendChatToPlayer(EntityPlayer player, String message)
    {
        player.sendChatToPlayer(ChatMessageComponent.createFromText(message));
    }

    @Override public void tickStart(EnumSet<TickType> tickTypes, Object... objects)
    {
        if (messageSent)
        {
            return;
        }

        final EntityPlayer player = (EntityPlayer) objects[1];

        if (Version.versionSeen() && Version.isVersionCheckComplete())
        {
            if (Version.hasUpdated())
            {
                sendChatToPlayer(player, String.format("[%s]: %s: %s", References.MODID, "Version update is available", Version.getRecommendedVersion()));
                sendChatToPlayer(player, String.format("[%s]: to view a changelog use: %s", References.MODID, "/eplus changelog"));
            }
        }
        messageSent = true;
    }

    @Override public void tickEnd(EnumSet<TickType> tickTypes, Object... objects)
    {

    }

    @Override public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override public String getLabel()
    {
        return "version_tick_handler";
    }
}
