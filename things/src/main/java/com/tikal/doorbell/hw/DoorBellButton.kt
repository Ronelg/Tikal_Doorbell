package com.tikal.doorbell.hw

import android.view.KeyEvent
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.button.ButtonInputDriver
import com.tikal.doorbell.android.BoardDefaults
import timber.log.Timber
import java.io.IOException

/**
 * Emits KEY_ENTER on doorbell button press.
 */
class DoorBellButton {

    // Button driver associated with PI3 doorbell GPIO
    private lateinit var btnDriver: ButtonInputDriver

    init {
        // Initiate the doorbell driver
        initDriver()
    }

    /**
     * Initiates the doorbell button driver.
     */
    private fun initDriver() {
        try {
            // Associate the button driver with the target GPIO
            btnDriver = ButtonInputDriver(BoardDefaults.gpioForButton,
                Button.LogicState.PRESSED_WHEN_LOW,
                KeyEvent.KEYCODE_ENTER)

            // Register the driver against AoT
            btnDriver.register()
            Timber.i("doorbell button driver ready")
        } catch (e: IOException) {
            Timber.e(e, "failed to initiate doorbell button driver")
            throw HardwareException(BoardDefaults.HW_DOORBELL_BUTTON, e.message)
        }
    }
}