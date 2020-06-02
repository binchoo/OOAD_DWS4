package org.ooad_dws4.Model.Output;

import org.ooad_dws4.Model.Common.Message;

public class OutputController{

    private BuzzerAdapter buzzerAdapter;
    private LCDAdapter lcdAdapter;

    public OutputController() {
        this.buzzerAdapter = new BuzzerAdapter();
        this.lcdAdapter = new LCDAdapter();
    }

    public void output(Message msg) {
        String action = msg.getAction();
        if(action.equals("updateView")){
            this.lcdAdapter.update(msg.getArg());
        }
        else{
            this.buzzerAdapter.execute(action);
        }
    }

    public BuzzerAdapter getBuzzerAdapter() {
        return buzzerAdapter;
    }

    public LCDAdapter getLcdAdapter() {
        return lcdAdapter;
    }
}