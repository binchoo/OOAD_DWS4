package org.ooad_dws4.View;

import org.ooad_dws4.View.Button.ButtonPanel;
import org.ooad_dws4.View.Buzzer.BuzzerSound;
import org.ooad_dws4.View.LCD.LCDPanel;

import javax.swing.*;
import java.awt.*;

public class DWSFrame extends JFrame {
    LCDPanel lcdPanel;
    ButtonPanel btnPanel;
    BuzzerSound buzzer;
    public DWSFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(214, 245, 214));
        this.setTitle("DWS TEAM 4");
        this.setSize(680,800);
        this.setVisible(true);
        this.setLocation(370, 20);
        this.setLayout(null);
        this.setResizable(false);

        this.buzzer = new BuzzerSound();

        this.lcdPanel = new LCDPanel();
        this.add(lcdPanel);

        this.btnPanel = new ButtonPanel();
        this.add(btnPanel);
        this.btnPanel.setParentReference();

        this.repaint();
    }

    public ButtonPanel getBtnPanel() {
        return btnPanel;
    }

    public BuzzerSound getBuzzer() {
        return buzzer;
    }

    public LCDPanel getLcdPanel() {
        return lcdPanel;
    }
}
