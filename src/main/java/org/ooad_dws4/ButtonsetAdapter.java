package org.ooad_dws4;

public class ButtonsetAdapter {

    private static Input input;

    public void linkObject(final Input input){
        this.input = input;
    }

    public void buttonClick(final int button) {
        input.buttonClick(button);
    }

}