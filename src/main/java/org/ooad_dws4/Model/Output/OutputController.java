package org.ooad_dws4.Model.Output;

import org.ooad_dws4.Model.Common.DWSObject;
import org.ooad_dws4.Model.Common.Message;

public class OutputController extends DWSObject {

    public OutputController() {
        this.buzzerAdapter = new BuzzerAdapter();
        this.lcdAdapter = new LCDAdapter();
    }

    private BuzzerAdapter buzzerAdapter;
    private LCDAdapter lcdAdapter;

    public boolean output(Message msg) {
        // TODO implement here
        if(true){
//            buzzerAdapter.execute();
            return true;
        }
        else if(true){
//                lcdAdapter.update();
            return true;
        }
        else
            return false;
    }

    public BuzzerAdapter getBuzzerAdapter() {
        return buzzerAdapter;
    }

    public LCDAdapter getLcdAdapter() {
        return lcdAdapter;
    }
}