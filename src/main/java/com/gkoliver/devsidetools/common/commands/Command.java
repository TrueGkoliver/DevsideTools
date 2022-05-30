package com.gkoliver.devsidetools.common.commands;

import com.gkoliver.devsidetools.DevsideTools;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public abstract class Command {
    public Command() {
        DevsideTools.commands.add(this);
    }
    public abstract void register(CommandDispatcher<CommandSourceStack> dispatcher);
    public static String stripCommandContent(String commandName, String cmd) {
        String realCmd = cmd.substring(commandName.length()-1, cmd.length()-1);
        return realCmd;
    }
    public static String stripCommand(String cmd_stripped, int argsToRemove) {
        String[] args = cmd_stripped.split(" ", argsToRemove+1);
        System.out.println(args);
        String tbr = args[args.length-1];
        StringBuilder builder = new StringBuilder(tbr);
        builder.deleteCharAt(0);
        boolean flag = false;
        while (!flag) {
            if (builder.charAt(0)==' ') {
                builder.deleteCharAt(0);
            } else {
                flag = true;
            }
        }
        return builder.toString();
    }
}
