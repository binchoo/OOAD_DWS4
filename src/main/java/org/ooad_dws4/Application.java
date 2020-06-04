package org.ooad_dws4;

import org.ooad_dws4.View.DWSFrame;

public class Application {
        public static void main(String[] args) {
                Clock.timeUnit = 100;
                Clock.defaultScreenTime = 600000;
                Clock.buzzerOffTime = 7000;

                // Model
                Clock clock = new Clock();
                TimeRunner timeRunner = new TimeRunner();
                MainController mainController = new MainController();
                EventScheduler eventScheduler = new EventScheduler();
                IOBridge ioBridge = new IOBridge();
                ModeManager modeManager = new ModeManager();
                InputController inputController = new InputController();
                OutputController outputController = new OutputController();

                mainController.linkObjects(ioBridge, timeRunner, eventScheduler, modeManager);
                timeRunner.linkObject(mainController);
                clock.linkObjects(timeRunner);
                eventScheduler.linkObjects(mainController);
                ioBridge.linkObject(mainController, outputController);
                inputController.linkObject(ioBridge);

                // View
                DWSFrame dwsFrame = new DWSFrame();
                dwsFrame.getBtnPanel().getButtonsetAdapter().linkObject(inputController);
                outputController.getBuzzerAdapter().linkObject(dwsFrame.getBuzzer());
                outputController.getLcdAdapter().linkObject(dwsFrame.getLcdPanel());

                // TestCode
                // mainController.broadcast(new Date().getTime());
                // Release Code
                 clock.run();

        }
}
