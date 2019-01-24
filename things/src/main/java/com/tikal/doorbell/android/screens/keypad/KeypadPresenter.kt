package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.DoorManager
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatasource
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
    private val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatasource())
    private lateinit var doorManager: DoorManager

    private val enteredCode = StringBuilder()

    private var codeObservable: Disposable? = null

    override fun subscribe(view: KeypadContract.View) {
        this.view = view
        subscribeDatabase()
        doorManager = DoorManager()
    }

    override fun unsubscribe() {
        codeObservable?.dispose()
        doorManager.destroy()
    }

    private fun subscribeDatabase() {
        Timber.v("subscribeDatabase")
        codeObservable = repository.getCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { code ->
                            doorbellCode = code
                            Timber.i("### $code")
                            view.toast(code)
                        },
                        onError = { Timber.e(it) }
                )
    }

    override fun onKeypadNumberClicked(value: String) {
        Timber.v("onKeypadNumberClicked %s", value)
        enteredCode += value
        verifyCode(doorbellCode, enteredCode.toString())
    }

    private fun verifyCode(expected: String, actual: String) {
        if (expected.length == actual.length) {
            if (expected == actual) {
                handleAccessGranted()
            } else {
                handleAccessDenied()
            }
            enteredCode.clear()
        }
    }

    private fun handleAccessDenied() {
        view.showAccessDenied()
        lockDoor()
    }

    private fun handleAccessGranted() {
        view.showAccessGranted()
        openDoor()
    }

    /** Ensure the door is locked. */
    private fun lockDoor() {
        doorManager.lock()
    }

    /** Unlock to open the door. */
    private fun openDoor() {
        doorManager.unlock()
    }
}