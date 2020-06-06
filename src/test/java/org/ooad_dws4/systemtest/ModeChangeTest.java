package org.ooad_dws4.systemtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ooad_dws4.systemtest.SystemMocker.MODE;
import org.ooad_dws4.systemtest.SystemMocker.STRING;
import org.ooad_dws4.systemtest.SystemMocker.BUTTON;
import org.ooad_dws4.systemtest.SystemMocker.LCDPART;

public class ModeChangeTest extends SystemTest {

    @Override
    void onSystemReady() {
        gotoModeChangeMode();
        offAllMode();
        sleepCompat(5000);
    }

    private void gotoModeChangeMode() {
        system.click(BUTTON.LNG_ADJUST);
    }

    private void offAllMode() {
        MODE mode = MODE.TIME_KEEPING;

        while (mode.next() != MODE.TIME_KEEPING) {
            String activation = system.getText(LCDPART.TOP_LEFT);
            if (activation.equals(STRING.ON.getValue())) {
                system.click(BUTTON.ADJUST);
            }
        }
    }

    @Test
    void viewReferenceOK() {
        assertNotNull(system.view);
    }
}