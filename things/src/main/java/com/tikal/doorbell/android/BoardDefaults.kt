package com.tikal.doorbell.android

import android.os.Build


object BoardDefaults {
    private val DEVICE_RPI3 = "rpi3"
    private val DEVICE_IMX7D_PICO = "imx7d_pico"

    /**
     * Return the GPIO pin that the Button is connected on.
     */
    val gpioForButton: String
        get() {
            when (Build.DEVICE) {
                DEVICE_RPI3 -> return "BCM21"
                DEVICE_IMX7D_PICO -> return "GPIO6_IO14"
                else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
            }
        }

    /**
     * Return the GPIO pin that the door LED is connected on.
     */
    val gpioForDoor: String
        get() {
            when (Build.DEVICE) {
                DEVICE_RPI3 -> return "BCM16"
                DEVICE_IMX7D_PICO -> return "GPIO6_IO12"
                else -> throw IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE)
            }
        }

    /**
     * Hardware components.
     */
    const val HW_DOORBELL_BUTTON = "hw-db-btn"
}