package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.entity.player.PlayerEntity;

public class PanoramaCommand extends Command {
    @Override
    public void register(CommandDispatcher<CommandSource> dispatcher) {

    }
    public void makePanormaScrenshots(PlayerEntity playerIn) {
        Minecraft.getInstance().gameSettings.chatVisibility = ChatVisibility.HIDDEN;
        Minecraft.getInstance().gameSettings.hideGUI = true;
    }
}
