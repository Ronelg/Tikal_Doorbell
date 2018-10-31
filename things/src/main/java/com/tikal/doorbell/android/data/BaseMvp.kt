package com.tikal.doorbell.android.data

interface BasePresenter {
    fun subscribe()
    fun unSubscribe()
}

interface BaseView{
    fun setPresenter(presenter : BasePresenter)
}