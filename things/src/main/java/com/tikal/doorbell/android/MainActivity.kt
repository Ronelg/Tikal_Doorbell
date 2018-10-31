package com.tikal.doorbell.android

import android.app.Activity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.database.FirebaseDatabase


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        // Initialize the doorbell button driver
        initPIO()
    }

    private fun initPIO() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleAccessDenied() {
        showAccessDenied()
        lockDoor()
    }

    private fun handleAccessGranted() {
        showAccessGranted()
        openDoor()
    }

    /** Switch on the red LED. */
    private fun showAccessDenied() {
        TODO("Switch on the red LED.")
    }

    /** Switch on the green LED. */
    private fun showAccessGranted() {
        TODO("Switch on the green LED")
    }

    /** Ensure the door is locked. */
    private fun lockDoor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** Open the door. */
    private fun openDoor() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
