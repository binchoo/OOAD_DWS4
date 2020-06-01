package org.ooad_dws4.Controller;

import org.ooad_dws4.Model.Controller.IOBridge;
import org.ooad_dws4.Model.Controller.MainController;
import org.ooad_dws4.Model.Input.InputController;
import org.ooad_dws4.Model.Output.OutputController;
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
