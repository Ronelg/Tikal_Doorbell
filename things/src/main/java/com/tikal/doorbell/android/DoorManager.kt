package com.tikal.doorbell.android

import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.io.IOException
import java.util.concurrent.TimeUnit

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

    companion object {
        private const val LED_OFF = true
        private const val LED_ON = false
    }

    private val service = PeripheralManager.getInstance()
    private var ledGpio: Gpio? = null
    private var blinker: Disposable? = null

    init {
        try {
            val led = service.openGpio(BoardDefaults.gpioForDoor)
            led.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
            ledGpio = led
            ledGpio?.value = LED_OFF
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun lock() {
        ledGpio?.value = LED_OFF
    }

    fun unlock() {
        ledGpio?.value = LED_ON
    }

    fun destroy() {
        blinker?.dispose()
        ledGpio?.close()
    }

    fun blink() {
        blinker = Observable.interval(0L, 1L, TimeUnit.SECONDS)
                .subscribe {
                    ledGpio?.value = it.rem(2L) == 0L
                }
    }

    fun stopBlink() {
        blinker?.dispose()
    }
}