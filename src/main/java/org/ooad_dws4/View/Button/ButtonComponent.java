package org.ooad_dws4.View.Button;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    private int btnNum;
    private Image img;
    int size;
    private ButtonPanel buttonPanel;
    private ButtonEvent buttonEvent;

    ButtonComponent(int btnNum, String name, Image img, int size, int x, int y){
        this.btnNum = btnNum;
        this.size = size;
        this.img = img;
        this.setName(name);
        this.setBounds(x,y,size,size);
        this.setOpaque(true);
        this.setVisible(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
            buttonEvent = new ButtonEvent(btnNum);
            this.addMouseListener(buttonEvent);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, size, size, this);
    }
    
    public void setParentReference(ButtonPanel buttonPanel){
        for(int i=0; i<4; i++){
            this.buttonEvent.setButtonPanel(buttonPanel);
        }
    }
}
