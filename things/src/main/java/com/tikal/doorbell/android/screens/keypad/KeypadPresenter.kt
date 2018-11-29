package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import com.tikal.lang.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class KeypadPresenter : KeypadContract.Presenter {

    private lateinit var doorbellCode: String
    private lateinit var view: KeypadContract.View
    private val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())

    private val enteredCode = StringBuilder()

    private var codeObservable: Disposable? = null

    override fun subscribe(view: KeypadContract.View) {
        this.view = view
        subscribeDatabase()
    }

    override fun unsubscribe() {
        codeObservable?.dispose()
    }

    private fun subscribeDatabase() {
        Timber.v("subscribeDatabase")
        codeObservable = repository.getCode()
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

    override fun onKeypadNumberClicked(value: String) {
        enteredCode += value
        verifyCode(value)
    }

    private fun verifyCode(value: String) {
        if (value.length == enteredCode.length) {
            if (value == enteredCode.toString()) {
                view.toast("Door Open")
            } else {
                view.toast("Invalid Code")
            }
            enteredCode.clear()
        }
    }
}