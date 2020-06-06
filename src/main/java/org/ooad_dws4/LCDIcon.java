package org.ooad_dws4;

import java.awt.*;

public class LCDIcon extends LCDComponent{
    private Image img;
    LCDIcon(Image img, int size, int x, int y, int lcdNum){
        super(lcdNum);
        this.img = img;
        this.setBounds(0,0, size, size);
        this.setLocation(x,y);
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 20, 20, this);
    }
}
