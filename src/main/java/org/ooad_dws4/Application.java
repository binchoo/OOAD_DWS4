package org.ooad_dws4;

public class Application {
    public static void main(String[] args) {

        TimeRunner timeRunner = new TimeRunner();
        Clock clock = new Clock(1000);
        MainController mainController = new MainController();
        IOBridge ioBridge = new IOBridge();
        EventScheduler eventScheduler = new EventScheduler();
        ModeManager modeManager = new ModeManager();


        mainController.linkObjects(ioBridge, timeRunner, eventScheduler, modeManager);
        timeRunner.linkObject(mainController);
        clock.linkObjects(timeRunner);
        eventScheduler.linkObjects(mainController);
        modeManager.linkObjects(mainController);

        clock.run();

    }
}
