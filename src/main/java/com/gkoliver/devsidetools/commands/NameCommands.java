package com.gkoliver.devsidetools.commands;

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
import net.minecraft.util.text.TextComponent;

public class NameCommands {
	public static class NameCommand {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("name").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()))
				.then(Commands.argument("message", MessageArgument.message()).executes((cmd)->{
					return nameItem(cmd, EntityArgument.getEntities(cmd, "targets"), MessageArgument.getMessage(cmd, "message"));
				})));
		}
		public static int nameItem(CommandContext<CommandSource> source, Collection<? extends Entity> targets, ITextComponent component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getHeldItemMainhand();
					if (!stack.isEmpty()) {
						stack.setDisplayName(component);
					}
				}
			}
			return 1;
		}
	}
	public static class NameRawCommands {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
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
					}
				}
			}
			return 1;
		}
	}
	public static class LoreCommand {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("lore").requires((p_198820_0_) -> {
		         return p_198820_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("target", EntityArgument.players()).executes((context)->{
		    	  return nameItem(context, EntityArgument.getPlayers(context, "target"), new StringTextComponent("null"));
		      }))
				.then(Commands.argument("message", MessageArgument.message()).executes((cmd)->{
					return nameItem(cmd, EntityArgument.getPlayers(cmd, "target"), MessageArgument.getMessage(cmd, "message"));
				})));
		}
		public static int nameItem(CommandContext<CommandSource> source, Collection<? extends Entity> targets, ITextComponent component) {
			for (Entity entity : targets) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					ItemStack stack = livingEntity.getHeldItemMainhand();
					if (!stack.isEmpty()) {
						ListNBT list = new ListNBT();
						list.add(StringNBT.valueOf(component.getFormattedText()));
						CompoundNBT nbt = stack.getOrCreateChildTag("display");
						nbt.put("Lore",list);
						
;
					}
				}
			}
			return 1;
		}
	}
	public static class LoreRawCommands {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
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
					}
				}
			}
			return 1;
		}
	}
}
