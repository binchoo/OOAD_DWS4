package org.ooad_dws4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BuzzerSound implements Runnable {
    private Clip clip;
    private File soundFile;
    private boolean stop;
    private boolean ringing;
    private Thread thread;

    public BuzzerSound() {
        this.stop = true;
        this.ringing = false;
        soundFile = new File("./sound/beep.wav");
    }

    public void beep(){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ring(){
        if(ringing) return;
        ringing = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        this.stop = true;
    }

    public void run() {
        int count = 0;
        long start = System.currentTimeMillis();
        stop = false;
        while(!stop && (count < 60)){ // ring during 60 seconds
            long current = System.currentTimeMillis();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(current-start>1000){
                start+=1000;
                count++;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ringing = false;
    }

}

