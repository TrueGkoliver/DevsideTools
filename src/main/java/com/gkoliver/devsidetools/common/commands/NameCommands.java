package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ComponentArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class NameCommands {
	public static class NameRawCommands extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("nameraw").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermission(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).then(Commands.argument("message", ComponentArgument.textComponent()).executes(
		    		  (context) -> {
		    			  return renameInt(context, EntityArgument.getEntities(context, "targets"), ComponentArgument.getComponent(context, "message"));
		    		  }
		      ))));
		}
		public static int renameInt(CommandContext<CommandSourceStack> source, Collection<? extends Entity> targets, Component component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getMainHandItem();
					if (!stack.isEmpty()) {
						stack.setHoverName(component);
						source.getSource().sendSuccess(new TranslatableComponent("commands.name"), true);
					}
				}
			}
			return 1;
		}
	}
	public static class LoreRawCommands extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("loreraw").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermission(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()
		       ).then(Commands.argument("message", ComponentArgument.textComponent()).executes(
		    		  (context) -> {
		    			  return renameInt(context, EntityArgument.getPlayers(context, "targets"), ComponentArgument.getComponent(context, "message"));
		    		  }
		      ))));
		}
		public static int renameInt(CommandContext<CommandSourceStack> source, Collection<? extends Entity> targets, Component component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getMainHandItem();
					if (!stack.isEmpty()) {
						ListTag list = new ListTag();
						list.add(StringTag.valueOf(TextComponent.Serializer.toJson(component)));
						CompoundTag nbt = stack.getOrCreateTagElement("display");
						nbt.put("Lore",list);
						source.getSource().sendSuccess(new TranslatableComponent("commands.lore"), true);
					}
				}
			}
			return 1;
		}
	}
}
