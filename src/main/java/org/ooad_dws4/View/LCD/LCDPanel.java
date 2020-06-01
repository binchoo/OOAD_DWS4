package org.ooad_dws4.View.LCD;

import javax.swing.*;
import java.awt.*;

public class LCDPanel extends JPanel{
    private Image img;
    private int imageWidth;
    private int imageHeight;
    private LCDComponent[] lcdComp = new LCDComponent[28];
//    private char LCDCharacter[] = {
//            'M', 'O', 'N', '3', '+', '0', '2', '5', '3',
//            ' ', ' ', 'D', '-', 'D', 'A', 'Y', ' ', '2',
//            '2', '0', '2', '0', '-', '0', '5', '-', '0', '4'
//    };
    private char LCDCharacter[] = {
       'M', 'O', 'N', '3', '+', '0', '2', '5', '3', // 0~8
       ' ', ' ', '0', '0', '|', '0', '0', '4', '2', // 9~17
       '2', '0', '2', '0', '-', '0', '5', '-', '0', '4' // 18~27
    };
    private int LCDProperty[][] = {
    // { size, x, y }
        { 2, 120, 110 }, // 0
        { 2, 135, 110 }, // 1
        { 2, 150, 110 }, // 2
        { 1, 182, 104 }, // 3
        { 1, 182, 116 }, // 4
        { 2, 205, 110 }, // 5
        { 2, 220, 110 }, // 6
        { 2, 235, 110 }, // 7
        { 2, 250, 110 }, // 8
        { 20, 95, 187 }, // 9  : mute icon
        { 17, 94, 210 }, // 10 : bell icon
        { 3, 115, 160 }, // 11
        { 3, 142, 160 }, // 12
        { 3, 169, 160 }, // 13
        { 3, 196, 160 }, // 14
        { 3, 223, 160 }, // 15
        { 2, 255, 170 }, // 16
        { 2, 270, 170 }, // 17
        { 2, 115, 215 }, // 18
        { 2, 130, 215 }, // 19
        { 2, 145, 215 }, // 20
        { 2, 160, 215 }, // 21
        { 2, 175, 215 }, // 22
        { 2, 190, 215 }, // 23
        { 2, 205, 215 }, // 24
        { 2, 220, 215 }, // 25
        { 2, 235, 215 }, // 26
        { 2, 250, 215 }, // 27
    };

    public LCDPanel(){
        img = new ImageIcon("./img/emptyLCD(full).png").getImage();
        imageWidth = 500;
        imageHeight = 540;
        this.setBounds(50,100,imageWidth,imageHeight);
        this.setVisible(true);
        this.setOpaque(false);

        int offsetX = 57, offsetY = 48;
        for (int i=0; i<28; i++){
            if(i==9){
                this.lcdComp[i] = new LCDIcon(new ImageIcon("./img/mute.png").getImage(),
                        LCDProperty[i][0],LCDProperty[i][1]+offsetX,LCDProperty[i][2]+offsetY);
            }
            else if(i==10){
                this.lcdComp[i] = new LCDIcon(new ImageIcon("./img/bell.png").getImage(),
                        LCDProperty[i][0],LCDProperty[i][1]+offsetX,LCDProperty[i][2]+offsetY);
            }
            else{
                this.lcdComp[i] = new LCDText(LCDCharacter[i], LCDProperty[i][0],LCDProperty[i][1]+offsetX,LCDProperty[i][2]+offsetY);
            }
            this.add(lcdComp[i]);
        }
        this.lcdComp[13].setHorizontalAlignment(SwingConstants.CENTER); // '|' middle alignment
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, imageWidth, imageHeight, this);
    }
}
