package org.ooad_dws4.systemtest;

import org.ooad_dws4.View.Button.ButtonPanel;
import org.ooad_dws4.View.Buzzer.BuzzerSound;
import org.ooad_dws4.View.DWSFrame;
import org.ooad_dws4.View.LCD.LCDPanel;

public class SystemMocker {

    public DWSFrame view;
    public ButtonPanel buttonPanel;
    public LCDPanel lcdPanel;
    public BuzzerSound buzzerSound;

    private ApplicationRunner appRunner;
    private Thread systemThread;

    public SystemMocker() {
        appRunner = new ApplicationRunner();
        appRunner.onViewReadyListener = (itsView) -> {
            view = itsView;
            buttonPanel = view.getBtnPanel();
            lcdPanel = view.getLcdPanel();
            buzzerSound = view.getBuzzer();
        };

        systemThread = new Thread(appRunner) {
            @Override
            public synchronized void start() {
                super.start();
                appRunner.waitViewReady();
            }
        };
    }

    public void showUp() {
        systemThread.start();
    }

    public void click(Button button) {
        buttonPanel.buttonClick(button.getCode());
    }

    public String getText(LCDPart lcdPart) {
        if (lcdPart != LCDPart.ICON)
            return lcdPanel.getText(lcdPart.start, lcdPart.last);
        else
            return null;
    }

    public void setText(LCDPart lcdPart, String text) {
        if (lcdPart != LCDPart.ICON) {
            lcdPanel.setText(lcdPart.start, lcdPart.last, text);
        }
    }

    public enum Mode {
        TIME_KEEPING, WORLD_TIME, ALARM, TIMER, STOPWATCH, D_DAY;

        private static Mode[] vals = values();
        public static int count = vals.length;

        Mode next() {
            int nextIndex = (ordinal() + 1) % count;
            return vals[nextIndex];
        }

        static Mode get(int index) {
            return vals[index];
        }
    }

    public enum Button {
        NONE, MODE, ADJUST, REVERSE, FORWARD,
        LNG_MODE, LNG_ADJUST, LNG_REVERSE, LNG_FORWARD;

        int getCode() {
            return ordinal();
        }
    }

    public enum LCDPart {
        TOP_LEFT(0, 2),
        TOP_RIGTH(3, 8),
        ICON(9, 9),
        CLOCK(10, 16),
        BOTTOM(17, 26);

        int start;
        int last;

        LCDPart(int start, int last) {
            this.start = start;
            this.last = last;
        }
    }

    public enum ModeStatus {
        ON(" ON"), OFF("OFF");

        String value;

        ModeStatus(String value) {
            this.value = value;
        }

        public ModeStatus opposite() {
            return this == ON? OFF : ON;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
