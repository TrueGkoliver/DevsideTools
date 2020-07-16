package com.gkoliver.devsidetools.core.deepeditor;

import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.Frame;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItemEditorFrame extends Frame {
    public ItemStack stack;
    public final Checkbox UNBREAKABLE = new Checkbox("Unbreakable?");
    public final Label DAMAGE_LABEL = new Label("Item Damage:");
    public final IntOnlyField DAMAGE = new IntOnlyField();
    public final Label COUNT_LABEL = new Label("Item Count:");
    public final IntOnlyField COUNT = new IntOnlyField();
    public final Label ID_LABEL = new Label("Namespaced ID:");
    public final TextField NAMESPACED_ID = new TextField();
    Container container;
    int slotId;
    public ItemEditorFrame() {
        this.setup();
    }
    public ItemEditorFrame(ItemStack stackToEdit) {
        this();
        System.out.println("We do be firing though");
        this.setTitle("Item Editor");
        this.stack = stackToEdit;
        setupWithItemStack(stackToEdit);
    }
    public ItemEditorFrame(ItemStack stackToEdit, Container containerIn, int slotId) {
        this(stackToEdit);
        this.container = containerIn;
        this.slotId = slotId;
    }
    public void setupWithItemStack(ItemStack stackIn) {
        System.out.println(stackIn);
        if (stackIn.getTag()!=null) {
            UNBREAKABLE.setState(stackIn.getTag().getBoolean("Unbreakable"));
        }
        COUNT.setText(String.valueOf(stackIn.getCount()));
        ResourceLocation locationIn = stackIn.getItem().getRegistryName();
        NAMESPACED_ID.setText(locationIn.toString());
        DAMAGE.setText(String.valueOf(stackIn.getDamage()));
        this.setVisible(true);
    }
    public void serializeStack() {
        boolean unbreakable = UNBREAKABLE.getState();
        int count = COUNT.getValue();
        ResourceLocation id = new ResourceLocation(NAMESPACED_ID.getText());
        int damage = DAMAGE.getValue();
        if (id==stack.getItem().getRegistryName()) {
            this.stack.setCount(count);
            this.stack.setDamage(damage);
            CompoundNBT editable = stack.getTag();
            editable.putBoolean("Unbreakable", unbreakable);
            stack.setTag(editable);
        } else {
            ItemStack stackToReplace = new ItemStack(ForgeRegistries.ITEMS.getValue(id));
            this.stack.setCount(count);
            this.stack.setDamage(damage);
            CompoundNBT editable = stack.getTag();
            editable.putBoolean("Unbreakable", unbreakable);
            stackToReplace.setTag(editable);
            System.out.println(stackToReplace);
            System.out.println(slotId);
            container.inventorySlots.get(slotId).putStack(stackToReplace);

        }





    }
    public void setup() {
        this.setLayout(new FlowLayout());
        this.add(UNBREAKABLE);
        this.add(COUNT_LABEL);
        this.add(COUNT);
        this.add(DAMAGE_LABEL);
        this.add(DAMAGE);
        this.add(ID_LABEL);
        NAMESPACED_ID.setSize(250, 36);
        this.add(NAMESPACED_ID);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serializeStack();
                dispose();
            }
        });
    }

}
