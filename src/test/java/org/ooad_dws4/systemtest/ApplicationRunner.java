package org.ooad_dws4.systemtest;

import org.ooad_dws4.Application;
import org.ooad_dws4.View.DWSFrame;

public class ApplicationRunner implements Runnable {

    Boolean isRunning = false;
    DWSFrame view = null;
    public OnViewReadyListener onViewReadyListener = null;

    @Override
    public void run() {
        isRunning = true;
        Application.main(null);
    }

    synchronized public void waitViewReady() {

        System.out.println("System Loading.");
        while (!isRunning) waitMillis(100);

        System.out.println("View Loading.");
        while ((view = Application.dwsFrame) == null)
            waitMillis(100);

        System.out.println("System Loaded.");
        if (onViewReadyListener != null)
            onViewReadyListener.onViewReady(view);
    }

    void waitMillis(long l) {
        try {
            super.wait(l);
        } catch (InterruptedException e) {
        }
    }

    interface OnViewReadyListener {
        void onViewReady(DWSFrame view);
    }
}
