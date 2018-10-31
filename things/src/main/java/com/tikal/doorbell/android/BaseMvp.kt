package com.tikal.doorbell.android

interface BasePresenter {
    fun subscribe(view : BaseView)
    fun unSubscribe()
}

interface BaseView{
    fun setPresenter(presenter : BasePresenter)
}