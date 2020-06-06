package org.ooad_dws4.systemtest

import org.junit.jupiter.api.Test
import org.ooad_dws4.View.DWSFrame
import org.junit.jupiter.api.Assertions.*


class ModeChangeTest {

    lateinit var view: DWSFrame

    init {
        systemSetup()
    }

    fun systemSetup() {
        val appRunner = ApplicationRunner()
        appRunner.onViewReadyListener = {
            view = it
        }

        val systemThread = Thread(appRunner)
        systemThread.start()

        appRunner.waitViewReady()
    }

    @Test
    fun viewReferenceOK() {
        assertNotNull(view)
    }

}