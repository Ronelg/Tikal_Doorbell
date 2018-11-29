package com.tikal.doorbell.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.keypad_activity.*
import timber.log.Timber.d

class KeypadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.keypad_activity)

        loopViews(keypad as ViewGroup)
    }

    private fun loopViews(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            view.getChildAt(i).setOnClickListener {
                onKeypadItemClick(it)
            }
        }
    }

    private fun onKeypadItemClick(view: View) {
        d("view click")
        when (view.id) {
            R.id.pad_1 -> {
                showMessage(view, "pad1")
            }
            R.id.pad_2 -> {
                showMessage(view, "pad2")
            }
            R.id.pad_3 -> {
                showMessage(view, "pad3")
            }
            R.id.pad_4 -> {
                showMessage(view, "pad4")
            }
            R.id.pad_5 -> {
                showMessage(view, "pad5")
            }
            R.id.pad_6 -> {
                showMessage(view, "pad6")
            }
            R.id.pad_7 -> {
                showMessage(view, "pad7")
            }
            R.id.pad_8 -> {
                showMessage(view, "pad8")
            }
            R.id.pad_9 -> {
                showMessage(view, "pad9")
            }
            R.id.pad_0 -> {
                showMessage(view, "pad0")
            }
            R.id.pad_star -> {
                showMessage(view, "pad_star")
            }
            R.id.pad_hash_tag -> {
                showMessage(view, "pad_hash_tag")
            }
        }
    }

    private fun showMessage(view: View, text: String) {
        d(text)
        Toast.makeText(view.context, text,Toast.LENGTH_SHORT).show()
    }
}