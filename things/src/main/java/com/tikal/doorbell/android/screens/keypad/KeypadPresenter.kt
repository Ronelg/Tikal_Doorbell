package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class KeypadPresenter : KeypadContract.Presenter {

    private val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())
    private lateinit var doorbellCode: String
    private lateinit var view: KeypadContract.View

    override fun subscribe(view: KeypadContract.View) {
        Timber.i("KeypadPresenter: init")
        this.view = view
        subscribeDatabase()
    }

    override fun unsubscribe() {
    }

    fun subscribeDatabase() {
        repository.getCode()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    doorbellCode = it
                    Timber.i("### $it")
                    view.toast(it)

                },
                onError = { Timber.e(it) }
            )

    }

    override fun onKeypadNumberClicked(num: Int) {
        Timber.i("### onKeypadNumberClicked $num")
    }
}