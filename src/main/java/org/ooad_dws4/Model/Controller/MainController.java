package org.ooad_dws4.Model.Controller;

public class MainController {

    public MainController() {
    }

    public void linkObjects(IOBridge ioBridge) {
        this.ioBridge = ioBridge;
    }

    private IOBridge ioBridge;

    public void inputEvent(int event) {
        // TODO implement here
        System.out.println("inputEvent() has been called from MainController : BUTTON NUMBER = "+event);

//        this.ioBridge.outputEvent(new Message());
    }

    public void defaultScreenTimerReset() {
        // TODO implement here
    }

    public void stopBuzzer() {
        // TODO implement here
    }

}