package org.ooad_dws4;

public class OutputController{

    private static BuzzerAdapter buzzerAdapter = null;
    private static LCDAdapter lcdAdapter = null;

    public OutputController() {
        this.buzzerAdapter = new BuzzerAdapter();
        this.lcdAdapter = new LCDAdapter();
    }

    public void output(final Message msg) {
        final String action = msg.getAction();
        if("updateView".equals(action)){
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