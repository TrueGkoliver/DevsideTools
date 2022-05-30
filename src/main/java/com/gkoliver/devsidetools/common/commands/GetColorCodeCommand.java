package com.gkoliver.devsidetools.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;


public class GetColorCodeCommand extends Command {
	private static final SimpleCommandExceptionType NOT_ACCURATE_EXCEPTION = new SimpleCommandExceptionType(new TranslatableComponent("commands.getcolorcode.notaccurate"));
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("getcolorcode").requires(
			(cmd)->{
				return cmd.hasPermission(2);
			}).then(Commands.argument("hex", StringArgumentType.string()).executes((cmd)->{
				return getColorCode(cmd.getSource(), StringArgumentType.getString(cmd, "hex"));
			}))
				
		);
	}
	public static int getColorCode(CommandSourceStack source, String hex) throws CommandSyntaxException{
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
			final String colorCode = String.valueOf(colorcode);
			source.sendSuccess(new TranslatableComponent("commands.getcolorcode.hexer").append(new TextComponent(hex)).append(new TranslatableComponent("commands.getid.is")).append(new TextComponent(String.valueOf(colorcode)).withStyle((style)->{
				return style
						.withColor(ChatFormatting.GREEN)
						.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))
						.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, colorCode)
						
								
				);
			})), true);
		}
		return 1;
	}

}
