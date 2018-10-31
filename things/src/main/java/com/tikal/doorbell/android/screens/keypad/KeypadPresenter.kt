package com.tikal.doorbell.android.screens.keypad

import android.util.Log
import android.widget.Toast
import com.tikal.doorbell.android.BaseView
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class KeypadPresenter : KeypadContract.Presenter {

    private lateinit var doorbellCode: String
    private lateinit var view: KeypadContract.View
    val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())

    val enteredCode = StringBuffer()

    companion object {
        const val TAG = "KeypadPresenter"
        const val DOORBELL_CODE_LENGTH = 4;
    }

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

    override fun onKeypadNumberClicked(value: String) {
        enteredCode.append(value)
        verifyCode(value)
    }

    fun verifyCode(value : String){
        if(enteredCode.length <= DOORBELL_CODE_LENGTH){
            if(value.equals(enteredCode)){
                view.toast("Door Open")
            }else{
                view.toast("Invalid Code")
            }
        } else {
            enteredCode.replace(0, enteredCode.length, "");
        }
        view.updateEnteredCode(enteredCode.toString())

    }
}