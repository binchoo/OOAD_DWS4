package org.ooad_dws4;

import org.ooad_dws4.View.DWSFrame;

public class Application {
    public static void main(String[] args) {

        // Model
        MainController mainController = new MainController();
        IOBridge ioBridge = new IOBridge();
        InputController inputController = new InputController();
        OutputController outputController = new OutputController();

        mainController.linkObjects(ioBridge);
        ioBridge.linkObject(mainController, outputController);
        inputController.linkObject(ioBridge);

        //View
        DWSFrame dwsFrame = new DWSFrame();

        dwsFrame.getBtnPanel().getButtonsetAdapter().linkObject(inputController);
        outputController.getBuzzerAdapter().linkObject(dwsFrame.getBuzzer());
        outputController.getLcdAdapter().linkObject(dwsFrame.getLcdPanel());

    }
}
