package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ModeManagerTest {

    @Test
    void modeModify() {
        ModeManager modeManager = new ModeManager();
        Message message = modeManager.modeModify(6); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals(" MODE  ", message.getArg().get("3"));

        message = modeManager.modeModify(3); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals(" ON", message.getArg().get("0"));

        message = modeManager.modeModify(2); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals("OFF", message.getArg().get("0"));

        modeManager.modeModify(4); //Enter Edit Mode
        message = modeManager.modeModify(4); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals("OFF", message.getArg().get("0"));

        message = modeManager.modeModify(2); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals(" ON", message.getArg().get("0"));

        message = modeManager.modeModify(1); //Enter Edit Mode
        assertEquals(11, message.getDestination());
        assertEquals("updateView", message.getAction());
        assertEquals("1971-01-11", message.getArg().get("4"));
    }

    @Test
    void showDefaultScreen() {
        ModeManager modeManager = new ModeManager();
        Message in = new Message(30, "SwitchDefaultScreen", null);
        Message message = modeManager.showDefaultScreen(in);
        assertEquals("1971-01-11", message.getArg().get("4"));
    }
}