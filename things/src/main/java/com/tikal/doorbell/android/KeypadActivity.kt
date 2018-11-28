package com.tikal.doorbell.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.keypad.*
import timber.log.Timber.d

class KeypadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.keypad_activity)

        loopViews(keypadRoot)
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
                showMessasge(view, "pad1")
            }
            R.id.pad_2 -> {
                showMessasge(view, "pad2")
            }
            R.id.pad_3 -> {
                showMessasge(view, "pad3")
            }
            R.id.pad_4 -> {
                showMessasge(view, "pad4")
            }
            R.id.pad_5 -> {
                showMessasge(view, "pad5")
            }
            R.id.pad_6 -> {
                showMessasge(view, "pad6")
            }
            R.id.pad_7 -> {
                showMessasge(view, "pad7")
            }
            R.id.pad_8 -> {
                showMessasge(view, "pad8")
            }
            R.id.pad_9 -> {
                showMessasge(view, "pad9")
            }
            R.id.pad_0 -> {
                showMessasge(view, "pad0")
            }
            R.id.pad_star -> {
                showMessasge(view, "pad_star")
            }
            R.id.pad_hash_tag -> {
                showMessasge(view, "pad_hash_tag")
            }
        }
    }

    private fun showMessasge(view: View, text: String) {
        d(text)
        Toast.makeText(view.context, text,Toast.LENGTH_SHORT).show()
    }
}