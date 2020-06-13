package com.gkoliver.devsidetools.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class SetCountCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setcount").requires((cmdsource)->{
			return cmdsource.hasPermissionLevel(2);
		}).then(Commands.argument("targets", EntityArgument.entities()).executes((cmd)->{
			return 1;
		})
		  .then(Commands.argument("amt", IntegerArgumentType.integer(-100000, 100000)).executes((cmd)->{
			return setCount(EntityArgument.getEntities(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amt"));
		}))));
	}
	
	
	public static int setCount(Collection<? extends Entity> targets, int count) {
		for (Entity target : targets) {
			if (target instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) target;
				ItemStack stack = living.getHeldItemMainhand();
				stack.setCount(count);
			}
		}
		return 1;
	}

}
