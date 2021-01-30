package org.ooad_dws4;

public class InputController implements Input {

    private static IOBridge ioBridge;

    public void linkObject(final IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    @Override
    public void buttonClick(final int button) {
        ioBridge.inputEvent(button);
    }
}