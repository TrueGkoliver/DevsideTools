package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;

public interface ICommand {
	public void register(CommandDispatcher<CommandSource> dispatcher);

}
