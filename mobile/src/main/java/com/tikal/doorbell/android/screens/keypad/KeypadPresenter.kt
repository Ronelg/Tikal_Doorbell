package com.tikal.doorbell.android.screens.keypad

import android.annotation.SuppressLint
import com.tikal.doorbell.android.repository.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

class KeypadPresenter(val view: KeypadContract.View) : KeypadContract.Presenter {
    val repository : AppRepository
    var doorCode : String? = null
    val subscriptions = CompositeDisposable()
    init {
        repository = AppRepository()
        loadDoorbellCode()
    }

    private fun loadDoorbellCode() {

        val subscribe = repository.getDoorCode().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { t -> doorCode = t }

        subscriptions.add(subscribe)
    }

    override fun onKeypadClicked(key: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun processKeypadClick(key: String) {

    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}