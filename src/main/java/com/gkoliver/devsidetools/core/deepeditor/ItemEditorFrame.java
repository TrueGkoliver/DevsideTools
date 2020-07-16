package com.gkoliver.devsidetools.core.deepeditor;

import net.minecraft.item.ItemStack;

import java.awt.*;

public class ItemEditorFrame extends Frame {
    public final Checkbox UNBREAKABLE = new Checkbox("Unbreakable?");
    public final IntOnlyField COUNT = new IntOnlyField("Count: ");
    public final TextField NAMESPACED_ID = new TextField("Item ID: ");

    public ItemEditorFrame() {
        this.setup();
    }
    public ItemEditorFrame(ItemStack stackToEdit) {
        this();
        setupWithItemStack(stackToEdit);
    }
    public void setupWithItemStack(ItemStack stackIn) {

    }
    public void setup() {
        this.add(UNBREAKABLE);
        this.add(COUNT);
    }
}
