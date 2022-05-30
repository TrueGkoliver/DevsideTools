package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;


public class SetCountCommand extends Command {
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("setcount").requires((cmdsource)->{
			return cmdsource.hasPermission(2);
		}).then(Commands.argument("targets", EntityArgument.entities()).executes((cmd)->{
			return 1;
		})
		  .then(Commands.argument("amt", IntegerArgumentType.integer(-100000, 100000)).executes((cmd)->{
			return setCount(cmd.getSource(), EntityArgument.getEntities(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amt"));
		}))));
	}
	
	
	public static int setCount(CommandSourceStack source, Collection<? extends Entity> targets, int count) {
		for (Entity target : targets) {
			if (target instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) target;
				ItemStack stack = living.getMainHandItem();
				stack.setCount(count);
				source.sendSuccess(new TranslatableComponent("commands.setcount"), true);
				
			}
		}
		return 1;
	}

}
