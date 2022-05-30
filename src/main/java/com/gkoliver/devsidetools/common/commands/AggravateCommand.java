package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;
import java.util.Set;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;


import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class AggravateCommand extends Command {
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("aggravate")
				.then(Commands.argument("aggressor", EntityArgument.entities())
				.then(Commands.argument("aggression", EntityArgument.entity()).executes((context)->{
					return aggravate(context.getSource(), EntityArgument.getEntities(context, "aggressor"), EntityArgument.getEntity(context, "aggression"), false);
				}).then(Commands.argument("doBoth", BoolArgumentType.bool()).executes((context)->{
					return aggravate(context.getSource(), EntityArgument.getEntities(context, "aggressor"), EntityArgument.getEntity(context, "aggression"), BoolArgumentType.getBool(context, "doBoth"));
				})))
		));
	}
	public static int aggravate(CommandSourceStack source, Collection<? extends Entity> aggressor, Entity aggression, boolean doBoth) {
		if (aggression instanceof LivingEntity) {
			for (Entity entity : aggressor) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingAggressor = (LivingEntity)entity;
					LivingEntity livingVictim = (LivingEntity)aggression;
					livingAggressor.setLastHurtByMob(livingVictim);
					if (doBoth) {
						livingVictim.setLastHurtByMob(livingAggressor);
					}
					
				}
			}
			//LivingEntity aggressor1 = (LivingEntity) aggressor;
			//LivingEntity aggression1 = (LivingEntity) aggression;
			//aggressor1.setRevengeTarget(aggression1);
			//if (doBoth) {
			//	aggression1.setRevengeTarget(aggressor1);
			//}
			source.sendSuccess(new TranslatableComponent("commands.aggravate"), true);
			
		}
		return 1;
	}

}
