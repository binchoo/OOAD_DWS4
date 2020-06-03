package org.ooad_dws4;

import org.junit.jupiter.api.Test;

class MainControllerTest {

    @Test
    void linkObjects() {
    }

    @Test
    void broadcast() {
    }

    @Test
    void inputEvent() {
        Clock.timeUnit = 1000;
        Clock.defaultScreenTime = 600000;
        Clock.buzzerOffTime = 7000;

        Clock clock = new Clock();
        TimeRunner timeRunner = new TimeRunner();
        MainController mainController = new MainController();
        EventScheduler eventScheduler = new EventScheduler();

        IOBridge ioBridge = new IOBridge();
        ModeManager modeManager = new ModeManager();

        mainController.linkObjects(ioBridge, timeRunner, eventScheduler, modeManager);
        timeRunner.linkObject(mainController);
        clock.linkObjects(timeRunner);
        eventScheduler.linkObjects(mainController);

        mainController.inputEvent(6);
        mainController.inputEvent(4);
        mainController.inputEvent(4);
        mainController.inputEvent(2);
        mainController.inputEvent(3);
        mainController.inputEvent(2);
        mainController.inputEvent(1);
    }

    @Test
    void defaultScreenTimerReset() {
    }

    @Test
    void stopBuzzer() {
    }
}