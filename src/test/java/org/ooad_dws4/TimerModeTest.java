package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerModeTest {


    @Test
    void makeTimeForm() {
        TimerMode timerMode = new TimerMode(true);
        System.out.println(timerMode.makeTimeForm(2,15,52));

    }

    @Test
    void makeView() {
        TimerMode timerMode = new TimerMode(true);
        timerMode.makeView().doMessageAction();
    }
}