package org.ooad_dws4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class LCDPanel extends JPanel {
    private Image img;
    private int imageWidth;
    private int imageHeight;
    private LCDComponent[] lcdComp = new LCDComponent[27];


    //    private char LCDCharacter[] = {
//            'T', 'H', 'U', '-', '1', '9', '9', '9', '9', // 0~80
//            ' ', '0', '9', '|', '0', '0', '0', '0', // 9~16
//            '1', '9', '7', '0', '-', '0', '1', '-', '0', '1' // 17~26
//    };
//    // initial view
    private char LCDCharacter[] = {
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', // 0~80
            ' ', 'S', 'T', 'A', 'R', 'T', '.', '.', // 9~16
            'N', 'O', 'W', 'L', 'O', 'A', 'D', 'I', 'N', 'G'// 17~26
    };

//    Use Case 1. Change Mode
//    Use Case 6. Timekeeping                                           blink
//    Use Case 7. Change Date and Time                                  blink
//    Use Case 8. Watch D-Day
//    Use Case 9. Change D-Day Index (on TimeKeeping Mode)
//    Use Case 28. Toggle Sound
//    Use Case 29. Watch Timekeeping
//    Use Case 30. Time Refresh
//    private char LCDCharacter[] = {
//       'M', 'O', 'N',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     '0', '6', '|', '2', '0',    '4', '2', // 9~16
//       '2', '0', '2', '0', '-', '0', '5', '-', '0', '4' // 17~26
//    };

//    Use Case 2. Activate Modes
//    private char LCDCharacter[] = {
//       ' ', 'O', 'N',   ' ', ' ',   ' ', '2', '/', '4', // 0~8
//       ' ',     ' ', 'M', 'O', 'D', 'E',    ' ', ' ', // 9~16
//       ' ', ' ', 'D', '-', 'D', 'A', 'Y', ' ', ' ', ' ' // 17~26
//    };

    //    Use Case 3. Change D-Day Index
//    Use Case 5. Toggle D-Day
//    private char LCDCharacter[] = {
//            ' ', 'O', 'N', '3', '+', ' ', ' ', '6', '5', // 0~8
//            ' ', 'D', '-', 'D', 'A', 'Y', ' ', '3', // 9~16
//            '2', '0', '2', '0', '-', '0', '4', '-', '0', '1' // 17~26
//    };

//    Use Case 4. Set Date of D-Day                                     blink
//    private char LCDCharacter[] = {
//       'E', 'D', 'T',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     'D', '-', 'D', 'A', 'Y',    ' ', '3', // 9~16
//       '2', '0', '2', '0', '-', '0', '4', '-', '0', '1' // 17~26
//    };

//    Use Case 10. Start Timer
//    Use Case 11. Set Timer Time                                       blink
//    Use Case 12. Pause Timer
//    Use Case 13. Resume Timer
//    Use Case 14. Reset Timer
//    private char LCDCharacter[] = {
//       ' ', ' ', ' ',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     '0', '0', '|', '0', '0',    '0', '0', // 9~16
//       ' ', ' ', 'T', 'I', 'M', 'E', 'R', ' ', ' ', ' ' // 17~26
//    };

//    Use Case 15. Start Buzzing
//    Use Case 16. Stop Buzzing
//    Use Case 20. Stop Buzzing
//    Use Case 21. Start Buzzing

//    Use Case 17. Change Alarm Index
//    Use Case 18. Toggle Alarm
//    Use Case 19. Set Alarm Time
//    private char LCDCharacter[] = {
//       'O', 'F', 'F',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     '0', '4', '|', '3', '0',    ' ', '2', // 9~16
//       ' ', ' ', 'A', 'L', 'A', 'R', 'M', ' ', ' ', ' ' // 17~26
//    };

//    Use Case 22. Start Stopwatch
//    Use Case 23. Pause Stopwatch
//    Use Case 24. Reset Stopwatch
//    Use Case 25. Resume Stopwatch
//    private char LCDCharacter[] = {
//       ' ', ' ', ' ',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     '0', '0', '|', '0', '0',    ' ', '0', // 9~16
//       'S', 'T', 'O', 'P', 'W', 'A', 'T', 'C', 'H', ' ' // 17~26
//    };

//    Use Case 26. Change City Index
//    Use Case 27. Choose City
//       'N', 'Y', 'C',   '3', '+',   ' ', '2', '5', '3', // 0~8
//       ' ',     '1', '2', '|', '2', '2',    'T', 'Z', // 9~16
//       'W', 'O', 'R', 'L', 'D', ' ', 'T', 'I', 'M', 'E' // 17~26
//    };

    private int LCDProperty[][] = {
            // { size, x, y }
            {2, 120, 110}, // 0
            {2, 135, 110}, // 1
            {2, 150, 110}, // 2
            {2, 180, 110}, // 3 : + / -
            {1, 260, 120}, // 4 : dday index
            {2, 195, 110}, // 5
            {2, 210, 110}, // 6
            {2, 225, 110}, // 7
            {2, 240, 110}, // 8
            {20, 95, 197}, // 9  : mute icon
            {3, 115, 160}, // 10
            {3, 142, 160}, // 11
            {3, 169, 160}, // 12
            {3, 196, 160}, // 13
            {3, 223, 160}, // 14
            {2, 255, 170}, // 15
            {2, 270, 170}, // 16
            {2, 115, 215}, // 17
            {2, 130, 215}, // 18
            {2, 145, 215}, // 19
            {2, 160, 215}, // 20
            {2, 175, 215}, // 21
            {2, 190, 215}, // 22
            {2, 205, 215}, // 23
            {2, 220, 215}, // 24
            {2, 235, 215}, // 25
            {2, 250, 215}, // 26
    };

    public LCDPanel() {
        try {
            img = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/emptyLCD(full).png"))).getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageWidth = 500;
        imageHeight = 540;
        this.setBounds(50, 100, imageWidth, imageHeight);
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(null);

        int offsetX = 57, offsetY = 48;
        for (int i = 0; i < 27; i++) {
            if (i == 9) {
                try {
                    this.lcdComp[i] = new LCDIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/img/mute.png"))).getImage(),
                            LCDProperty[i][0], LCDProperty[i][1] + offsetX, LCDProperty[i][2] + offsetY, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.lcdComp[i].setVisible(false);
            } else {
                this.lcdComp[i] = new LCDText(LCDCharacter[i], LCDProperty[i][0], LCDProperty[i][1] + offsetX, LCDProperty[i][2] + offsetY, i);
            }
            this.add(lcdComp[i]);
        }
        this.lcdComp[12].setHorizontalAlignment(SwingConstants.CENTER); // '|' middle alignment
    }

    public void stopBlink(int from, int to) {
        for (int i = from; i <= to; i++) {
            lcdComp[i].stopBlink();
        }
    }

    public void startBlink(int from, int to) {
        for (int i = from; i <= to; i++) {
            lcdComp[i].startBlink();
        }
    }

    public void blink(int screenNum){
        stopBlink(10,26);
        switch(screenNum){
            case -1:
                // stop Blink
                break;
            case 0:
                startBlink(17, 20);
                break;
            case 1:
                startBlink(22, 23);
                break;
            case 2:
                startBlink(25, 26);
                break;
            case 3:
                startBlink(10, 11);
                break;
            case 4:
                startBlink(13, 14);
                break;
            case 5:
                startBlink(15, 16);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + screenNum);
        }
    }

    public void changeLCD(int screenNum, String value) {
        switch (screenNum) {
            case 0:
                setText(0, 2, value);
                break;
            case 1:
                setText(3, 8, value);
                break;
            case 2:
//                System.out.println("1");
                lcdComp[9].setVisible(Boolean.valueOf(value));
                break;
            case 3:
                setText(10, 16, value);
                break;
            case 4:
                setText(17, 26, value);
                break;
        }
    }

    public void setText(int startIndex, int finishIndex, String value) {
        for (int i = 0; i <= finishIndex - startIndex; i++) {
            lcdComp[startIndex + i].setText(String.valueOf(value.charAt(i)));
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, imageWidth, imageHeight, this);
    }
}
