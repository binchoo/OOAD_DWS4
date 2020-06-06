package org.ooad_dws4.systemtest

import org.ooad_dws4.Application
import org.ooad_dws4.View.DWSFrame

class ApplicationRunner: Runnable {

    var isRunning = false
    var view: DWSFrame? = null
    var onViewReadyListener: ((DWSFrame)->Unit)? = null

    override fun run() {
        isRunning = true;
        Application.main(null);
    }

    fun waitViewReady() {
        if (isRunning) {
            view = Application.dwsFrame

            while (view == null);
            onViewReadyListener?.invoke(view!!)
        } else {
            throw Exception()
        }
    }
}