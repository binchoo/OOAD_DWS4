package org.ooad_dws4;

import java.util.HashMap;

public class IOBridge {

    private static MainController mainController;
    private static OutputController output;
    private static boolean isRinging;
    private static boolean mute;

    public IOBridge() {
        isRinging = false;
        mute = false;
    }

    public void linkObject(MainController mainController, OutputController outputController) {
        this.mainController = mainController;
        this.output = outputController;
    }

    public void outputEvent(Message msg) {
        final int dest = msg.getDestination();
        final String action = msg.getAction();
        switch (dest) {
            case 10: {
                if ("toggleMute".equals(action)) {
                    this.toggleSound();
                    this.output.output(new Message(11, "updateView", new HashMap<String, String>() {
                        {
                            put("2", String.valueOf(mute));
                        }
                    }));
                }
            }
                break;
            case 11: {
                if ("buzzOff".equals(action)){
                    isRinging = false;
                }
                else if("buzzRinging".equals(action)){
                    this.isRinging = true;
                }
                this.output.output(msg);
            }
                break;
            default:
        }

    }

    public void inputEvent(int event) {
        if (this.isRinging)
            mainController.stopBuzzer();
        else
            mainController.inputEvent(event);
        if (!this.mute)
            this.output.output(new Message(11, "beep", null)); // beep
    }

    public void toggleSound() {
        this.mute = !this.mute;
        // System.out.println(isMute);
    }

    public boolean isMute() {
        return mute;
    }

    public boolean isRinging() {
        return isRinging;
    }
}