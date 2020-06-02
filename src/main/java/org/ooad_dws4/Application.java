package org.ooad_dws4;

public class Application {
    public static void main(String[] args) {

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

        clock.run();

    }
}
