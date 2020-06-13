package com.gkoliver.devsidetools.commands;

import java.util.Collection;

import javax.annotation.Nullable;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.PotionArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TranslationTextComponent;

public class GetPotionIDCommand {
	private static final DynamicCommandExceptionType NONEXISTENT_POTION_EXCEPTION = new DynamicCommandExceptionType((obj) -> {
	    return new TranslationTextComponent("commands.getpotionid.nonexistentpotion", obj);
	});
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("geteffectid").requires((cmd) -> {
			System.out.println("FIRING THE WHATEVER");
	         return cmd.hasPermissionLevel(2);
	      }).then(Commands.argument("effect", PotionArgument.mobEffect()).executes((cmd)->{
				return addEffect(cmd.getSource(),PotionArgument.getMobEffect(cmd, "effect"));
			}
		)));
		
	}
	
	private static int addEffect(CommandSource source, Effect effect) throws CommandSyntaxException {
	      int i = 1;
	      int j;
	      int id = Effect.getId(effect);
	      System.out.println(id);
	      source.sendFeedback(new TranslationTextComponent("commands.getid.isPotion").appendSibling(effect.getDisplayName().appendSibling(new TranslationTextComponent("commands.getid.is")).appendText(String.valueOf(id))), true);
	      
	      return i;
	      
	   }

}
