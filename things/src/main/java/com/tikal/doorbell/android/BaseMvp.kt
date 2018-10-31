package com.tikal.doorbell.android

import com.tikal.doorbell.android.screens.keypad.KeypadContract

interface BasePresenter {
    fun subscribe(view :  KeypadContract.View)
    fun unsubscribe()
}

interface BaseView{
    fun setPresenter(presenter : BasePresenter)
}