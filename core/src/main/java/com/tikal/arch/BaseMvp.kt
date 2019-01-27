package com.tikal.arch

interface BasePresenter {
    fun subscribe(view: BaseView)
    fun unsubscribe()
}

interface BaseView {
    fun setPresenter(presenter: BasePresenter)
}
