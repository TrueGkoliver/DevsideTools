package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerHealCommands {
	public static class HealCommand extends Command {
		public void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("health")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSource source, int amt, Collection<ServerPlayerEntity> targets) {
			for (PlayerEntity player : targets) {
				player.setHealth(amt);
				source.sendFeedback(new TranslationTextComponent("commands.health"), true);
			}
			return 1;
		}
	}
	public static class SaturateCommand extends Command {
		public void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("saturation")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSource source, int amt, Collection<ServerPlayerEntity> targets) {
			for (PlayerEntity player : targets) {
				player.getFoodStats().setFoodSaturationLevel(amt);
				source.sendFeedback(new TranslationTextComponent("commands.saturate"), true);
			}
			return 1;
		}
	}
	
	public static class HungerCommand extends Command {
		public void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("hunger")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSource source, int amt, Collection<ServerPlayerEntity> targets) {
			for (PlayerEntity player : targets) {
				player.getFoodStats().setFoodLevel(amt);
				source.sendFeedback(new TranslationTextComponent("commands.hunger"), true);
			}
			return 1;
		}
	}

}
