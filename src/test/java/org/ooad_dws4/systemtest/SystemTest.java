package org.ooad_dws4.systemtest;

public abstract class SystemTest {

    protected SystemMocker system;

    public SystemTest() {
        system = new SystemMocker();
        system.showUp();

        sleepCompat(1000);

        onSystemReady();
    }

    abstract void onSystemReady();

    void sleepCompat(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }
}
