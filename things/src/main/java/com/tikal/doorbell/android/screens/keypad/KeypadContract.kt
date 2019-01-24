package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.BaseView

interface KeypadContract {
    interface Presenter : BasePresenter {
        fun onKeypadNumberClicked(value: String)
    }

    interface View : BaseView {
        fun toast(text: String)
        fun updateEnteredCode(enteredCode: String)
        fun showAccessDenied()
        fun showAccessGranted()
    }
}

