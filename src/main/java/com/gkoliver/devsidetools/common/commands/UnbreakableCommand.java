package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;

public class UnbreakableCommand extends Command {
	private static final DynamicCommandExceptionType NO_ITEM = new DynamicCommandExceptionType((entity) -> {
	      return new TranslationTextComponent("commands.unbreakable.failed.noitem", entity);
	});
	public void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("unbreakable").requires((cmdsource) -> {
	         return cmdsource.hasPermissionLevel(2);
	    }).then(Commands.argument("targets", EntityArgument.entities()).executes((cmdsource)->{
	    	return createUnbreakable(cmdsource.getSource(), EntityArgument.getEntities(cmdsource, "targets"));
	    })));
	}
	
	private static int createUnbreakable(CommandSource source, Collection<? extends Entity> targets) throws CommandSyntaxException {
		for (Entity entity : targets) {
			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				ItemStack stack = livingEntity.getHeldItemMainhand();
				if (!stack.isEmpty()) {
					CompoundNBT nbtToModify = stack.getTag();
					nbtToModify.putBoolean("Unbreakable", true);
					stack.setTag(nbtToModify);
					source.sendFeedback(new TranslationTextComponent("command.unbreakable.success"), true);
				} else {
					throw NO_ITEM.create(entity);
				}
			}
		}
		return 1;
	}

}
