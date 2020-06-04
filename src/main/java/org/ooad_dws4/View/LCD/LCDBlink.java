package org.ooad_dws4.View.LCD;

public class LCDBlink implements Runnable{
    private LCDComponent lcdComponent;
    private boolean stop;
    private Thread thread;

    LCDBlink(LCDComponent lcdComponent){
        this.lcdComponent = lcdComponent;
        this.stop = true;
//        System.out.println("blink"+this.lcdComponent.lcdNum+" created");
    }
    public void run() {
        long start = System.currentTimeMillis();
//        System.out.println("blink"+this.lcdComponent.lcdNum+" run");
        stop = false;
        while(!stop){
            long current = System.currentTimeMillis();
            if(current-start>500){
                start+=500;
//                System.out.println(this.lcdComponent.lcdNum);
                this.lcdComponent.blink();
            }
        }
        stop = false;
//        System.out.println("blink"+this.lcdComponent.lcdNum+" stopped");
    }
    public void blink(){
        this.thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.stop = true;
        this.lcdComponent.setVisible(true);
    }
}
