package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MobEffectArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class PotionCommands {
	public static class PotionCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("potion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermission(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", MobEffectArgument.effect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSourceStack> contextIn, Collection<? extends Entity> entities, MobEffect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.POTION);
					CompoundTag nbtToSet = new CompoundTag();
					CompoundTag potionNBT = new CompoundTag();
					potionNBT.putByte("Id", (byte) MobEffect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListTag activePotionEffects = new ListTag();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayer) {
						ServerPlayer serverEntity = (ServerPlayer) entityIn;
						contextIn.getSource().sendSuccess(new TranslatableComponent("commands.givepotion").append(stack.getDisplayName()), true);
						serverEntity.addItem(stack);
					}
				}
			}
			
			return 1;
		}
	}
	
	public static class SplashPotionCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("splashpotion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermission(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", MobEffectArgument.effect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSourceStack> contextIn, Collection<? extends Entity> entities, MobEffect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.SPLASH_POTION);
					CompoundTag nbtToSet = new CompoundTag();
					CompoundTag potionNBT = new CompoundTag();
					potionNBT.putByte("Id", (byte) MobEffect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListTag activePotionEffects = new ListTag();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayer) {
						ServerPlayer serverEntity = (ServerPlayer) entityIn;
						contextIn.getSource().sendSuccess(new TranslatableComponent("commands.givepotion").append(stack.getDisplayName()), true);
						serverEntity.addItem(stack);
					}
				}
			}
			
			return 1;
		}
	}
	
	public static class LingeringPotionCommand extends Command {
		public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("lingeringpotion").requires((p_198359_0_) -> {
		         return p_198359_0_.hasPermission(2);
		      }).then(Commands.argument("targets", EntityArgument.entities()).executes((ctx)->{
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("effect", MobEffectArgument.effect()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("seconds", IntegerArgumentType.integer(0, 1000000)).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, true, true);
		      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), false, !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("ambient", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), true);
		      }).then(Commands.argument("showIcon", BoolArgumentType.bool()).executes((ctx) -> {
		    	  return givePotion(ctx, EntityArgument.getEntities(ctx, "targets"), MobEffectArgument.getEffect(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "amplifier"), IntegerArgumentType.getInteger(ctx, "seconds"), BoolArgumentType.getBool(ctx, "ambient"), !BoolArgumentType.getBool(ctx, "hideParticles"), BoolArgumentType.getBool(ctx, "showIcon"));
		 
		      })
		    		  
		    		  
		   ))))))));
		}
		public static int givePotion(CommandContext<CommandSourceStack> contextIn, Collection<? extends Entity> entities, MobEffect effectIn, int amplifier, int seconds, boolean ambient, boolean showParticles, boolean showIcon) {
			//EffectInstance instance = new EffectInstance(effectIn, amplifier, seconds, ambient, showParticles, showIcon);
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					LivingEntity entityIn = (LivingEntity) entity;
					ItemStack stack = new ItemStack(Items.LINGERING_POTION);
					CompoundTag nbtToSet = new CompoundTag();
					CompoundTag potionNBT = new CompoundTag();
					potionNBT.putByte("Id", (byte) MobEffect.getId(effectIn));
					potionNBT.putByte("Amplifier", (byte) amplifier);
					potionNBT.putInt("Duration", seconds*20);
					potionNBT.putBoolean("Ambient", ambient);
					potionNBT.putBoolean("ShowParticles", showParticles);
					potionNBT.putBoolean("ShowIcon", showIcon);
					ListTag activePotionEffects = new ListTag();
					activePotionEffects.add(potionNBT);
					nbtToSet.put("CustomPotionEffects", activePotionEffects);
					stack.setTag(nbtToSet);
					if (entityIn instanceof ServerPlayer) {
						ServerPlayer serverEntity = (ServerPlayer) entityIn;
						contextIn.getSource().sendSuccess(new TranslatableComponent("commands.givepotion").append(stack.getDisplayName()), true);
						serverEntity.addItem(stack);
					}
				}
			}
			
			return 1;
		}
	}

}
