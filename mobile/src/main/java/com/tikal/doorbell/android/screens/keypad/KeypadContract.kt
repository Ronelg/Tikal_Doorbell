package com.tikal.doorbell.android.screens.keypad

interface KeypadContract {
    interface Presenter{
        fun onKeypadClicked(key : String)
    }

    interface View{

    }
}