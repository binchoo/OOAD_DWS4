package org.ooad_dws4.View.LCD;

public class LCDBlink implements Runnable{
    private LCDComponent lcdComponent;
    private boolean stop;

    LCDBlink(LCDComponent lcdComponent){
        this.lcdComponent = lcdComponent;
        this.stop = true;
    }
    public void run() {
        stop = false;
        while(true){
            if(stop){
                stop = false;
                break;
            }
            this.lcdComponent.blink();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop(){
        this.stop = true;
    }
}
