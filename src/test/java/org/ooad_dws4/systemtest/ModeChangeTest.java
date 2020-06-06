package org.ooad_dws4.systemtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ooad_dws4.systemtest.SystemMocker.MODE;
import org.ooad_dws4.systemtest.SystemMocker.STRING;
import org.ooad_dws4.systemtest.SystemMocker.BUTTON;
import org.ooad_dws4.systemtest.SystemMocker.LCDPART;

import java.util.ArrayList;

public class ModeChangeTest extends SystemTest {

    @Override
    void onSystemReady() {
        gotoModeChangeMode();
        offAllMode();
        sleepCompat(2000);
    }

    private void gotoModeChangeMode() {
        system.click(BUTTON.LNG_ADJUST);
    }

    private void offAllMode() {
        MODE mode = MODE.TIME_KEEPING;

        do {
            String activation = system.getText(LCDPART.TOP_LEFT);

            if (activation.equals(STRING.ON.getValue()))
                system.click(BUTTON.ADJUST);
            system.click(BUTTON.FORWARD);
            mode = mode.next();
        } while (mode != MODE.TIME_KEEPING);

        assertEquals(mode, MODE.TIME_KEEPING);
    }

    @Test
    void viewReferenceOK() {
        assertNotNull(system.view);
    }

    @Test
    void allModeCombination_generatorWorksProperly() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 3);
        int normalCombSize = mcg.getModeCombination().size();

        CombinationGenerator cg = new CombinationGenerator(5, 2);
        int modeCombSize = cg.getCombination().size();

        assertEquals(modeCombSize, normalCombSize);
    }

    @Test
    void allModeCombination_printModeCombination() {
        ModeCombinationGenerator cg = new ModeCombinationGenerator(6, 4);
        for (ArrayList<MODE> modeSet : cg.getModeCombination()) {
            for(MODE mode : modeSet) {
                System.out.print(mode + ", ");
            }
            System.out.println();
        }
    }
}

