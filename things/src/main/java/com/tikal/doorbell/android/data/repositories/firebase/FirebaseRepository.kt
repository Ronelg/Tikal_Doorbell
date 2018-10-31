package com.tikal.doorbell.android.data.repositories.firebase

import com.tikal.doorbell.android.data.datasources.firebase.FbRealtimeDatabase
import com.tikal.doorbell.android.data.datasources.firebase.FirebaseRemoteDatesource
import io.reactivex.Observable

class FirebaseRepository(val remoteDatesource: FirebaseRemoteDatesource) : FbRealtimeDatabase {

    override fun getCode(): Observable<String> {
        return remoteDatesource.getCode()
    }

    override fun updateCode(code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}