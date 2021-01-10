package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.PotionArgument;
import net.minecraft.potion.Effect;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GetPotionIDCommand extends Command {
	public void register(CommandDispatcher<CommandSource> dispatcher) {
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
	  int id = Effect.getId(effect);
	  System.out.println(id);
	  source.sendFeedback(new TranslationTextComponent("commands.getid.isPotion").func_230529_a_(effect.getDisplayName()).func_230529_a_(new TranslationTextComponent("commands.getid.is")).func_230529_a_(new StringTextComponent(String.valueOf(id))), true);

	  return i;

   }

}
