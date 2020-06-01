package org.ooad_dws4.Model.Input;

import org.ooad_dws4.Model.Controller.IOBridge;

public class InputController implements Input {

    private IOBridge ioBridge;

    public InputController() {
    }

    public void linkObject(IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    @Override
    public void buttonClick(int button) {
        ioBridge.inputEvent(button);
    }
}