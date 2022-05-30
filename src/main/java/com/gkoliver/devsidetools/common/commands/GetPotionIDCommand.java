package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MobEffectArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;


public class GetPotionIDCommand extends Command {
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("geteffectid").requires((cmd) -> {

	         return cmd.hasPermission(2);
	      }).then(Commands.argument("effect", MobEffectArgument.effect()).executes((cmd)->{
				return addEffect(cmd.getSource(),MobEffectArgument.getEffect(cmd, "effect"));
			}
		)));
		
	}
	
	private static int addEffect(CommandSourceStack source, MobEffect effect) throws CommandSyntaxException {

	  int id = MobEffect.getId(effect);
	  System.out.println(id);
	  source.sendSuccess(new TranslatableComponent("commands.getid.isPotion").append(effect.getDisplayName()).append(new TranslatableComponent("commands.getid.is")).append(new TranslatableComponent(String.valueOf(id))), true);

	  return 1;

   }

}
