package org.ooad_dws4.View.Buzzer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

public class BuzzerSound implements Runnable {
    private Clip clip;
    //    private InputStream soundFile;
    private boolean stop;
    private boolean ringing;
    private Thread thread;

    public BuzzerSound() {
        this.stop = true;
        this.ringing = false;
    }

    public void beep() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/sound/beep.wav")));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            audioIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ring() {
        if (ringing) return;
        ringing = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.stop = true;
    }

    public void run() {
        long start = System.currentTimeMillis();
        stop = false;
//        while(!stop && (count < 300)){ // ring during 60 seconds
        while (!stop) {
            long current = System.currentTimeMillis();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (current - start > 500) {
                start += 500;
                try {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/sound/beep.wav")));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    audioIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ringing = false;
    }

}

