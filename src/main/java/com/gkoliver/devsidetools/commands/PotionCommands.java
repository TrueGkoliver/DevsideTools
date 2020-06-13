package com.gkoliver.devsidetools.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.PotionArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;

public class PotionCommands {
	public static class PotionCommand {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("potion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", PotionArgument.mobEffect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSource> contextIn, Collection<? extends Entity> entities, Effect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.POTION);
					CompoundNBT nbtToSet = new CompoundNBT();
					CompoundNBT potionNBT = new CompoundNBT();
					potionNBT.putByte("Id", (byte) Effect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListNBT activePotionEffects = new ListNBT();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayerEntity) {
						ServerPlayerEntity serverEntity = (ServerPlayerEntity) entityIn;
						serverEntity.addItemStackToInventory(stack);
					}
				}
			}
			
			return 1;
		}
	}
	
	public static class SplashPotionCommand {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("splashpotion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", PotionArgument.mobEffect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSource> contextIn, Collection<? extends Entity> entities, Effect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.SPLASH_POTION);
					CompoundNBT nbtToSet = new CompoundNBT();
					CompoundNBT potionNBT = new CompoundNBT();
					potionNBT.putByte("Id", (byte) Effect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListNBT activePotionEffects = new ListNBT();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayerEntity) {
						ServerPlayerEntity serverEntity = (ServerPlayerEntity) entityIn;
						serverEntity.addItemStackToInventory(stack);
					}
				}
			}
			
			return 1;
		}
	}
	
	public static class LingeringPotionCommand {
		public static void register(CommandDispatcher<CommandSource> dispatcher) {
			dispatcher.register(Commands.literal("lingeringpotion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermissionLevel(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", PotionArgument.mobEffect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), PotionArgument.getMobEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSource> contextIn, Collection<? extends Entity> entities, Effect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.LINGERING_POTION);
					CompoundNBT nbtToSet = new CompoundNBT();
					CompoundNBT potionNBT = new CompoundNBT();
					potionNBT.putByte("Id", (byte) Effect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListNBT activePotionEffects = new ListNBT();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayerEntity) {
						ServerPlayerEntity serverEntity = (ServerPlayerEntity) entityIn;
						serverEntity.addItemStackToInventory(stack);
					}
				}
			}
			
			return 1;
		}
	}

}
