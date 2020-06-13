package com.gkoliver.devsidetools.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GetColorCodeCommand {
	private static final SimpleCommandExceptionType NOT_ACCURATE_EXCEPTION = new SimpleCommandExceptionType(new TranslationTextComponent("commands.getcolorcode.notaccurate"));
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("getcolorcode").requires(
			(cmd)->{
				return cmd.hasPermissionLevel(2);
			}).then(Commands.argument("hex", StringArgumentType.string()).executes((cmd)->{
				return getColorCode(cmd.getSource(), StringArgumentType.getString(cmd, "hex"));
			}))
				
		);
	}
	public static int getColorCode(CommandSource source, String hex) throws CommandSyntaxException{
		if (hex.length()!=6) {
			throw NOT_ACCURATE_EXCEPTION.create();
		} else {
			int colorcode = 0;
			
			int pt1 = Integer.parseInt(Character.toString(hex.charAt(0))+Character.toString(hex.charAt(1)), 16);
			int pt2 = Integer.parseInt(Character.toString(hex.charAt(2))+Character.toString(hex.charAt(3)), 16);
			int pt3 = Integer.parseInt(Character.toString(hex.charAt(4))+Character.toString(hex.charAt(5)), 16);
			
			colorcode = pt1<<16;
			colorcode += pt2<<8;
			colorcode += pt3;
			source.sendFeedback(new TranslationTextComponent("commands.getcolorcode.hexer").appendSibling(new StringTextComponent(String.valueOf(colorcode))), true);
		}
		return 1;
	}

}
