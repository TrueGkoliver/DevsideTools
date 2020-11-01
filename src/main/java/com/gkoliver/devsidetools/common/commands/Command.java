package com.gkoliver.devsidetools.common.commands;

import com.gkoliver.devsidetools.DevsideTools;

public abstract class Command implements ICommand {
    public Command() {
        DevsideTools.commands.add(this); }
}
