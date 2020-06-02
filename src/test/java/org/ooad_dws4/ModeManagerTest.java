package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModeManagerTest {

    @Test
    void broadcast() {
    }

    @Test
    void modeModify() {
        ModeManager m = new ModeManager();
        m.modeModify(6).doMessageAction();
        m.modeModify(3).doMessageAction();
        m.modeModify(3).doMessageAction();
        m.modeModify(4).doMessageAction();
        m.modeModify(4).doMessageAction();
        m.modeModify(3).doMessageAction();
        m.modeModify(2).doMessageAction();
        m.modeModify(3).doMessageAction();
        m.modeModify(2).doMessageAction();
        m.modeModify(1).doMessageAction();
    }

    @Test
    void showDefaultScreen() {
    }
}