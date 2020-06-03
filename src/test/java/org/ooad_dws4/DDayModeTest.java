package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DDayModeTest {

    @Test
    void getModeData() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.getModeData();
        if (m != null)
            m.doMessageAction();
    }

    @Test
    void update() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime(), true);
        if (m != null)
            m.doMessageAction();
    }

    @Test
    void testUpdate() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime());
        if (m != null)
            m.doMessageAction();
    }

    @Test
    void modeModify() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime(), true);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(2);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(3);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(4);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(4);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(5);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(3);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(3);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(2);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(4);
        if (m != null)
            m.doMessageAction();
        m = dDay.modeModify(1);
        if (m != null)
            m.doMessageAction();
    }
}