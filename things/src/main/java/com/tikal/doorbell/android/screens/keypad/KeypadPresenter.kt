package com.tikal.doorbell.android.screens.keypad

import android.util.Log
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class KeypadPresenter : KeypadContract.Presenter {

    val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())
    lateinit var doorbellCode: String
    lateinit var view: KeypadContract.View

    companion object {
        const val TAG = "KeypadPresenter"
    }

    override fun subscribe(view: KeypadContract.View) {
        Log.i(TAG, "KeypadPresenter: init")
        this.view = view
        subscribeDatabase()
    }

    override fun unSubscribe() {
        codeObservable?.dispose()
    }

    private var codeObservable: Disposable? = null

    fun subscribeDatabase() {
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

    override fun onKeypadNumberClicked(num: Int) {
        Timber.i("### onKeypadNumberClicked $num")
    }


}