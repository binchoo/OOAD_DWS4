package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IOBridgeTest {
    @Test
    void outputEventTest() {
        IOBridge ioBridge = new IOBridge();
        OutputController outputController = new OutputController();
        outputController.getLcdAdapter().linkObject(new LCDPanel());
        ioBridge.linkObject(new MainController(), outputController);
        outputController.getBuzzerAdapter().linkObject(new BuzzerSound());

        // test 1
        ioBridge.outputEvent(new Message(10,
                "toggleMute", null));
        assertTrue(ioBridge.isMute());
        ioBridge.outputEvent(new Message(10,
                "toggleMute", null));
        assertTrue(!ioBridge.isMute());

        // test 2
        ioBridge.outputEvent(new Message(11,
                "buzzRinging", null));
        assertTrue(ioBridge.isBuzzerRinging());
        ioBridge.outputEvent(new Message(11,
                "buzzOff", null));
        assertTrue(!ioBridge.isBuzzerRinging());

    }

    @Test
    void inputEventTest() {
        // test impossible
    }

    @Test
    void toggleSoundTest() {
        IOBridge ioBridge = new IOBridge();
        ioBridge.toggleSound();
        assertTrue(ioBridge.isMute());
        ioBridge.toggleSound();
        assertTrue(!ioBridge.isMute());
    }
}
