package com.tikal.doorbell.android.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.NonNull

import io.reactivex.subjects.BehaviorSubject
import java.util.*

/**
 * Created by asafvaron on 11/09/2017.
 */

object RxEditText {

    val SEARCH_TIMEOUT: Long = 400

    fun fromEditText(@NonNull editText: EditText): BehaviorSubject<String> {
        val subject = BehaviorSubject.create<String>()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val query = charSequence.toString()
                if (query.length > 1) {
                    subject.onNext(query)
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

        return subject
    }
}
