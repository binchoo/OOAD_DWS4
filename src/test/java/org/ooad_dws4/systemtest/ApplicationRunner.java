package org.ooad_dws4.systemtest;

import org.ooad_dws4.Application;
import org.ooad_dws4.View.DWSFrame;

public class ApplicationRunner implements Runnable {

    boolean isRunning = false;
    DWSFrame view = null;
    public OnViewReadyListener onViewReadyListener = null;


    @Override
    public void run() {
        isRunning = true;
        Application.main(null);
    }

    public void waitViewReady() {
        while (!isRunning);
        System.out.println("System Executed.");

        while ((view = Application.dwsFrame) == null);
        System.out.println("View Loaded.");

        onViewReadyListener.onViewReady(view);
    }

    interface OnViewReadyListener {
        void onViewReady(DWSFrame view);
    }
}
