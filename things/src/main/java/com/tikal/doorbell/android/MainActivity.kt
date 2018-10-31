package com.tikal.doorbell.android

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.tikal.doorbell.android.screens.keypad.KeypadFragment
import com.tikal.doorbell.hw.DoorBellButton

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
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var doorManager: DoorManager
    private lateinit var mp: MediaPlayer
    private lateinit var dbButton: DoorBellButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbButton = DoorBellButton()
        mp = MediaPlayer.create(this, R.raw.doorbell)
        doorManager = DoorManager()
        doorManager.blink()

        Log.i(TAG, "onCreate")
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, KeypadFragment()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        doorManager.destroy()
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

    /**
     */
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        // Plays a doorbell sound when the doorbell button is pushed.
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Log.i(BoardDefaults.HW_DOORBELL_BUTTON, "doorbell button pressed")
            if (mp.isPlaying)
                mp.stop()
            mp.release()
            mp = MediaPlayer.create(this, R.raw.doorbell)
            mp.start()
        }
        return super.onKeyUp(keyCode, event)
    }
}
