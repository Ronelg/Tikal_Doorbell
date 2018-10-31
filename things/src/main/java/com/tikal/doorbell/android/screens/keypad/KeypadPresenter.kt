package com.tikal.doorbell.android.screens.keypad

import com.tikal.doorbell.android.BaseView
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import com.tikal.doorbell.android.data.repositories.firebase.FirebaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

class KeypadPresenter : KeypadContract.Presenter{

    val repository : FirebaseRepository = FirebaseRepository(FirebaseRemoteDatesource())
    lateinit var doorbellCode : String
    lateinit var view: BaseView



    override fun subscribe(view : BaseView) {
        this.view = view
        subscribeDatabase()
    }

    override fun unSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun subscribeDatabase(){
        repository.getCode().observeOn(Schedulers.io()).
                subscribeOn(AndroidSchedulers.mainThread()).subscribe{value ->
                doorbellCode = value;
        }
    }

    override fun onKeypadNumberClicked(num: Int) {
        TODO("compare entered value with firebase") //To change body of created functions use File | Settings | File Templates.
    }



}