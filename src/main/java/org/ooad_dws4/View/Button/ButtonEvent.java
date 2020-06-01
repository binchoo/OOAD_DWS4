package org.ooad_dws4.View.Button;

import org.ooad_dws4.View.Buzzer.BuzzerSound;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonEvent extends MouseAdapter implements Runnable {

    private int btnNum;
    private boolean shortReleased;
    private boolean longReleased;
    private Thread thread;
    private long elapsedTime;
    private JButton button;
    private ButtonPanel parentReference;

    public ButtonEvent(int btnNum) {
        this.btnNum = btnNum;
    }

    public void setParentReference(ButtonPanel buttonPanel){
        this.parentReference = buttonPanel;
    }

    public void run() {
        long start = System.currentTimeMillis();
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(shortReleased){
                shortReleased = false;
                return;
            }
            long current = System.currentTimeMillis();
            elapsedTime = current-start;
            if(elapsedTime>1000){
                longReleased = true;
//                System.out.println("button "+this.button.getName()+" pressed (long) : "+(this.btnNum+5));
                this.parentReference.buttonClick(this.btnNum+5);
                new BuzzerSound().beep();
                return;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        this.button = (JButton)e.getSource();
        elapsedTime = 0;
        thread = new Thread(this);
        thread.start();
    }

    public void mouseReleased(MouseEvent e) {
        shortReleased = true;
        if(longReleased){
            longReleased = false;
            return;
        }
//        System.out.println("button "+this.button.getName()+" pressed (short) : "+(this.btnNum+1));
        this.parentReference.buttonClick(this.btnNum+1);
        new BuzzerSound().beep();
    }
}