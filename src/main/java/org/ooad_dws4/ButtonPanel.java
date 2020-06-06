package org.ooad_dws4;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private ButtonsetAdapter buttonsetAdapter;
    private Image img;
    private int imageSize;
    private ButtonComponent[] btnComp = new ButtonComponent[4];
    private ButtonLabel[] btnLabel = new ButtonLabel[4];
    private String btnName[] = { "MODE", "ADJUST", "REVERSE", "FORWARD"};

    private int btnProperty[][] = {
            // { x, y }
            { 100, 500 }, // MODE : 1
            { 100, 200 }, // ADJUST : 2
            { 460, 200 }, // REVERSE : 3
            { 460, 500 }, // FORWARD : 4
    };

    public ButtonPanel(){
        this.buttonsetAdapter = new ButtonsetAdapter();

        img = new ImageIcon("./img/button.png").getImage();
        imageSize = 50;
        this.setBounds(0,0,680,800);
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(null);

        for (int i=0; i<4; i++){
            this.btnComp[i] = new ButtonComponent(i, btnName[i], img, imageSize, btnProperty[i][0], btnProperty[i][1]);
            this.add(btnComp[i]);
            this.btnLabel[i] = new ButtonLabel(btnName[i], btnProperty[i][0]-25, btnProperty[i][1]+45);
            this.btnLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(btnLabel[i]);
        }
    }

    public void buttonClick(int button) {
        buttonsetAdapter.buttonClick(button);
    }

    public ButtonsetAdapter getButtonsetAdapter() {
        return buttonsetAdapter;
    }

    public void setParentReference() {
        for(int i=0; i<4; i++){
            this.btnComp[i].setParentReference(this);
        }
    }
}
