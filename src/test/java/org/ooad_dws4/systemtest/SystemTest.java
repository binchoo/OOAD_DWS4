package org.ooad_dws4.systemtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.ooad_dws4.Application;

import javax.swing.*;

public abstract class SystemTest {

    protected SystemMocker system;

    @BeforeEach
    private void systemInit() {
        system = new SystemMocker();
        system.showUp();

        testWorkerSleep(1000); // wait until view is updated at least once.
        onSystemReady();
    }

    @AfterEach
    private void systemFree() {
        Application.dwsFrame = null;
    }

    protected static void testWorkerSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    public abstract void onSystemReady();
}
