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
    public void onSystemReady() {
        gotoModeChangeMode();
    }


    private void gotoModeChangeMode() {
        system.click(Button.LNG_ADJUST);
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
            tryModeSave(); // 모드 변경을 저장한다

            String currentModeString = system.getText(LCDPart.BOTTOM);
            for(int i = 0; i < 4; i++)
                system.click(Button.MODE);
            assertEquals(currentModeString, system.getText(LCDPart.BOTTOM));

            gotoModeChangeMode();
            testWorkerSleep(10);
        }
    }

    @Test
    void allModeCombination_timekeepingAlwaysOn() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 4);
        for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {

            modeSetActivation(ModeStatus.OFF, modeSet); //모든 모드를 끄려고 시도한다. 작업이 끝나면 TIMEKEEPING 보여지고 있다.

            String onoff = system.getText(LCDPart.TOP_LEFT); //TIMEKEEPING 모드의 ON/OFF 문자열을 본다
            assertEquals(onoff, ModeStatus.ON.toString()); //ON인지 체크한다

            testWorkerSleep(10);
        }
    }

    @Test
    void allModeCombination_lessFourModeSelected_notSavable() {
        for(int r = 1; r < 4; r++) {
            assertTrue(isModeChangeMode());

            ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, r);
            for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {
                modeSetActivation(ModeStatus.OFF, null); //전체 모드를 끈다
                modeSetActivation(ModeStatus.ON, modeSet); //모드 조합에 대하여 다시 켠다

                tryModeSave(); // 저장을 시도한다

                assertTrue(isModeChangeMode()); // 아직도 "MODE" 가 전시되어야 한다.
                testWorkerSleep(10);
            }
        }
    }

    @Test
    void allModeCombination_fourModeSelected_savable() {
        ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, 4);
        for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {
            assertTrue(isModeChangeMode());

            modeSetActivation(ModeStatus.OFF, null);
            modeSetActivation(ModeStatus.ON, modeSet);

            tryModeSave(); // 저장을 시도한다

            assertFalse(isModeChangeMode()); //모드변경 모드를 탈출하여 중앙 문자가 "MODE"가 아니어야 한다
            gotoModeChangeMode(); //다음 테스트를 위해 모드변경 모드로 되돌아온다
            testWorkerSleep(10);
        }
    }

    @Test
    void allModeCombination_moreModeSelected_notAcceptable() {
        for(int r = 5; r <= 6; r++) {
            assertTrue(isModeChangeMode());

            ModeCombinationGenerator mcg = new ModeCombinationGenerator(6, r);
            for (ArrayList<Mode> modeSet : mcg.getModeCombination()) {
                modeSetActivation(ModeStatus.OFF, null); //전체 모드를 끈다
                modeSetActivation(ModeStatus.ON, modeSet); //모드 조합에 대하여 다시 켠다

                int onCount = 0;
                for (int i = 0; i < 6; i++) { //ON인 모드를 센다
                    String onoff = system.getText(LCDPart.TOP_LEFT);
                    if (onoff.equals(ModeStatus.ON))
                        onCount++;
                }

                assertNotEquals(onCount, r);
                testWorkerSleep(10);
            }
        }
    }

    private void modeSetActivation(ModeStatus onoff, ArrayList<Mode> modeSet) {
        Mode mode = Mode.TIME_KEEPING;
        String modeStatusString;

        do {
            modeStatusString = system.getText(LCDPart.TOP_LEFT);

            if (modeStatusString.equals(onoff.opposite().toString())
                    && (modeSet == null || modeSet.contains(mode))) {
                system.click(Button.ADJUST);
            }
            system.click(Button.FORWARD);

        } while ((mode = mode.next()) != Mode.TIME_KEEPING);

        assertEquals(mode, Mode.TIME_KEEPING);
    }

    private boolean isModeChangeMode() {
        String centerString = system.getText(LCDPart.CLOCK);
        return centerString.contains("MODE");
    }

    private void tryModeSave() {
        system.click(Button.MODE);
    }
}

