package com.tikal.doorbell.android.screens.keypad

interface KeypadContract {
    interface Presenter{
        fun onKeypadClicked(key : String)
        fun subscribe()
        fun unsubscribe()
    }

    interface View{

    }
}