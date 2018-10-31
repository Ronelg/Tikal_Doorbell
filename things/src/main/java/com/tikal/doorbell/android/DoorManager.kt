package com.tikal.doorbell.android

import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager
import java.io.IOException

/**
 * Door manager.
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * val service = PeripheralManagerService()
 * val mLedGpio = service.openGpio("BCM6")
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * mLedGpio.value = true
 * }</pre>
 * <p>
 * @author moshe on 2018/10/31.
 */
class DoorManager {

    private val service = PeripheralManager.getInstance()
    private var ledGpio: Gpio? = null

    init {
        try {
            val led = service.openGpio(BoardDefaults.gpioForDoor)
            led.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
            ledGpio = led
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun unlock() {
        ledGpio?.value = true
    }

    fun lock() {
        ledGpio?.value = false
    }
}