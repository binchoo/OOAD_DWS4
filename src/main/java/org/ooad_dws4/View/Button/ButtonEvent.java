package org.ooad_dws4.View.Button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonEvent extends MouseAdapter implements Runnable {

    private int btnNum;
    private boolean shortReleased;
    private boolean longReleased;
    private Thread thread;
    private long elapsedTime;
    private ButtonPanel buttonPanel;

    public ButtonEvent(int btnNum) {
        this.shortReleased = false;
        this.longReleased = false;
        this.btnNum = btnNum;
    }

    public void setButtonPanel(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;
    }

    public void run() {
        long start = System.currentTimeMillis();
        while(true){
            if(shortReleased){
                shortReleased = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long current = System.currentTimeMillis();
            elapsedTime = current-start;
            if(elapsedTime>1000){
                longReleased = true;
//                System.out.println("button "+this.button.getName()+" pressed (long) : "+(this.btnNum+5));
                this.buttonPanel.buttonClick(this.btnNum+5);
                return;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        elapsedTime = 0;
        thread = new Thread(this);
        thread.start();
    }

    public void mouseReleased(MouseEvent e) {
        shortReleased = true;
        if(longReleased){
            shortReleased = false;
            longReleased = false;
            return;
        }
//        System.out.println("button "+this.button.getName()+" pressed (short) : "+(this.btnNum+1));
        this.buttonPanel.buttonClick(this.btnNum+1);
    }
}