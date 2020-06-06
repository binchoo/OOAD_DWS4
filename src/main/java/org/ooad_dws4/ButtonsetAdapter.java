package org.ooad_dws4;

public class ButtonsetAdapter {

    public ButtonsetAdapter() {
    }

    public void linkObject(Input input){
        this.input = input;
    }

    private Input input;

    public void buttonClick(int button) {
        input.buttonClick(button);
    }

}