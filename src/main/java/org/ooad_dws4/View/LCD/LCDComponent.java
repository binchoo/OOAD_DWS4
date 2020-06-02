package org.ooad_dws4.View.LCD;

import javax.swing.*;
import java.awt.*;

public abstract class LCDComponent extends JLabel {
    private LCDBlink lcdBlink;
    LCDComponent(){
        this.setForeground(new Color(152,152,152));
        this.setVisible(true);
        this.setOpaque(false);
        this.lcdBlink = new LCDBlink(this);
    }

    public void startBlink(){
        this.lcdBlink.run();
    }

    public void stopBlink(){
        this.lcdBlink.stop();
    }

    public void blink() {
        if (this.isVisible()) this.setVisible(false);
        else this.setVisible(true);
    }

    public void toggle(){

    }
}
