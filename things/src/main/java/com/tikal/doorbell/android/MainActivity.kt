package com.tikal.doorbell.android

import android.app.Activity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


/**
 * Skeleton of an Android Things activity.
 *
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
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 *
 */
class MainActivity : Activity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var doorManager: DoorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        doorManager = DoorManager()
    }

    private fun handleAccessDenied() {
        showAccessDenied()
        lockDoor()
    }

    private fun handleAccessGranted() {
        showAccessGranted()
        openDoor()
    }

    private fun showAccessDenied() {
    }

    private fun showAccessGranted() {
    }

    /** Ensure the door is locked. */
    private fun lockDoor() {
        doorManager.lock()
    }

    /** Unlock to open the door. */
    private fun openDoor() {
        doorManager.unlock()
    }
}
