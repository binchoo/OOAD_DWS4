package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DDayModeTest {

    @Test
    void getModeData() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.getModeData();
        assertEquals("D-DAY 1", m.getArg().get("3"));
    }

    @Test
    void update() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime(), true);
        assertEquals("D-DAY 1", m.getArg().get("3"));
    }

    @Test
    void testUpdate() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime());
        assertEquals(null, m);
    }

    @Test
    void modeModify() {
        DDayMode dDay = new DDayMode(true);
        Message m = dDay.update(new Date().getTime(), true);
        assertEquals(" 1 OFF", m.getArg().get("1"));
        m = dDay.modeModify(2);
        assertEquals("+1   1", m.getArg().get("1"));
        m = dDay.modeModify(3);
        assertEquals("D-DAY 4", m.getArg().get("3"));
        m = dDay.modeModify(4);
        assertEquals("D-DAY 1", m.getArg().get("3"));

    }
}