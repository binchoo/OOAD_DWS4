package org.ooad_dws4;

public class Application {
    public static void main(String[] args) {
        TimeRunner t = new TimeRunner();

        Clock c = new Clock(1000, (PulseMaker) t);
        c.run();
    }
}
