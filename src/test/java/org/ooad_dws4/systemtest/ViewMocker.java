package org.ooad_dws4.systemtest;

import org.ooad_dws4.View.Button.ButtonPanel;
import org.ooad_dws4.View.Buzzer.BuzzerSound;
import org.ooad_dws4.View.DWSFrame;
import org.ooad_dws4.View.LCD.LCDPanel;

public class ViewMocker {

    public DWSFrame view;
    public ButtonPanel buttonPanel;
    public LCDPanel lcdPanel;
    public BuzzerSound buzzerSound;
    ApplicationRunner appRunner;

    public ViewMocker() {
        appRunner = new ApplicationRunner();
        appRunner.onViewReadyListener = (itsView) -> {
            view = itsView;
            buttonPanel = view.getBtnPanel();
            lcdPanel = view.getLcdPanel();
            buzzerSound = view.getBuzzer();
        };

        runSystem();
    }

    private void runSystem() {
        new Thread(appRunner).start();
    }

    public void waitViewReady() {
        appRunner.waitViewReady();
    }

    public void buttonClick(BUTTON buttonLabel) {
        int buttonCode = buttonLabel.getCode();
        buttonPanel.buttonClick(buttonCode);
    }

    enum BUTTON {
        NONE, MODE, ADJUST, REVERSE, FORWARD,
        LNG_MODE, LNG_ADJUST, LNG_REVERSE, LNG_FORWARD;

        int getCode() {
            return ordinal();
        }
    }
}
