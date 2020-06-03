package org.ooad_dws4;

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