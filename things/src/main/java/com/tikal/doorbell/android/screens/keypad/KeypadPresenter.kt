package com.tikal.doorbell.android.screens.keypad

import com.tikal.BaseView
import com.tikal.doorbell.android.DoorManager
import com.tikal.doorbell.data.datasources.firebase.FirebaseRemoteDatasource
import com.tikal.doorbell.data.repository.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import timber.log.Timber.d
import timber.log.Timber.v

class KeypadPresenter : KeypadContract.Presenter {

    private lateinit var doorbellCode: String
    private lateinit var view: KeypadContract.View
    private val repository: FirebaseRepository = FirebaseRepository(FirebaseRemoteDatasource())
    private lateinit var doorManager: DoorManager

    private var enteredCode = ""

    private val compositeDisposable = CompositeDisposable()

    override fun subscribe(view: BaseView) {
        this.view = view as KeypadContract.View
        subscribeDatabase()
        doorManager = DoorManager()
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
        doorManager.destroy()
    }

    override fun onKeypadNumberClicked(value: String) {
        d("onKeypadNumberClicked %s", value)
        enteredCode = value
        verifyCode(enteredCode)
    }

    private fun verifyCode(code: String) {
        d("verifyCode")
        if (doorbellCode == code) {
            handleAccessGranted()
        } else {
            handleAccessDenied()
        }
        enteredCode = ""
        view.updateEnteredCode(enteredCode)
    }

    private fun subscribeDatabase() {
        v("subscribeDatabase")
        repository.getCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            doorbellCode = it
                            Timber.i("### $it")
                            view.toast(it)
                        }
                ) { Timber.e(it) }
                .addTo(compositeDisposable)
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