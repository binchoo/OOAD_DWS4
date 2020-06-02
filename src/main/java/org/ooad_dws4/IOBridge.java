package org.ooad_dws4;

/**
 *
 */
public class IOBridge {

    /**
     * Default constructor
     */
    public IOBridge() {
        isBuzzerRinging = false;
        isMute = false;
    }

    private MainController mainController;

    /**
     *
     */
    private OutputController output;

    /**
     *
     */
    private InputController input;

    /**
     *
     */
    private boolean isBuzzerRinging;

    /**
     *
     */
    private boolean isMute;

    public void linkObject(MainController mainController, InputController inputController, OutputController outputController) {
        this.mainController = mainController;
        this.input = inputController;
        this.output = outputController;
    }

    /**
     * @param msg
     */
    public void outputEvent(Message msg) {
        System.out.print("IOBridge >> ");
        msg.doMessageAction();
    }

    /**
     * @param event
     */
    public void inputEvent(int event) {
        // TODO implement here
    }

    /**
     *
     */
    public void toggleSound() {
        // TODO implement here
    }

}