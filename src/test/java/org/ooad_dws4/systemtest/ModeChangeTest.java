package org.ooad_dws4.systemtest;

import org.junit.jupiter.api.Test;
import org.ooad_dws4.View.DWSFrame;
import static org.junit.jupiter.api.Assertions.*;

public class ModeChangeTest {

    ViewMocker viewMocker;
    ModeChangeTest() {
        viewMocker = new ViewMocker();
        viewMocker.waitViewReady();
    }

    @Test
    void viewReferenceOK() {
        assertNotNull(viewMocker.view);
    }

    @Test
    void gotoModeChangeMode() {
        viewMocker.buttonClick(ViewMocker.BUTTON.LNG_ADJUST);
        sleepCompat(5000);
    }

    void offAllMode() {
        gotoModeChangeMode();
    }

    void sleepCompat(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }
}