package org.ooad_dws4.View.LCD;

import javax.swing.*;
import java.awt.*;

public abstract class LCDComponent extends JLabel {
    LCDComponent(){
        this.setForeground(new Color(152,152,152));
        this.setVisible(true);
        this.setOpaque(false);
    }

    public boolean blink(){

        return true;
    }

    public boolean toggle(){

        return true;
    }
}
