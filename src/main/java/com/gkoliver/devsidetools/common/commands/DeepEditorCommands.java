package com.gkoliver.devsidetools.common.commands;

import com.gkoliver.devsidetools.core.deepeditor.ItemEditorFrame;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.ItemStack;

public class DeepEditorCommands {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("opengui").requires((cmdsource) -> {
            return cmdsource.hasPermissionLevel(2);
        }).executes((context)->{
            ItemStack stackIn = context.getSource().asPlayer().getHeldItemMainhand();
            int slotId = context.getSource().asPlayer().inventory.getSlotFor(stackIn);
            try {
                if (Minecraft.getInstance().world.isRemote()) {
                    new ItemEditorFrame(stackIn, context.getSource().asPlayer().container, slotId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 1;
        }));
    }
}
