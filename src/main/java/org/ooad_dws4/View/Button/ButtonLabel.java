package org.ooad_dws4.View.Button;

import javax.swing.*;

public class ButtonLabel extends JLabel {
    ButtonLabel(String text, int x, int y){
        this.setBounds(x,y,100,30);
        this.setText(text);
        this.setVisible(true);
        this.setOpaque(false);
    }
}
