package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatasource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import timber.log.Timber.d
import timber.log.Timber.v

class KeypadPresenter : KeypadContract.Presenter {

    private lateinit var doorbellCode: String
    private lateinit var view: KeypadContract.View
    private val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatasource())

    private var enteredCode = ""

    private val mCompositeDisposable = CompositeDisposable()

    override fun subscribe(view: KeypadContract.View) {
        this.view = view
        subscribeDatabase()
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onKeypadNumberClicked(value: String) {
        d("onKeypadNumberClicked")
        enteredCode = value
        verifyCode()
    }

    private fun verifyCode() {
        d("verifyCode")
        if (doorbellCode == enteredCode) {
            view.toast("Door Open")
        } else {
            view.toast("Invalid Code")
        }
        enteredCode = ""
        view.updateEnteredCode(enteredCode)
    }

    private fun subscribeDatabase() {
        v("subscribeDatabase")
        val codeObservable = repository.getCode()
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
        mCompositeDisposable.add(codeObservable)
    }
}