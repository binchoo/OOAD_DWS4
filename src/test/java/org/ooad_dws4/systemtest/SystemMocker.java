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
    ApplicationRunner appRunner;

    public SystemMocker() {
        appRunner = new ApplicationRunner();
        appRunner.onViewReadyListener = (itsView) -> {
            view = itsView;
            buttonPanel = view.getBtnPanel();
            lcdPanel = view.getLcdPanel();
            buzzerSound = view.getBuzzer();
        };
    }

    public void showUp() {
        new Thread(appRunner).start();
        waitViewReady();
    }

    private void waitViewReady() {
        appRunner.waitViewReady();
    }

    public void click(BUTTON buttonLabel) {
        int buttonCode = buttonLabel.getCode();
        buttonPanel.buttonClick(buttonCode);
    }

    public String getText(LCDPART lcdPart) {
        if (lcdPart != LCDPART.ICON)
            return lcdPanel.getText(lcdPart.start, lcdPart.last);
        else
            return null;
    }

    public void setText(LCDPART lcdPart, String text) {
        if (lcdPart != LCDPART.ICON) {
            lcdPanel.setText(lcdPart.start, lcdPart.last, text);
        }
    }

    enum MODE {
        TIME_KEEPING, WORLD_TIME, ALARM, TIMER, STOPWATCH, D_DAY;

        private static MODE[] vals = values();
        public static int count = vals.length;

        MODE next() {
            int nextIndex = (ordinal() + 1) % count;
            return vals[nextIndex];
        }

        static MODE get(int index) {
            return vals[index];
        }
    }

    enum BUTTON {
        NONE, MODE, ADJUST, REVERSE, FORWARD,
        LNG_MODE, LNG_ADJUST, LNG_REVERSE, LNG_FORWARD;

        int getCode() {
            return ordinal();
        }
    }

    enum LCDPART {
        TOP_LEFT(0, 2),
        TOP_RIGTH(3, 8),
        ICON(9, 9),
        CLOCK(10, 16),
        BOTTOM(17, 26);

        int start;
        int last;

        LCDPART(int start, int last) {
            this.start = start;
            this.last = last;
        }
    }

    enum STRING {
        ON(" ON"), OFF("OFF");

        String value;

        STRING(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
