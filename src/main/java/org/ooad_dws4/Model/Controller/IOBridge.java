package org.ooad_dws4.Model.Controller;

import org.ooad_dws4.Model.Common.DWSObject;
import org.ooad_dws4.Model.Common.Message;
import org.ooad_dws4.Model.Input.InputController;
import org.ooad_dws4.Model.Output.OutputController;

public class IOBridge extends DWSObject {

    public IOBridge() {
        isBuzzerRinging = false;
        isMute = false;
    }

    private MainController mainController;
    private OutputController output;
    private InputController input;
    private boolean isBuzzerRinging;
    private boolean isMute;

    public void linkObject(MainController mainController, OutputController outputController){
        this.mainController = mainController;
        this.output = outputController;
    }

    public void outputEvent(Message msg ) {
        // TODO implement here
        this.output.output(msg);
    }

    public void inputEvent(int event) {
        // TODO implement here
        mainController.inputEvent(event);
    }

    public void toggleSound() {
        // TODO implement here
    }

}