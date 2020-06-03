package org.ooad_dws4;

public class MainController {
    private IOBridge ioBridge;

    public MainController() {
    }

    public void linkObjects(IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    public void inputEvent(int event) {
//        System.out.println("inputEvent() has been called from MainController : BUTTON NUMBER = "+event);
    }

    public void defaultScreenTimerReset() {
    }

    public void stopBuzzer() {
    }

}