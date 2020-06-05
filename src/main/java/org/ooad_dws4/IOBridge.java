package org.ooad_dws4;

import java.util.HashMap;

public class IOBridge extends DWSObject {

    private MainController mainController;
    private OutputController output;
    private boolean isBuzzerRinging;
    private boolean isMute;

    public IOBridge() {
        isBuzzerRinging = false;
        isMute = false;
    }

    public void linkObject(MainController mainController, OutputController outputController) {
        this.mainController = mainController;
        this.output = outputController;
    }

    public void outputEvent(Message msg) {
        int dest = msg.getDestination();
        String action = msg.getAction();
        switch (dest) {
            case 10: {
                if (action.equals("toggleMute")) {
                    this.toggleSound();
                    this.output.output(new Message(11, "updateView", new HashMap<String, String>() {
                        {
                            put("2", String.valueOf(isMute));
                        }
                    }));
                }
            }
                break;
            case 11: {
                if (action.equals("buzzOff") || action.equals("buzzRinging"))
                    this.isBuzzerRinging = !this.isBuzzerRinging;
                this.output.output(msg);
            }
                break;
        }
    }

    public void inputEvent(int event) {
        if (this.isBuzzerRinging)
            mainController.stopBuzzer();
        else
            mainController.inputEvent(event);
        if (!this.isMute)
            this.output.output(new Message(11, "beep", null)); // beep
    }

    public void toggleSound() {
        this.isMute = !this.isMute;
        // System.out.println(isMute);
    }

    public boolean isMute() {
        return isMute;
    }

    public boolean isBuzzerRinging() {
        return isBuzzerRinging;
    }
}