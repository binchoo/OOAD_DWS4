package org.ooad_dws4.View.Buzzer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class BuzzerSound {
    private Clip clip;
    private File soundFile;
    private boolean stop;
    public BuzzerSound() {
        this.stop = false;
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
        for (int i=0; i<60; i++){
            if(stop) {
                stop = false;
                break;
            }
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.stop = true;
    }
}
