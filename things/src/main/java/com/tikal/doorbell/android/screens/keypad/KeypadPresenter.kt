package com.tikal.doorbell.android.screens.keypad

import android.util.Log
import com.tikal.doorbell.android.BaseView
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class KeypadPresenter : KeypadContract.Presenter {

    val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())
    lateinit var doorbellCode: String
    lateinit var view: BaseView

    companion object {
        const val TAG = "KeypadPresenter"
    }

    override fun subscribe(view : BaseView) {
        Log.i(TAG, "KeypadPresenter: init")
        this.view = view
        subscribeDatabase()
    }

    override fun unSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun subscribeDatabase() {
        repository.getCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { doorbellCode = it },
                        onError = { Timber.e(it) }
                )

    }

    override fun onKeypadNumberClicked(num: Int) {
        TODO("compare entered value with firebase") //To change body of created functions use File | Settings | File Templates.
    }


}