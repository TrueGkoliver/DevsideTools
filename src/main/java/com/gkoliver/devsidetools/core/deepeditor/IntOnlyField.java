package com.gkoliver.devsidetools.core.deepeditor;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class IntOnlyField extends TextField {
    public int value = 0;
    public IntOnlyField() {
        super();
        this.addTextListener((event)->{
            try {
                int i = Integer.valueOf(this.getText());
                value = i;
            }
            catch (NumberFormatException e) {
                this.setText("0");
            }
        });
    }
    public IntOnlyField(String label) {
        super(label);
        this.addTextListener((event)->{
            try {
                int i = Integer.valueOf(this.getText());
                value = i;
            }
            catch (NumberFormatException e) {
                this.setText("0");
                value = 0;
            }
        });
    }

    public int getValue() {
        return value;
    }
}
