package org.ooad_dws4;

public class ButtonsetAdapter {

    private Input input;

    public ButtonsetAdapter() {
    }

    public void linkObject(Input input){
        this.input = input;
    }

    public void buttonClick(int button) {
        input.buttonClick(button);
    }

}