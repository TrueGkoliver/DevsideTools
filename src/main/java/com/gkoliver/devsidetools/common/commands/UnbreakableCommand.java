package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;


public class UnbreakableCommand extends Command {
	private static final DynamicCommandExceptionType NO_ITEM = new DynamicCommandExceptionType((entity) -> {
	      return new TranslatableComponent("commands.unbreakable.failed.noitem", entity);
	});
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("unbreakable").requires((cmdsource) -> {
	         return cmdsource.hasPermission(2);
	    }).then(Commands.argument("targets", EntityArgument.entities()).executes((cmdsource)->{
	    	return createUnbreakable(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"));
	    })));
	}
	
	private static int createUnbreakable(CommandSourceStack source, Collection<? extends Entity> targets) throws CommandSyntaxException {
		for (Entity entity : targets) {
			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				ItemStack stack = livingEntity.getMainHandItem();
				if (!stack.isEmpty()) {
					CompoundTag nbtToModify = stack.getTag();
					nbtToModify.putBoolean("Unbreakable", true);
					stack.setTag(nbtToModify);
					source.sendSuccess(new TranslatableComponent("command.unbreakable.success"), true);
				} else {
					throw NO_ITEM.create(entity);
				}
			}
		}
		return 1;
	}

}
