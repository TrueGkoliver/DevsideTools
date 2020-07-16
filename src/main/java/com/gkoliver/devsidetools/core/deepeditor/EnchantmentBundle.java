package com.gkoliver.devsidetools.core.deepeditor;

import net.minecraft.enchantment.Enchantment;

import java.awt.*;

public class EnchantmentBundle {
    Enchantment enchant;
    int amplifier;
    public EnchantmentBundle(Enchantment enchantIn, int amplifierIn) {
        this.enchant = enchantIn;
        this.amplifier = amplifierIn;
    }
    public String getBundleName() {
        return enchant.getDisplayName(amplifier).getString();
    }
    public Container getContainer() {
        Container toReturn = new Container();
        Label enchant_label = new Label("Namespaced Enchant ID:");



        return toReturn;
    }
}
