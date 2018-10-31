package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.BasePresenter
import com.tikal.doorbell.android.BaseView

interface KeypadContract {
    interface Presenter:BasePresenter{
        fun onKeypadNumberClicked(num : Int)
    }

    interface View : BaseView{

    }
}

