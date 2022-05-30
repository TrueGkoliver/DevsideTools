package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;


public class PlayerHealCommands {
	public static class HealCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("health")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSourceStack source, int amt, Collection<ServerPlayer> targets) {
			for (Player player : targets) {
				player.setHealth(amt);
				source.sendSuccess(new TranslatableComponent("commands.health"), true);
			}
			return 1;
		}
	}
	public static class SaturateCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("saturation")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSourceStack source, int amt, Collection<ServerPlayer> targets) {
			for (Player player : targets) {
				player.getFoodData().setSaturation(amt);
				source.sendSuccess(new TranslatableComponent("commands.saturate"), true);
			}
			return 1;
		}
	}
	
	public static class HungerCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("hunger")
					.then(Commands.argument("targets", EntityArgument.players()).executes((cmd)->{
						return heal(cmd.getSource(), 20, EntityArgument.getPlayers(cmd, "targets"));
					}).then(Commands.argument("amt", IntegerArgumentType.integer(0, 10000)).executes((cmd)->{
						return heal(cmd.getSource(), IntegerArgumentType.getInteger(cmd, "amt"), EntityArgument.getPlayers(cmd, "targets"));
					}))));
		}
		public static int heal(CommandSourceStack source, int amt, Collection<ServerPlayer> targets) {
			for (Player player : targets) {
				player.getFoodData().setFoodLevel(amt);
				source.sendSuccess(new TranslatableComponent("commands.hunger"), true);
			}
			return 1;
		}
	}

}
