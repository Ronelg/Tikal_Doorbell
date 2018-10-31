package com.tikal.doorbell.hw

import android.util.Log
import android.view.KeyEvent
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.button.ButtonInputDriver
import com.tikal.doorbell.android.BoardDefaults
import java.io.IOException

/**
 * Emits KEY_ENTER on doorbell button press.
 */
class DoorBellButton {

    // Button driver associated with PI3 doorbell GPIO
    lateinit var btnDriver: ButtonInputDriver

    init {
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
            Log.i(BoardDefaults.HW_DOORBELL_BUTTON, "doorbell button driver ready")
        } catch (e: IOException) {
            Log.e(BoardDefaults.HW_DOORBELL_BUTTON, "failed to initiate doorbell button driver", e)
            throw HardwareException(BoardDefaults.HW_DOORBELL_BUTTON, e.message)
        }
    }
}