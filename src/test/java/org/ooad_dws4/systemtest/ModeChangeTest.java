package org.ooad_dws4.systemtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ooad_dws4.systemtest.SystemMocker.Mode;
import org.ooad_dws4.systemtest.SystemMocker.ModeStatus;
import org.ooad_dws4.systemtest.SystemMocker.Button;
import org.ooad_dws4.systemtest.SystemMocker.LCDPart;

import java.util.ArrayList;

public class ModeChangeTest extends SystemTest {

    @Override
    void onSystemReady() {
        gotoModeChangeMode();
    }

    private void gotoModeChangeMode() {
        system.click(Button.LNG_ADJUST);
    }

    private void modeSetActivation(ModeStatus onoff, ArrayList<Mode> modeSet) {
        Mode mode = Mode.TIME_KEEPING;
        do {
            String modeStatusString = system.getText(LCDPart.TOP_LEFT);

            if (modeStatusString.equals(onoff.opposite().toString())
                    && (modeSet == null || modeSet.contains(mode)))
                system.click(Button.ADJUST);

            system.click(Button.FORWARD);
            mode = mode.next();

        } while (mode != Mode.TIME_KEEPING);

        assertEquals(mode, Mode.TIME_KEEPING);
    }

    @Test
    void viewReferenceOK() {
        assertNotNull(system.view);
    }

    @Test
    void allModeCombination_generatorWorksProperly() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 4);
        int modeCombSize = mcg.getModeCombination().size();

        CombinationGenerator cg = new CombinationGenerator(5, 3);
        int normalCombSize = cg.getCombination().size();

        assertEquals(modeCombSize, normalCombSize);
    }

    @Test
    void allModeCombination_printModeCombination() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 4);
        for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {
            for(Mode mode : modeSet) {
                System.out.print(mode + ", ");
            }
            System.out.println();
        }
    }

    @Test
    void allModeCombination_modeChangeable() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 4);
        for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {

            modeSetActivation(ModeStatus.OFF, null);
            modeSetActivation(ModeStatus.ON, modeSet);
            system.click(Button.MODE);

            String currentModeString = system.getText(LCDPart.BOTTOM);
            for(int i = 0; i < 4; i++)
                system.click(Button.MODE);
            assertEquals(currentModeString, system.getText(LCDPart.BOTTOM));

            gotoModeChangeMode();
            sleep(100);
        }
    }
}

