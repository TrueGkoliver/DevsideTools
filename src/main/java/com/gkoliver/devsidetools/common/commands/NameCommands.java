package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ComponentArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class NameCommands {
	public static class NameRawCommands extends Command {
		public void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("nameraw").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).then(Commands.argument("message", ComponentArgument.component()).executes(
		    		  (context) -> {
		    			  return renameInt(context, EntityArgument.getEntities(context, "targets"), ComponentArgument.getComponent(context, "message"));
		    		  }
		      ))));
		}
		public static int renameInt(CommandContext<CommandSource> source, Collection<? extends Entity> targets, ITextComponent component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getHeldItemMainhand();
					if (!stack.isEmpty()) {
						stack.setDisplayName(component);
						source.getSource().sendFeedback(new TranslationTextComponent("commands.name"), true);
					}
				}
			}
			return 1;
		}
	}
	public static class LoreRawCommands extends Command {
		public void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("loreraw").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()
		       ).then(Commands.argument("message", ComponentArgument.component()).executes(
		    		  (context) -> {
		    			  return renameInt(context, EntityArgument.getPlayers(context, "targets"), ComponentArgument.getComponent(context, "message"));
		    		  }
		      ))));
		}
		public static int renameInt(CommandContext<CommandSource> source, Collection<? extends Entity> targets, ITextComponent component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getHeldItemMainhand();
					if (!stack.isEmpty()) {
						ListNBT list = new ListNBT();
						list.add(StringNBT.valueOf(ITextComponent.Serializer.toJson(component)));
						CompoundNBT nbt = stack.getOrCreateChildTag("display");
						nbt.put("Lore",list);
						source.getSource().sendFeedback(new TranslationTextComponent("commands.lore"), true);
					}
				}
			}
			return 1;
		}
	}
}
