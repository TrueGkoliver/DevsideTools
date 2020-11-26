package com.gkoliver.devsidetools.common.commands;

import com.gkoliver.devsidetools.DevsideTools;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

public abstract class Command {
    public Command() {
        DevsideTools.commands.add(this);
    }
    public abstract void register(CommandDispatcher<CommandSource> dispatcher);
}
