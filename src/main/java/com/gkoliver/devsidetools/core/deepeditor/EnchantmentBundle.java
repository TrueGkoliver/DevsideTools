package com.gkoliver.devsidetools.core.deepeditor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class EnchantmentBundle {
    Enchantment enchant;
    int amplifier;

    public EnchantmentBundle(Enchantment enchantIn, int amplifierIn) {
        this.enchant = enchantIn;
        this.amplifier = amplifierIn;
    }

    public void setEnchant(String value) {
        this.enchant = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(value));
        if (enchant==null) {
            throw new NullPointerException();
        }
    }

    public String getBundleName() {
        return enchant.getDisplayName(amplifier).getString();
    }
    public Container getContainer() {
        Container toReturn = new Container();
        Label enchant_label = new Label("Namespaced Enchant ID:");
        TextField enchant_field = new TextField();
        enchant_field.setText(this.enchant.getRegistryName().toString());
        enchant_field.addTextListener(
            (ctx)->{
                try {
                    this.setEnchant(enchant_field.getText());
                } catch (Exception e) {
                    enchant_field.setText("minecraft:placeholder");
                }
            }
        );
        toReturn.add(enchant_label);
        toReturn.add(enchant_field);
        return toReturn;
    }
}
