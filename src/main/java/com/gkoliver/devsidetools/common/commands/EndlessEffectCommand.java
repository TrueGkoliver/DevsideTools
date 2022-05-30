package com.gkoliver.devsidetools.common.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;


import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.MobEffectArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EndlessEffectCommand extends Command {
	//Comment
	private static final SimpleCommandExceptionType GIVE_FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("commands.effect.give.failed"));
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
	      dispatcher.register(Commands.literal("effectinfinity").requires((p_198359_0_) -> {
	         return p_198359_0_.hasPermission(2);
	      }).then(Commands.argument("targets", EntityArgument.entities()).executes((context)->{
	    	  return addEffect(context.getSource(), EntityArgument.getEntities(context, "targets"), MobEffectArgument.getEffect(context, "effect"), IntegerArgumentType.getInteger(context, "amplifier"), true);
	      }).then(Commands.argument("effect", MobEffectArgument.effect()).executes((cmd) -> {
	    	  return addEffect(cmd.getSource(), EntityArgument.getEntities(cmd, "targets"), MobEffectArgument.getEffect(cmd, "effect"), 0, true);
	      }).then(Commands.argument("amplifier", IntegerArgumentType.integer(0, 255)).executes((p_198358_0_) -> {
	    	  return addEffect(p_198358_0_.getSource(), EntityArgument.getEntities(p_198358_0_, "targets"), MobEffectArgument.getEffect(p_198358_0_, "effect"), IntegerArgumentType.getInteger(p_198358_0_, "amplifier"), true);
	      }).then(Commands.argument("hideParticles", BoolArgumentType.bool()).executes((p_229759_0_) -> {
	         return addEffect(p_229759_0_.getSource(), EntityArgument.getEntities(p_229759_0_, "targets"), MobEffectArgument.getEffect(p_229759_0_, "effect"), IntegerArgumentType.getInteger(p_229759_0_, "amplifier"), !BoolArgumentType.getBool(p_229759_0_, "hideParticles"));
	      }))))));
	   }
	
	private static int addEffect(CommandSourceStack source, Collection<? extends Entity> targets, MobEffect effect, int amplifier, boolean showParticles) throws CommandSyntaxException {
	      int i = 0;
	      int j;
	      int seconds = 1000000;
	      if (seconds != 0) {
	         if (effect.isInstantenous()) {
	            j = seconds;
	         } else {
	            j = seconds * 20;
	         }
	      } else if (effect.isInstantenous()) {
	         j = 1;
	      } else {
	         j = 600;
	      }

	      for(Entity entity : targets) {
	         if (entity instanceof LivingEntity) {
	            MobEffectInstance effectinstance = new MobEffectInstance(effect, j, amplifier, false, showParticles);
	            if (((LivingEntity)entity).addEffect(effectinstance)) {
	               ++i;
	            }
	         }
	      }

	      if (i == 0) {
	         throw GIVE_FAILED_EXCEPTION.create();
	      } else {
	         if (targets.size() == 1) {
	            source.sendSuccess(new TranslatableComponent("commands.effect.give.success.single", effect.getDisplayName(), targets.iterator().next().getDisplayName(), j / 20), true);
	         } else {
	            source.sendSuccess(new TranslatableComponent("commands.effect.give.success.multiple", effect.getDisplayName(), targets.size(), j / 20), true);
	         }

	         return i;
	      }
	   }

}
