package org.ooad_dws4.Model.Controller;

import org.ooad_dws4.Model.Common.DWSObject;
import org.ooad_dws4.Model.Common.Message;
import org.ooad_dws4.Model.Input.InputController;
import org.ooad_dws4.Model.Output.OutputController;

public class IOBridge extends DWSObject {

    private MainController mainController;
    private OutputController output;
    private InputController input;
    private boolean isBuzzerRinging;
    private boolean isMute;

    public IOBridge() {
        isBuzzerRinging = false;
        isMute = false;
    }

    public void linkObject(MainController mainController, OutputController outputController){
        this.mainController = mainController;
        this.output = outputController;
    }

    public void outputEvent(Message msg) {
        int dest = msg.getDestination();
        String action = msg.getAction();
        switch (dest){
            case 10:
            {
                if(action.equals("toggleMute")) this.toggleSound();
            }
            break;
            case 11: {
                if (action.equals("buzzOff") || action.equals("buzzRinging")) {
                    this.isBuzzerRinging = !this.isBuzzerRinging;
                }
                this.output.output(msg);
            }
            break;
        }
    }

    public void inputEvent(int event) {
        if(!this.isMute) this.output.output(new Message(11, "beep",null)); // beep
        mainController.inputEvent(event);
    }

    public void toggleSound() {
        this.isMute = !this.isMute;
    }

}