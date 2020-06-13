package com.gkoliver.devsidetools.commands;

import java.util.Collection;

import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.PotionArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TranslationTextComponent;

public class EndlessEffectCommand {
	//Comment
	private static final SimpleCommandExceptionType GIVE_FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.effect.give.failed"));
    private static final SimpleCommandExceptionType CLEAR_EVERYTHING_FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.effect.clear.everything.failed"));
    private static final SimpleCommandExceptionType CLEAR_SPECIFIC_FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.effect.clear.specific.failed"));
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
	      dispatcher.register(Commands.literal("effectinfinity").requires((p_198359_0_) -> {
	         return p_198359_0_.hasPermissionLevel(2);
	      }).then(Commands.argument("targets", EntityArgument.entities()).executes((context)->{
	    	  return addEffect(context.getSource(), EntityArgument.getEntities(context, "targets"), PotionArgument.getMobEffect(context, "effect"), IntegerArgumentType.getInteger(context, "amplifier"), true);
	      }).then(Commands.argument("effect", PotionArgument.mobEffect()).executes((cmd) -> {
	    	  return addEffect(cmd.getSource(), EntityArgument.getEntities(cmd, "targets"), PotionArgument.getMobEffect(cmd, "effect"), 0, true);
	      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((p_198358_0_) -> {
	    	  return addEffect(p_198358_0_.getSource(), EntityArgument.getEntities(p_198358_0_, "targets"), PotionArgument.getMobEffect(p_198358_0_, "effect"), IntegerArgumentType.getInteger(p_198358_0_, "amplifier"), true);
	      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((p_229759_0_) -> {
	         return addEffect(p_229759_0_.getSource(), EntityArgument.getEntities(p_229759_0_, "targets"), PotionArgument.getMobEffect(p_229759_0_, "effect"), IntegerArgumentType.getInteger(p_229759_0_, "amplifier"), !BoolArgumentType.getBool(p_229759_0_, "hideParticles"));
	      }))))));
	   }
	
	private static int addEffect(CommandSource source, Collection<? extends Entity> targets, Effect effect, int amplifier, boolean showParticles) throws CommandSyntaxException {
	      int i = 0;
	      int j;
	      int seconds = 1000000;
	      if (seconds != 0) {
	         if (effect.isInstant()) {
	            j = seconds;
	         } else {
	            j = seconds * 20;
	         }
	      } else if (effect.isInstant()) {
	         j = 1;
	      } else {
	         j = 600;
	      }

	      for(Entity entity : targets) {
	         if (entity instanceof LivingEntity) {
	            EffectInstance effectinstance = new EffectInstance(effect, j, amplifier, false, showParticles);
	            if (((LivingEntity)entity).addPotionEffect(effectinstance)) {
	               ++i;
	            }
	         }
	      }

	      if (i == 0) {
	         throw GIVE_FAILED_EXCEPTION.create();
	      } else {
	         if (targets.size() == 1) {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.give.success.single", effect.getDisplayName(), targets.iterator().next().getDisplayName(), j / 20), true);
	         } else {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.give.success.multiple", effect.getDisplayName(), targets.size(), j / 20), true);
	         }

	         return i;
	      }
	   }

	   private static int clearAllEffects(CommandSource source, Collection<? extends Entity> targets) throws CommandSyntaxException {
	      int i = 0;

	      for(Entity entity : targets) {
	         if (entity instanceof LivingEntity && ((LivingEntity)entity).clearActivePotions()) {
	            ++i;
	         }
	      }

	      if (i == 0) {
	         throw CLEAR_EVERYTHING_FAILED_EXCEPTION.create();
	      } else {
	         if (targets.size() == 1) {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.clear.everything.success.single", targets.iterator().next().getDisplayName()), true);
	         } else {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.clear.everything.success.multiple", targets.size()), true);
	         }

	         return i;
	      }
	   }

	   private static int clearEffect(CommandSource source, Collection<? extends Entity> targets, Effect effect) throws CommandSyntaxException {
	      int i = 0;

	      for(Entity entity : targets) {
	         if (entity instanceof LivingEntity && ((LivingEntity)entity).removePotionEffect(effect)) {
	            ++i;
	         }
	      }

	      if (i == 0) {
	         throw CLEAR_SPECIFIC_FAILED_EXCEPTION.create();
	      } else {
	         if (targets.size() == 1) {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.clear.specific.success.single", effect.getDisplayName(), targets.iterator().next().getDisplayName()), true);
	         } else {
	            source.sendFeedback(new TranslationTextComponent("commands.effect.clear.specific.success.multiple", effect.getDisplayName(), targets.size()), true);
	         }

	         return i;
	      }
	   }

}
