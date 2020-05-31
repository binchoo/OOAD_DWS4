package org.ooad_dws4;


/**
 * 
 */
public class ModeManager {

    /**
     * Default constructor
     */
    public ModeManager() {
    }

    private MainController mainController;

    /**
     * 
     */
    private Mode[] modes;

    /**
     * 
     */
    private Mode currentMode;

    /**
     * 
     */
    private Mode defaultMode;

    public void linkObjects(MainController mainController){
        this.mainController = mainController;
    }


    /**
     * @param systemTime
     */
    public void broadcast(long systemTime) {
        // TODO implement here
    }

    /**
     * @param event 
     * @return
     */
    public Message modeModify(int event) {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void changeMode() {
        // TODO implement here
    }

    /**
     * 
     */
    public void enterModeActivationEdit() {
        // TODO implement here
    }

    /**
     * 
     */
    public void changeActivationEditTarget() {
        // TODO implement here
    }

    /**
     * 
     */
    public void changeModeIndex() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showDefaultScreen() {
        // TODO implement here
    }

    /**
     * 
     */
    public void editModeActivation() {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean saveActivation() {
        // TODO implement here
        return true;
    }

}